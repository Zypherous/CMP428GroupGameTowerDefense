import java.awt.Graphics;

public class Enemy extends Circle implements Health,Damage  {

	String name;
	int health;
	int damage;
	int points;
	boolean dead;
	
	public Enemy(String name, int health, int damage, int points, boolean dead ,double x, double y, double r, int A) {
		super(x,y,r,A);
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.points = points;
		this.dead   = dead;
		
		this.x = x;
		this.y = y;
		this.r = r;
		this.A = A;
		rect = new Rect((int)x - (int )r, (int)y - (int )r, (int)r*2, (int)r*2);
	}



	public void setHealth() {
		
		this.health += health;
		
	}

	
	public int getHealth() {
		
		return this.health;
	}

	
	public boolean isDead() {
		
		if(health == 0) dead = true;
		
		return dead;
	}
	

	
	public int getDamage() {
		
		return this.damage;
	}


	public void draw(Graphics pen)
	{
//		pen.setColor(fillColor);
//		pen.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		
		pen.setColor(drawColor);
		pen.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		pen.drawLine((int)(x), (int)(y), (int)(x + r * ux), (int)(y + r * uy));
		rect.draw(pen);
		this.sprite.draw(pen);
	}




	@Override
	public void takeDamage(int dmg) {
		health -= dmg;
		if(health <= 0) {
			this.dead = true;
		}
	}



	
	
}
