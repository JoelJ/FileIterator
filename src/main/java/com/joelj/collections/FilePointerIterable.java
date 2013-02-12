package com.joelj.collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * User: Joel Johnson
 * Date: 2/12/13
 * Time: 12:38 AM
 */
public class FilePointerIterable extends AbstractFileIterable {
	private final File file;
	private final RandomAccessFile randomAccessFile;
	private boolean fileOpen;
	private boolean endReached;
	private int currentLine;
	private Map<Integer, Long> newLinePointers;

	public static FilePointerIterable openFile(File file) throws FileNotFoundException {
		return new FilePointerIterable(file);
	}

	private FilePointerIterable(File file) throws FileNotFoundException {
		this.file = file;
		randomAccessFile = new RandomAccessFile(file, "r");
		fileOpen = true;
		endReached = false;
		currentLine = 0;
		newLinePointers = new HashMap<Integer, Long>();
		newLinePointers.put(currentLine, 0L); //Line 0 starts at position 0
	}

	@Override
	public String get(int index) {
		String result = null;
		while(currentLine <= index) {
			try {
				result = this.readLine();
				if(result == null) {
					break;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		//At this point: we have either found the line we're looking for, or we have hit the end of the file
		//So let's make sure we haven't hit the end of the file.
		if(index > currentLine) {
			throw new IndexOutOfBoundsException("No line: "+index + " in file " + file);
		}

		//If result isn't null, that means the last line we read was the line we're looking for.
		if(result != null) {
			return result;
		} else {
			//If result is null, that means we've read the line before, and we need to look it up again.
			try {
				//seek to the cached position, and return the value from the stream.
				Long pos = newLinePointers.get(index);

				randomAccessFile.seek(pos);
				currentLine = index+1;
				return randomAccessFile.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private String readLine() throws IOException {
		if(!fileOpen) {
			throw new IllegalStateException("The file has been closed");
		}
		if(endReached) {
			return null;
		}

		String result = randomAccessFile.readLine();
		if(result != null) {
			long nextLineStartPosition = randomAccessFile.getFilePointer();
			newLinePointers.put(++currentLine, nextLineStartPosition);
			return result;
		} else {
			endReached = true;
//			newLinePointers.remove(currentLine); //Remove the last start of line, since it's not a real line.
			newLinePointers = Collections.unmodifiableMap(newLinePointers); //Make sure we don't modify this map anymore. It's been finalized.
			return null; //End of file was reached last time this method was called.
		}
	}

	@Override
	/*package*/ int getCurrentLine() {
		return currentLine;
	}

	@Override
	/*package*/ boolean hasLineAt(int index) {
		//TODO: Holy cow the get method needs to be refactored so I don't have to do this nastiness
		try {
			this.get(index);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	@Override
	public boolean isFileOpen() {
		return fileOpen;
	}

	@Override
	public void close() {
		try {
			randomAccessFile.close();
			fileOpen = false;
		} catch (IOException ignore) {
		}
	}

	@Override
	public FileIterator iterator() {
		return new FileIterator(file, this);
	}
}
