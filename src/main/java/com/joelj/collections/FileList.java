package com.joelj.collections;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Implements FileIterable loading lines into a List as Lines are read.
 *
 * Good for smaller files that can fit in memory that will be used for random access or searched over multiple times.
 *
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 9:24 PM
 */
public class FileList extends AbstractFileIterable implements Closeable {
	private final File file;
	private final Scanner scanner;
	private int currentLine;
	private boolean fileOpen;
	private List<String> cache;

	public static FileList openFile(String path) throws FileNotFoundException {
		return openFile(new File(path));
	}

	public static FileList openFile(File file) throws FileNotFoundException {
		return new FileList(file);
	}

	private FileList(File file) throws FileNotFoundException {
		this.file = file;
		this.scanner = new Scanner(file);
		this.currentLine = 0;
		fileOpen = true;
		cache = new ArrayList<String>();
	}

	@Override
	public boolean isFileOpen() {
		return fileOpen;
	}

	@Override
	public void close() {
		fileOpen = false;
		this.scanner.close();
	}

	@Override
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

	@Override
	/*package*/ Scanner getScanner() {
		return scanner;
	}

	@Override
	/*package*/ int getCurrentLine() {
		return currentLine;
	}

	@Override
	public String toString() {
		return "FileList{" +
				"file=" + file +
				", currentLine=" + currentLine +
				", fileOpen=" + fileOpen +
				'}';
	}
}
