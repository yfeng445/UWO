
public class Product {
	
	private String name;
	private String code;
	private double cost;
	private static double tax = 0.13;
	
	public Product (String prodName, String prodCode, double prodCost) {
		name = prodName;
		code = prodCode;
		cost = prodCost;
	}
	
	public String getName () {
		return name;
	}
	
	public String getCode () {
		return code;
	}
	
	public double getCost () {
		return cost;
	}
	
	public static double getTax () {
		return tax;
	}

}
