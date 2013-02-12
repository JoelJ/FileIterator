package com.joelj.collections;

import java.io.File;
import java.util.Iterator;

/**
 * Iterates a FileIterable.
 * Uses the FileIterable as a backend (since it already handles caching lines).
 *
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 11:22 PM
 */
public class FileIterator implements Iterator<String> {
	private final File file;
	private final FileList iterable;
	private int pointer;

	FileIterator(File file, FileList iterable) {
		this.file = file;
		this.iterable = iterable;
		this.pointer = 0;
	}

	@Override
	public boolean hasNext() {
		boolean result = iterable.getCurrentLine() > pointer || (iterable.isFileOpen() && iterable.getScanner().hasNextLine());
		if(!result) {
			iterable.close();
		}
		return result;
	}

	@Override
	public String next() {
		return iterable.get(pointer++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "FileIterator{" +
				"file=" + file +
				", pointer=" + pointer +
				'}';
	}
}
