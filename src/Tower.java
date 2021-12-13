import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.*;

public class Tower extends Rect implements Health  {

	Gun gun;
	Gun gun2;
	Gun gun3;
	Gun [] guns;
	int health ;
	boolean dead = false ; 
	int x ;
	int y ;
	int w ;
	int h;
	Image sprite;
	int numGuns = 1;
	
	public Tower( int health , int x , int y, int w, int h, GameF21 game) {
		super(x, y, w, h);
		this.health = health;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.game = game;
		this.sprite = Toolkit.getDefaultToolkit().getImage("images/tower.png");
		this.gun = new Gun(this.x + 64, this.y +128, 50 ,0, true , game);
		this.gun2 = new Gun (this.x + 64, this.y +64, 50 ,0, false, game);
		this.gun3 = new Gun (this.x + 64, this.y +128, 50 ,0, false, game);
		this.guns = new Gun[] {gun, gun2, gun3};
		System.out.print(guns[0].getActive());
	}

	
	public int getHealth() {
		return this.health;
	}


	public boolean isDead() {
		
		if(health <= 0) {
			this.health = 0;
			dead = true;		
			}
		return dead;
	}

	public void draw(Graphics pen) {
		pen.drawImage(sprite, x, y, w, h, null);
		for(int i = 0; i < numGuns; i++) {
			if(guns[i].getActive()) {
				guns[i].draw(pen);
			}
		}
		
	}


	
	public void setHealth() {
		
		this.health += health;
	}
	
	public void setGunActive(boolean active, int i) {
		this.guns[i].setActive(active);
	}
	
	public void destroy() {
		this.sprite = Toolkit.getDefaultToolkit().getImage("images/towerdead.png");
		for(int i = 0; i < numGuns; i++) {
			guns[i].setActive(false);
		}
	}
}
