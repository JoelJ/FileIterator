FileIterator
============

An easy way to iterate over the lines of a file in Java.

You can use it just like any other Iterable in java. For example:

    FileIterable fileIterable = FileIterable.openFile(file);
    try {
        for (String line : fileIterable) {
            System.out.println(line);
        }
    } finally {
        fileIterable.close();
    }

You can also index a specific line. Like this:

   FileIterable fileIterable = FileIterable.openFile(file);
   String line = fileIterable.get(10);
   System.out.println(line);

TODO
====
I still want to add a few more options I often wish I had easy access to in Java. Such as:
* grep and other find methods.
* set and replace.
