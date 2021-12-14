
public class DarkPinkMon extends Enemy{

	String[] pose = new String[] {""};
	public DarkPinkMon(String name, int health, int damage, int points, boolean dead, double x, double y, double r,int A, int speed) {
		super(name, health, damage, points, dead, x, y, r, A, speed);
		this.sprite = new Sprite((int)x - (int )r, (int)y- (int )r, (int)r*2, (int)r*2, 
				"sprites/pnkmon/darkpnkmon", pose, 2, "jpg", 5);
		this.rect = new Rect((int)x - (int )r, (int)y- (int )r, (int)r, (int)r);
	}

	
}
