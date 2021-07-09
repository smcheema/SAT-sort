# SAT-sort
Playing around with SAT encodings using OR-tools.

Maven project build using on OR-tools, JUnit, and Java 11.

OR-tools set-up : https://developers.google.com/optimization/install

Bare-bones sorting algorithm that encodes (OR-tools does the heavy lifting really) an array, passes it into a CP-SAT solver, and returns a sorted array after decoding 
the SAT solver response.
