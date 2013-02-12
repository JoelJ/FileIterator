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
	public static class PatternPredicate implements Predicate {
		private final Pattern pattern;
		public PatternPredicate(Pattern pattern) {
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
	public static class StartsWithPredicate implements Predicate {
		private final String substring;
		public StartsWithPredicate(String substring) {
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
	public static class EndsWithPredicate implements Predicate {
		private final String substring;
		public EndsWithPredicate(String substring) {
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
	public static class ContainsPredicate implements Predicate {
		private final String substring;
		public ContainsPredicate(String substring) {
			this.substring = substring;
		}

		@Override
		public boolean call(String string) {
			return string.contains(substring);
		}
	}
}
