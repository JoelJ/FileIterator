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
I still want to add a few more options I often wish I had easy access to in Java. Such as:
* set and replace.
