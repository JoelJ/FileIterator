package com.joelj.collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 9:02 PM
 */
public class SearchTest {
	@Test
	public void testPattern() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("SearchFile.txt");
		FileIterable fileIterable = FileList.openFile(file);
		try {
			List<String> strings = fileIterable.find(new Predicates.PatternPredicate(Pattern.compile("\\d+")));
			Assert.assertEquals(strings.size(), 11);
			for (String string : strings) {
				Assert.assertTrue(string.matches(".*\\d.*")); //sanity check. Make sure the predicates behavior is never changed.
			}
		} finally {
			fileIterable.close();
		}
	}

	@Test
	public void testStartsWith() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("SearchFile.txt");
		FileIterable fileIterable = FileList.openFile(file);
		try {
			List<String> strings = fileIterable.find(new Predicates.StartsWithPredicate("Lorem Ipsum"));
			Assert.assertEquals(strings.size(), 3);
			for (String string : strings) {
				Assert.assertTrue(string.startsWith("Lorem Ipsum")); //sanity check. Make sure the predicates behavior is never changed.
			}
		} finally {
			fileIterable.close();
		}
	}

	@Test
	public void testFirst() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("SearchFile.txt");
		FileIterable fileIterable = FileList.openFile(file);
		try {
			String string = fileIterable.findFirst(new Predicates.StartsWithPredicate("and"));
			Assert.assertNotNull(string);
			Assert.assertEquals(string, "and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		} finally {
			fileIterable.close();
		}
	}

	@Test
	public void testEndsWith() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("SearchFile.txt");
		FileIterable fileIterable = FileList.openFile(file);
		try {
			List<String> strings = fileIterable.find(new Predicates.EndsWithPredicate(","));
			Assert.assertEquals(strings.size(), 29);
			for (String string : strings) {
				Assert.assertTrue(string.endsWith(",")); //sanity check. Make sure the predicates behavior is never changed.
			}
		} finally {
			fileIterable.close();
		}
	}

	@Test
	public void testContains() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("SearchFile.txt");
		FileIterable fileIterable = FileList.openFile(file);
		try {
			List<String> strings = fileIterable.find(new Predicates.ContainsPredicate("Lorem Ipsum"));
			Assert.assertEquals(strings.size(), 16);
			for (String string : strings) {
				Assert.assertTrue(string.contains("Lorem Ipsum")); //sanity check. Make sure the predicates behavior is never changed.
			}
		} finally {
			fileIterable.close();
		}
	}
}
