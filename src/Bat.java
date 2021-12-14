
public class Bat extends Enemy{

	String[] pose = new String[] {"_Fly_00"};
	public Bat(String name, int health, int damage, int points, boolean dead, double x, double y, double r, int A, int speed) {
		super(name, health, damage, points, dead, x, y, r, A, speed);
		this.sprite = new Sprite((int)x - (int )r, (int)y- (int )r, (int)r*2, (int)r*2, 
				"sprites/bat/__Bat02", pose, 8, "png", 5);
	}

}
