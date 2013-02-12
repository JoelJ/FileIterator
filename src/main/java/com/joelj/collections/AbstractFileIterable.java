package com.joelj.collections;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Scanner;

/**
 * Base implementation for FileIterables.
 *
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 8:23 PM
 */
abstract class AbstractFileIterable implements FileIterable {
	/*package*/ abstract Scanner getScanner();
	/*package*/ abstract int getCurrentLine();
	public abstract boolean isFileOpen();

	@Override
	public List<String> toList() {
		ImmutableList.Builder<String> result = ImmutableList.builder();
		for (String line : this) {
			result.add(line);
		}
		return result.build();
	}

	@Override
	public void toList(List<String> listToPopulate) {
		for (String line : this) {
			listToPopulate.add(line);
		}
	}

	private static final boolean FIRST_ONLY = true;
	private static final boolean ALL_RESULTS = false;

	public String findFirst(Predicate predicate) {
		ImmutableList<String> strings = find(predicate, FIRST_ONLY);
		if(!strings.isEmpty()) {
			return strings.get(0);
		}
		return null;
	}

	public List<String> find(Predicate predicate) {
		return find(predicate, ALL_RESULTS);
	}

	private ImmutableList<String> find(Predicate predicate, boolean firstOnly) {
		ImmutableList.Builder<String> builder = ImmutableList.builder();
		for (String line : this) {
			if(predicate.call(line)) {
				builder.add(line);
				if(firstOnly) {
					break;
				}
			}
		}
		return builder.build();
	}
}

