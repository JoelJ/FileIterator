package com.joelj.collections;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Iterates a FileIterable.
 * Uses the AbstractFileIterable as a backend (since it already handles caching lines for whatever implementation this iterator is for).
 *
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 11:22 PM
 */
public class FileIterator implements Iterator<String> {
	private final File file;
	private final AbstractFileIterable iterable;
	private int pointer;

	FileIterator(File file, AbstractFileIterable iterable) {
		this.file = file;
		this.iterable = iterable;
		this.pointer = 0;
	}

	@Override
	public boolean hasNext() {
		return iterable.hasLineAt(pointer+1);
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
