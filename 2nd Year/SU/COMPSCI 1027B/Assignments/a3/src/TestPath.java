
public class TestPath {

	public static void main(String[] args) {
		StartSearch s1 = new StartSearch("map1.txt");
		String r1 = s1.findPath();
		//System.out.println(r1);
		if (r1.equals("2-7-8-7-6-11-16-17-18-E3")) {
			System.out.println("Test 1 Passed");
		} else {
			System.out.println("Test 1 Failed");
		}

		// ------------------------------------------------
		
		StartSearch s2 = new StartSearch("map2.txt");
		String r2 = s2.findPath();
		
		if (r2.equals("4-3-2-1-0-5-6-11-16-15-20-15-16-11-6-5-0-1-2-3-4-E10")) {
			System.out.println("Test 2 Passed");
		} else {
			System.out.println("Test 2 Failed");
		}

		// ------------------------------------------------

		StartSearch s3 = new StartSearch("map3.txt");
		String r3 = s3.findPath();
		
		if (r3.equals("4-3-2-1-0-5-6-11-16-15-20-21-22-23-24-19-E0")) {
			System.out.println("Test 3 Passed");
		} else {
			System.out.println("Test 3 Failed");
		}
		
		// ------------------------------------------------

		StartSearch s4 = new StartSearch("map4.txt");
		String r4 = s4.findPath();
		
		if (r4.equals("1-6-7-6-11-6-1-E10")) {
			System.out.println("Test 4 Passed");
		} else {
			System.out.println("Test 4 Failed");
		}
		
		// ------------------------------------------------

		StartSearch s5 = new StartSearch("map5.txt");
		String r5 = s5.findPath();
		
		if (r5.equals("1-6-7-8-7-12-7-6-11-16-17-18-17-16-11-6-1-E10")) {
			System.out.println("Test 5 Passed");
		} else {
			System.out.println("Test 5 Failed");
		}
		
		// ------------------------------------------------

		StartSearch s6 = new StartSearch("map6.txt");
		String r6 = s6.findPath();
		
		if (r6.equals("10-11-12-7-12-13-12-17-E6")) {
			System.out.println("Test 6 Passed");
		} else {
			System.out.println("Test 6 Failed");
		}
		
		// ------------------------------------------------

		StartSearch s7 = new StartSearch("map7.txt");
		String r7 = s7.findPath();
		
		if (r7.equals("0-5-10-15-20-21-22-23-E2")) {
			System.out.println("Test 7 Passed");
		} else {
			System.out.println("Test 7 Failed");
		}
		
		// ------------------------------------------------
		
		StartSearch s8 = new StartSearch("map8.txt");
		String r8 = s8.findPath();
		//System.out.println(r8);
		//System.out.println("1-8-15-22-29-36-43-44-45-38-31-24-17-10-3-4-5-12-19-26-33-40-33-26-19-12-5-4-3-10-17-24-31-38-45-44-43-36-29-22-15-8-1-E10");
		if (r8.equals("1-8-15-22-29-36-43-44-45-38-31-24-17-10-3-4-5-12-19-26-33-40-33-26-19-12-5-4-3-10-17-24-31-38-45-44-43-36-29-22-15-8-1-E10")) {
			System.out.println("Test 8 Passed");
		} else {
			System.out.println("Test 8 Failed");
		}
		
		// ------------------------------------------------
		
		StartSearch s9 = new StartSearch("map9.txt");
		String r9 = s9.findPath();
		
		if (r9.equals("74-75-76-77-78-69-60-59-58-57-58-59-60-51-42-41-40-39-40-41-42-33-24-23-22-21-22-23-24-15-6-5-4-3-E0")) {
			System.out.println("Test 9 Passed");
		} else {
			System.out.println("Test 9 Failed");
		}
		
		// ------------------------------------------------
		
		StartSearch s10 = new StartSearch("map10.txt");
		String r10 = s10.findPath();
		
		if (r10.equals("112-97-82-81-80-79-64-49-34-35-36-35-34-49-64-63-62-61-60-61-46-61-62-63-64-79-80-81-82-97-112-113-114-99-84-85-86"
				+ "-87-72-57-58-57-72-87-88-89-88-87-102-117-132-117-102-87-86-85-84-99-114-113-112-127-142-157-172-187-188-189-190-175-"
				+ "160-175-176-175-190-189-188-187-186-185-200-215-200-185-186-187-172-157-142-127-112-111-110-125-140-155-154-153-138-"
				+ "153-152-151-166-151-152-153-154-155-140-125-110-109-108-107-106-105-90-75-90-105-106-107-108-109-110-111-112-E10")) {
			System.out.println("Test 10 Passed");
		} else {
			System.out.println("Test 10 Failed");
		}

	}

}
