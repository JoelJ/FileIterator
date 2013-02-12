package com.joelj.collections;

import java.io.File;
import java.net.URL;

/**
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 9:06 PM
 */
public class ResourceFiles {
	public static File getResourceFile(String name) {
		URL resource = ResourceFiles.class.getClassLoader().getResource(name);
		assert resource != null : "There was no resource with the name `" + name + "`";
		return new File(resource.getFile());
	}
}
