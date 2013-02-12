package com.joelj.collections;

import java.util.regex.Pattern;

/**
 * Default implementations of the Predicate interface.
 *
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 8:56 PM
 */
public class Predicates {
	/**
	 * Filters values that match the given pattern
	 */
	public static class Pattern implements Predicate {
		private final java.util.regex.Pattern pattern;
		public Pattern(java.util.regex.Pattern pattern) {
			this.pattern = pattern;
		}

		@Override
		public boolean call(String string) {
			return pattern.matcher(string).find();
		}
	}

	/**
	 * Filters strings if they start with the given substring
	 */
	public static class StartsWith implements Predicate {
		private final String substring;
		public StartsWith(String substring) {
			this.substring = substring;
		}

		@Override
		public boolean call(String string) {
			return string.startsWith(substring);
		}
	}

	/**
	 * Filters strings if they end with the given substring
	 */
	public static class EndsWith implements Predicate {
		private final String substring;
		public EndsWith(String substring) {
			this.substring = substring;
		}

		@Override
		public boolean call(String string) {
			return string.endsWith(substring);
		}
	}

	/**
	 * Filters strings if they contain the given substring
	 */
	public static class Contains implements Predicate {
		private final String substring;
		public Contains(String substring) {
			this.substring = substring;
		}

		@Override
		public boolean call(String string) {
			return string.contains(substring);
		}
	}
}
