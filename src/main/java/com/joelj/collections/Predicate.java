package com.joelj.collections;

/**
 * Used in the {@link FileIterable} to do searches.
 * Default implementations are provided in the {@link Predicates} class.
 *
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 8:56 PM
 */
public interface Predicate {
	/**
	 * @param string The string to check to see if it should be included as a result.
	 * @return True if the given string should be included as a result. False if it should be ignored.
	 */
	public boolean call(String string);
}
