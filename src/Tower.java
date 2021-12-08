import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.*;

public class Tower extends Rect implements Health  {

	
	int health ;
	boolean dead = false ; 
	int x ;
	int y ;
	int w ;
	int h;
	Image sprite;
	
	public Tower( int health , int x , int y, int w, int h) {
		super(x, y, w, h);
		this.health = health;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sprite = Toolkit.getDefaultToolkit().getImage("images/tower.png");
	}

	
	public int getHealth() {
		return this.health;
	}


	public boolean isDead() {
		
		if(health == 0) dead = true;
		return dead;
	}

	public void draw(Graphics pen) {
		
		pen.drawImage(sprite, x, y, w, h, null);
	}


	
	public void setHealth() {
		
		this.health += health;
	}
	
	
}
