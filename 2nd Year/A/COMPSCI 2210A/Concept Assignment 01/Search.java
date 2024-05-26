public class Search {

/* This class contains implementations of 3 algorithms for solving
   the search problem. The complexities of the algorithms are O(n)
   O(n*n), and O(n!).                                             */

    private static int [] A; /* Auxiliary array for the FactorialSearch algorithm */
    private static final int SCALE = 1000;       /* scaling factor for the values to be stored
                                                    in the input array */
    private static final int iterations = 1000;  /* For linear search and quadratic search the
                                                    algorithms are executed 1000 times to get
                                                    a better estimation of their running times */

    /* Linear time algorithm for solving the search problem */
    private static int LinearSearch (int[] L, int n, int x) {
    /* Input: Array L of size n and value x.
       Output: Position of x in L, if x is in L; -1 if x is not in L */

	int i = 0;
	while ((i < n) && (L[i] != x)) ++i;
	if (i == n) return -1;
	else return i;
    }

    /* Quadratic time algorithm for solving the search problem */
    private static int QuadraticSearch (int[] L, int n, int x) {
    /* Input: Array L of size n and value x.
       Output: Position of x in L, if x is in L; -1 if x is not in L */

	int i;
	for (int j = 0; j < n; ++j) {
	    i = 0; 
	    while ((i <= j) && (L[i] != x)) ++i;
	    if ((i <= j) && (L[i] == x)) return i;
	}
	return -1;
    }

    /* O(n!) time algorithm for solving the search problem */
    private static int FactorialSearch (int[] L, int n, int x) {
    /* Input: Array L of size n and value x.
       Output: Position of x in L, if x is in L; -1 if x is not in L */

	for (int i = 0; i < n; ++i) A[i] = -1; // Clear array A
	return PermuteSearch(0,L,n,x);
    }


    /* Compute all possible permutations of the values in L and for
       each permutation check whether the first value is equal to x */
    private static int PermuteSearch (int p, int[] L, int n, int x) {
    /* Input: Value p, array L of size n and value x.
       Output: Position of x in L, if x is in L; -1 if x is not in L */
	int position;
	int j;

	if (p == n) {
	    if (L[A[0]] == x) return A[0];
	    else return -1;
	}
	else {
	    for (int i = 0; i < n; ++i) {
		for (j = 0; j < p; ++j)
		    if (A[j] == i) break;
		if (j == p) {
		    A[p] = i;
		    position = PermuteSearch(p+1,L,n,x);
		    if (position != -1) return position;
		}
	    }
	    A[p] = -1;
	    return -1;
	}
    }

    /* Runs the above search algorithms on worst-case random input. This
       method also prints the running time of each algorithm. */
    public static void main (String[] args) {
	int[] L;             /* Input array */
	int n;               /* Size of the input array */
	int x;               /* Value that algorithms need to look for in L */

	long startTime;      /* Auxiliary variables to measure running time */
	long runningTime;
	int result;

	/* Initialize n, array L, and auxiliary array A */
	n = 10;             /* Size of the input */
	L = new int[n];
	A = new int[n];

	/* Fill out array L with random integer values */
	for (int i = 0; i < n; ++i) 
	    L[i] = (int)Math.round(Math.random()*n*SCALE);

	x = n*SCALE+1; // This value of x is not in the array L, hence 
	               // the above search algorithms will exhibit their 
	               // worst-case time complexities with this input.

	System.out.println("Size of the input: "+n);

	/* Average running time over 1000 executions of the LinearSearch algorithm */
	startTime = System.nanoTime();
	for (int i = 0; i < iterations; ++i) 
	    result = LinearSearch(L,n,x);
	runningTime = (System.nanoTime()-startTime)/iterations;
	System.out.println("Running time of LinearSearch: "+runningTime+" ns");

	/* Average running time over 1000 executions of the QuadraticSearch algorithm */
	startTime = System.nanoTime();
	for (int i = 0; i < iterations; ++i) 
	    result = QuadraticSearch(L,n,x);
	runningTime = (System.nanoTime() - startTime)/iterations;
	System.out.println("Running time of QuadraticSearch: "+runningTime+" ns");

	/* Running time of a single execution of the FactorialSearch algorithm */
	startTime = System.nanoTime();
        result = FactorialSearch(L,n,x);
	runningTime = System.nanoTime() - startTime;
	System.out.println("Running time of FactorialSearch: "+runningTime+" ns");
    }

}
