# page-rank-algorithm

In order for the program to determine the page ranks, an input file must be present in the 
same directory containing an edge list.  Each page in the edge list must be represented by
indices beginning with index 0 and having continuous indices with no breaks--e.g., the first
page is index 0, the next page is index 1, and so on.

In order to compile and run the program, type: 
javac PowerIterator.java
java PowerIterator <name of file>.

The results containing the page ranks should be sent to a file named "output.txt". 
This file will be overwritten each time PowerIterator is run.  
