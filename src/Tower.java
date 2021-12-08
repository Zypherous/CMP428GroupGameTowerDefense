import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.*;

public class Tower extends Rect implements Health  {

	Gun gun;
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
		this.gun = new Gun(this.x + 64, this.y +128, 50 ,0 );
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
		gun.draw(pen);
	}


	
	public void setHealth() {
		
		this.health += health;
	}
	
	
}
