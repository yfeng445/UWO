
public class TestReverse {
	
	
	public static void main (String[] args) {

		String[] arr1 = new String[] {"atom", "breeze", "clock", "daydream", "energy"};
		ReversibleArray<String> revArr1 = new ReversibleArray<String>(arr1);
		System.out.println(revArr1.toString());
		revArr1.reverse();
		System.out.println(revArr1.toString());
		
		Integer[] arr2 = new Integer[] {11, 22, 33, 44, 55, 66, 77};
		ReversibleArray<Integer> revArr2 = new ReversibleArray<Integer>(arr2);
		System.out.println(revArr2.toString());
		revArr2.reverse();
		System.out.println(revArr2.toString());

		Person p1 = new Person("Jerry", "Seinfeld");
		Person p2 = new Person("Salma", "Hayek");
		Person p3 = new Person("Reese", "Witherspoon");
		Person p4 = new Person("Vince", "Vaughn");
		Person[] arr3 = new Person[] {p1, p2, p3, p4};
		ReversibleArray<Person> revArr3 = new ReversibleArray<Person>(arr3);
		System.out.println(revArr3.toString());
		revArr3.reverse();
		System.out.println(revArr3.toString());
		
	}

}
