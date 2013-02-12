FileIterator
============

An easy way to iterate over the lines of a file in Java.

You can use it just like any other Iterable in java. For example:

    FileIterable fileIterable = FileList.openFile(file);
    try {
        for (String line : fileIterable) {
            System.out.println(line);
        }
    } finally {
        fileIterable.close();
    }

You can also index a specific line. Like this:

    FileIterable fileIterable = FileList.openFile(file);
    String line = fileIterable.get(10);
    System.out.println(line);

Or you can search for strings:

    FileIterable fileIterable = FileList.openFile(file);
    List<String> line = fileIterable.find(new Predicate() {
       public boolean call(String string) {
          return string.contains("Java");
       }
    });

Or you can use the built in predicates in the Predicates class:

    FileIterable fileIterable = FileList.openFile(file);
    Predicate predicate = new Predicates.PatternPredicate(Pattern.compile("(bacon|cheesecake) is (amazing|delicious)"));
    List<String> matches = fileIterable.find(predicate);

Or if you just need one result:

    String firstMatch = fileIterable.findFirst(predicate);

*Note: Calling the find method causes the entire file to be read. However, the findFirst method only causes the file to be read to the first result.*

TODO
====
* Modify the file through changing the elements of the list/iterable.
  * Do global find and replace on the file both with strings and regex. e.g. fileIterable.replace("myStr", "a newStr")
  * Remove/Add individual lines to the files, just as you would with a list.
* A FileIterable that doesn't store it's entire contents in memory.
  Rather it will store pointers or something similar to each new line
  it comes across and just seek to where it needs to be. This would be more ideal for working with larger files that don't fit in memory.
