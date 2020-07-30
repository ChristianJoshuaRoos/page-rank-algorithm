# page-rank-algorithm

I have written a Power Iterator algorithm and a Power Iterator with Teleportation algorithm.  
Each iteratively ranks web pages based on the structure of the associated web graph.  Such 
algorithms are useful tools for determining which websites are frequently visited by people
using the Internet.  The complexity of the algorithm resides in the matrix multiplication
that is used to determine the rankings.

In order for the program to determine the page ranks, an input file must be present in the 
same directory containing an edge list.  Each page in the edge list must be represented by
indices beginning with index 0 and having continuous indices with no breaks--e.g., the first
page is index 0, the next page is index 1, and so on.

In order to compile and run the program, type: 
javac PowerIterator.java
java PowerIterator <name of file>.

In order to compile and run the program with teleportation, type: 
javac PowerIteratorWithTeleportation.java
java PowerIteratorWithTeleportation <name of file>.

The results containing the page ranks should be sent to a file named "output.txt". 
This file will be overwritten each time either PowerIterator or PowerIteratorWithTeleportation is run.  
