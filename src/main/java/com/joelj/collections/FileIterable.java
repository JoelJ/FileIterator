package com.joelj.collections;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Allows easy retrieval of a file by lines or iterating over a file.
 *
 * This was mostly for fun. I never bothered looking to see if this was done already.
 *
 * You can iterate a file using a ForEach loop. The file will automatically close when the end has been reached.
 * (However, the close method should still be called from a finally block in case something goes wrong.)
 *
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 9:24 PM
 */
public class FileIterable implements Iterable<String>, Closeable {
	private final File file;
	private final Scanner scanner;
	private int currentLine;
	private boolean fileOpen;
	private List<String> cache;

	public static FileIterable openFile(String path) throws FileNotFoundException {
		return openFile(new File(path));
	}

	public static FileIterable openFile(File file) throws FileNotFoundException {
		return new FileIterable(file);
	}

	private FileIterable(File file) throws FileNotFoundException {
		this.file = file;
		this.scanner = new Scanner(file);
		this.currentLine = 0;
		fileOpen = true;
		cache = new ArrayList<String>();
	}

	public boolean isFileOpen() {
		return fileOpen;
	}

	@Override
	public void close() {
		fileOpen = false;
		this.scanner.close();
	}

	public String get(int index) {
		while(currentLine <= index) {
			if(scanner.hasNextLine()) {
				readLine();
			} else {
				this.close(); //The file has been fully read.
				throw new IndexOutOfBoundsException("No line: "+index + " in file " + file);
			}
		}
		return cache.get(index);
	}

	@Override
	public FileIterator iterator() {
		return new FileIterator(file, this);
	}

	/*package*/ String readLine() {
		if(!fileOpen) {
			throw new IllegalStateException("The file has been closed");
		}
		if(scanner.hasNextLine()) {
			currentLine++;
			String s = scanner.nextLine();
			cache.add(s);
			assert cache.size() == currentLine;
			return s;
		} else {
			this.close();
			throw new IllegalStateException("Trying to read a line from the file, but the file has already been read.");
		}
	}

	/*package*/ Scanner getScanner() {
		return scanner;
	}

	/*package*/ int getCurrentLine() {
		return currentLine;
	}

	@Override
	public String toString() {
		return "FileIterable{" +
				"file=" + file +
				", currentLine=" + currentLine +
				", fileOpen=" + fileOpen +
				'}';
	}
}
