package com.joelj.collections;

import java.io.Closeable;
import java.util.List;

/**
 * Allows easy retrieval of a file by lines or iterating over a file.
 *
 * You can iterate a file using a ForEach loop. The file will automatically close when the end has been reached.
 * (However, the close method should still be called from a finally block in case something goes wrong.)
 *
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

	String findFirst(Predicate predicate);
	List<String> find(Predicate predicate);
}
