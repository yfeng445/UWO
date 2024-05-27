import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestSearch {

	private static int[] readTests(String[] args, int numTests) {
		int[] tests = new int[numTests];
		int value;

		if (args.length == 0)
			for (int i = 0; i < numTests; ++i)
				tests[i] = i + 1;
		else
			for (int i = 0; i < args.length; ++i) {
				value = Integer.parseInt(args[i]);
				if (value >= 1 && value <= numTests)
					tests[value - 1] = value;
				else
					System.out.println("ERROR: Test " + value + "does not exist");
			}
		return tests;
	}

	public static void main(String[] args) throws Exception {
		boolean testPassed;
		int[] tests = readTests(args, 7);

		String[] param = { "xmap1.txt", "5" }; // Name starts with x to avoid displaying map
		String correct;

		System.out.println("\nTESTS FOR CLASS StartSearch");
		System.out.println("==============================\n");

		
		// This test checks whether the arrow prefers a straight path over the preference to go north or up.
		if (tests[0] == 1) {

			StartSearch.main(param);
			correct = "push0push2push3push4push5pop5pop4pop3pop2pop0push0pop0";

			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================");
				System.out.println("TEST 1 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================");
				System.out.println("TEST 1 FAILED");
				System.out.println("==============================\n");
				
			} //end else
				

		} // end if
		
		
		
		
		// This test checks basic multiple arrow firing. The fourth target should not be hit.
		param[0] = "xmap2.txt";
		param[1] = "8";

		if (tests[1] == 2) {

			StartSearch.main(param);
			correct = "push0push8push3push4push5pop5pop4pop3pop8pop0push0push13push14push10push9pop9pop10push19push24pop24pop19pop14pop13pop0push0push17push22push23push18pop18pop23push21push20pop20pop21pop22pop17pop0";
			
			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 2 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 2 FAILED");
				System.out.println("==============================\n");
				
			} //end else
		} // end if
		
		param[0] = "xmap3.txt";
		param[1] = "22";
		
		// This test checks to see if inertia is implemented. If not, the arrow should be able to find a path to the target.

		if (tests[2] == 3) {

			StartSearch.main(param);
			correct = "push0push1push2push3push8push13push18push23pop23pop18pop13pop8pop3pop2pop1pop0";
			
			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 3 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 3 FAILED");
				System.out.println("==============================\n");
				
			} //end else

		} // end if
		
		// This test checks to see if the arrow stops when it hits a target, or if it passes through.
		
		String[] param2 = { "xmap4.txt" };

		if (tests[3] == 4) {

			StartSearch.main(param2);
			correct = "push0push6push12push19push26push33pop33push25pop25pop26pop19pop12pop6pop0push0push11push10push17push24push31push30push29pop29pop30pop31pop24pop17pop10pop11pop0push0push5push4push3push2push1push7pop7pop1pop2pop3pop4pop5pop0push0pop0";

			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 4 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 4 FAILED");
				System.out.println("==============================\n");
				
			} //end else

		} // end if
		
		// This test checks to see whether the algorithm can handle a larger map.
		
		param[0] = "xmap5.txt";
		param[1] = "250";

		if (tests[4] == 5) {

			StartSearch.main(param);
			correct = "push0push27push16push7push6push5push4push3push2pop2pop3pop4pop5pop6pop7pop16pop27pop0push0push37push49push63push76push83push93push104push115pop115pop104pop93pop83pop76pop63pop49pop37pop0push0push48push62push75push82push92push103pop103pop92pop82pop75pop62pop48pop0push0push36push35push34push33push44push45push46pop46pop45pop44pop33pop34pop35pop36pop";

			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 5 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 5 FAILED");
				System.out.println("==============================\n");
				
			} //end else
			
		} // end if
		
		// This tests what happens when two targets are next to each other and multiple arrows to fire.
		
		param[0] = "xmap6.txt";
		param[1] = "7";

		if (tests[5] == 6) {

			StartSearch.main(param);
			correct = "push0push1pop1pop0push0pop0push0pop0";

			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 6 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 6 FAILED");
				System.out.println("==============================\n");
				
			} //end else

		} // end if
		
		// This tests what happens when there are no valid paths and multiple arrows to fire.
		
		param[0] = "xmap7.txt";
		param[1] = "10";

		if (tests[6] == 7) {

			StartSearch.main(param);
			correct = "push0pop0push0pop0push0pop0push0pop0push0pop0";
			
			if (ArrayStack.sequence.equals(correct)) {

				testPassed = true;

			} // end if

			else {

				System.out.println("SequenceL: " + ArrayStack.sequence.length() + ", CorrectL: " + correct.length());
				System.out.println(ArrayStack.sequence);
				testPassed = false;

			} // end else

			if (testPassed) {
				
				System.out.println("==============================\n");
				System.out.println("TEST 7 PASSED");
				System.out.println("==============================\n");
				
			} //end if

			else {
				
				System.out.println("==============================\n");
				System.out.println("TEST 7 FAILED");
				System.out.println("==============================\n");
				
			} //end else

		} // end if

		System.exit(0);

	} // end main

} // end TestSearch (class)