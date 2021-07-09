# SAT-sort
Playing around with SAT encodings.

Maven project built on OR-tools, JUnit, and Java 11.

OR-tools set-up : https://developers.google.com/optimization/install

Currently contains a bare-bones sorting algorithm that encodes (OR-tools does the heavy lifting really) a sorting problem on an array, passes it into a CP-SAT solver, and returns a sorted array after decoding the SAT solver's response.
