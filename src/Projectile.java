
public class Projectile extends Circle{

	String [] pose = new String[] {""};
	public int damage;
	public Projectile(double x, double y, double r, int A, int dmg) {
		super(x, y, r, A);
		this.sprite = new Sprite((int)x - (int )r, (int)y- (int )r, (int)r*2, (int)r*2, 
				"images/BULLET", pose, 2, "png", 5);
		this.rect = new Rect((int)x - (int )r, (int)y- (int )r, (int)r, (int)r);
	}
	
	public void shoot(int x, int y) {
		this.x += x;
		this.y += y;
	}

}
