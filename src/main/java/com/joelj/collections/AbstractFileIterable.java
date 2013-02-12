package com.joelj.collections;

import java.io.IOException;
import java.util.Scanner;

/**
 * User: Joel Johnson
 * Date: 2/11/13
 * Time: 8:23 PM
 */
abstract class AbstractFileIterable implements FileIterable {
	/*package*/ abstract Scanner getScanner();
	/*package*/ abstract int getCurrentLine();
	public abstract boolean isFileOpen();
}
