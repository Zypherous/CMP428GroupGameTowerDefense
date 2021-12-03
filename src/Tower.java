import java.awt.Graphics;

public class Tower extends Rect implements Health  {

	
	int health ;
	boolean dead = false ; 
	int x ;
	int y ;
	int w ;
	int h;
	
	public Tower( int health , int x , int y, int w, int h) {
		super(x, y, w, h);
		this.health = health;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
	}

	
	public int getHealth() {
		return this.health;
	}


	public boolean isDead() {
		
		if(health == 0) dead = true;
		return dead;
	}

	public void draw(Graphics pen) {
		
		pen.drawRect(x, y, w, h);
	}


	
	public void setHealth() {
		
		this.health += health;
	}
	
	
}
