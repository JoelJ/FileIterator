package com.joelj.collections;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

/**
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 8:20 PM
 */
public interface FileIterable extends Iterable<String>, Closeable {
	/**
	 * Gets the value of the given line, excluding the trailing newline characters.
	 * @param index The line to get
	 * @return The line at the given line number
	 */
	String get(int index);

	/**
	 * Closes the underlying file stream.
	 * Any exceptions should be handled and not thrown.
	 */
	void close();

	/**
	 * Returns the iterator for the Iterable.
	 */
	@Override
	FileIterator iterator();
}
