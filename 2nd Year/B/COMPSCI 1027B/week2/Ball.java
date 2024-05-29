
public class Ball {
	
	private String colour;
	private double radius;
	private double x, y, z;
	private String name;
	private double elasticity;
	
	public Ball (String colour, double radius, double x, double y, double z, String name, double elasticity) {
		this.colour = colour;
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
		this.elasticity = elasticity;
	}
	
	public Ball () {
		this.colour = "black";
		this.radius = 5.0;
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
		this.name = "Generic ball";
		this.elasticity = 1.0;
	}
	
	public Ball (String colour, double elasticity) {
		this.colour = colour;
		this.radius = 5.0;
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
		this.name = "Generic ball";
		this.elasticity = elasticity;
	}

}
