package com.joelj.collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 9:44 PM
 */
public class FilePointerIterableTest {
	@Test
	public void testGet() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("File2.txt");

		FilePointerIterable iterable = FilePointerIterable.openFile(file);

		try {
			Assert.assertEquals(iterable.get(4), "line 4");
			Assert.assertEquals(iterable.get(3), "line 3");
			Assert.assertEquals(iterable.get(5), "line 5");
			Assert.assertEquals(iterable.get(1), "line 1");
			Assert.assertEquals(iterable.get(10), "line 10");
			Assert.assertEquals(iterable.get(7), "line 7");
			Assert.assertEquals(iterable.get(8), "line 8");
			Assert.assertEquals(iterable.get(9), "line 9");
			Assert.assertEquals(iterable.get(10), "line 10");
			Assert.assertEquals(iterable.get(11), "line 11");
			Assert.assertEquals(iterable.get(0), "line 0");
			Assert.assertEquals(iterable.get(11), "line 11");
			Assert.assertEquals(iterable.get(1), "line 1");
		} finally {
			iterable.close();
		}
	}

	@Test
	public void testIterate() throws FileNotFoundException {
		File file = ResourceFiles.getResourceFile("File2.txt");
		FilePointerIterable iterable = FilePointerIterable.openFile(file);

		try {
			int count = 0;
			for (String line : iterable) {
				Assert.assertEquals(line, "line "+count);
				count++;
			}
			Assert.assertNotEquals(count, 0, "Should iterate the first time");

			count = 0;
			for (String line : iterable) {
				Assert.assertEquals(line, "line "+count);
				count++;
			}
			Assert.assertNotEquals(count, 0, "Should iterate the second time");
		} finally {
			iterable.close();
		}
	}
}
