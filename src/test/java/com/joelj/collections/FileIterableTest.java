package com.joelj.collections;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * User: Joel Johnson
 * Date: 2/10/13
 * Time: 9:44 PM
 */
public class FileIterableTest {
	@Test
	public void testGet() throws FileNotFoundException {
		File file = getResourceFile("File1.txt");

		FileList spyFileIterable = Mockito.spy(FileList.openFile(file));

		try {
			Assert.assertEquals(spyFileIterable.get(4), "line 4");

			Mockito.doThrow(new AssertionError("The FileIterable should have already loaded line 3.")).when(spyFileIterable).readLine();
			Assert.assertEquals(spyFileIterable.get(3), "line 3");

			Mockito.reset(spyFileIterable);

			Assert.assertEquals(spyFileIterable.get(5), "line 5");
			Assert.assertEquals(spyFileIterable.get(1), "line 1");
		} finally {
			spyFileIterable.close();
		}
	}

	@Test
	public void testIterator() throws FileNotFoundException {
		File file = getResourceFile("File1.txt");
		FileIterable fileIterable = FileList.openFile(file);
		int counter = 0;
		try {
			for (String line : fileIterable) {
				Assert.assertEquals(line, "line " + counter);
				counter++;
			}
		} finally {
			fileIterable.close();
		}
	}

	@Test(dependsOnMethods = "testIterator")
	public void testMultipleIterators() throws FileNotFoundException {
		File file = getResourceFile("File1.txt");
		FileList fileIterable = FileList.openFile(file);
		try {
			int counter = 0;
			for (String line : fileIterable) {
				Assert.assertEquals(line, "line " + counter);
				counter++;
			}
		} finally {
			fileIterable.close();
		}

		int counter = 0;
		for (String line : fileIterable) {
			Assert.assertEquals(line, "line " + counter);
			counter++;
		}
	}

	@Test(dependsOnMethods = "testIterator")
	public void testIteratorAutoClose() throws FileNotFoundException {
		File file = getResourceFile("File1.txt");
		FileList fileIterable = FileList.openFile(file);
		int counter = 0;
		try {
			for (String line : fileIterable) {
				Assert.assertEquals(line, "line " + counter);
				counter++;
			}
			Assert.assertFalse(fileIterable.isFileOpen(), "The file should auto close when fully read.");
		} finally {
			fileIterable.close(); //Close in case there's an error.
		}
	}

	private File getResourceFile(String name) {
		URL resource = this.getClass().getClassLoader().getResource(name);
		assert resource != null : "There was no resource with the name `" + name + "`";
		return new File(resource.getFile());
	}
}
