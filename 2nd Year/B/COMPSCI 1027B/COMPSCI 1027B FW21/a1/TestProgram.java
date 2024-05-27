
public class TestProgram {

	public static void main(String[] args) {
		String[] files = new String[] {
			"cities1.txt", "cities2.txt", "cities3.txt", "cities4.txt", "cities5.txt"
		};
		int[] sizes = new int [] {
			21, 15, 10, 15, 36
		};
		String[] solns = new String[] {
			"\n   36.88\n  441.59  441.44\n  420.49  421.01   22.20\n  396.09  399.24   57.70   37.20\n   96.54  117.34  531.75  510.19  483.50\n  179.23  176.82  264.62  244.25  223.79  273.31\n",
			"\n  551.88\n  377.00  191.27\n  457.96   94.25  111.80\n   98.09  455.81  278.93  362.35\n  134.53  652.92  466.59  561.43  205.83\n",
			"\n  269.11\n   91.35  257.26\n  802.24  718.88  712.53\n  589.42  480.42  504.12  239.15\n",
			"\n  372.31\n  420.49   64.41\n   98.05  278.93  324.04\n  658.98  306.54  245.60  561.06\n  111.33  466.59  519.70  205.83  762.13\n",
			"\n  512.66\n  552.89   42.05\n  372.31  157.18  191.27\n  420.49   95.52  133.24   64.41\n   98.05  415.28  455.81  278.93  324.04\n  396.09  129.03  163.60   28.16   37.20  301.38\n   96.54  603.83  643.29  458.33  510.19  192.94  483.50\n  179.23  334.43  375.20  203.30  244.25   81.22  223.79  273.31\n"
		};
		double[] firsts = new double[] {
			36.87817782917155, 551.881327823292, 269.1059270993487, 372.3130403303113, 512.6646077115134
		};

		for (int f = 0; f < files.length; f++) {
			String file = files[f];
			
			Program prog = new Program(file, false);
			prog.compareDistances();
			CompressedArray ca = prog.getArray();

			boolean t1 = ca.getLength() == sizes[f];
			boolean t2 = ca.toString().equals(solns[f]);
			boolean t3 = ca.getElement(0) == firsts[f];

			if (t1 && t2 && t3) {
				System.out.println("Test " + (f+1) + " Passed");
			} else {
				System.out.println("Test " + (f+1) + " Failed");
			}
		}
		
	}

}
