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
	
   public void takeDamage() {
		
		
	}

	
	public int getDamage() {
		
		return this.damage;
	}


	public void draw(Graphics pen)
	{
		pen.setColor(fillColor);
		pen.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		
		pen.setColor(drawColor);
		pen.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		pen.drawLine((int)(x), (int)(y), (int)(x + r * ux), (int)(y + r * uy));
	}
	
}
