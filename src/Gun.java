import java.awt.Graphics;
import java.awt.*;

public class Gun extends Circle {

	String[] pose = new String[] {""};
	Projectile [] projectiles = new Projectile[1000];
	GameF21 game;
	private boolean active;
	private int numBullets;
	public Gun(double x, double y, double r, int A, boolean active, GameF21 game) {
		super(x, y, r, A);
		this.game = game;
		this.numBullets = 0;
		this.sprite = new Sprite((int)x-(int)r, (int)y -(int)r/2, (int)r*2, (int)r,
				"images/ART_GUN",
				pose, 2, "png", 10);
		this.active = active;
		
	}

	public void draw(Graphics pen)
	{
		pen.setColor(Color.RED);
		pen.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		pen.drawLine((int)(x), (int)(y), (int)(x + r *2* ux), (int)(y + r *2* uy));
		rect.draw(pen);
		this.sprite.draw(pen);
	}
//	public void rotate(Graphics2D pen, int x) {
//		
//	}
	
	public void turnTowards(Rect e) {

        if(distanceLeftRight(e.x , e.y ) < 1)  turnLeft(6);
        if(distanceLeftRight(e.x , e.y ) > 1)  turnRight(6);

        if(distanceLeftRight(e.w , e.h ) < 1)  turnLeft(6);
        if(distanceLeftRight(e.w , e.h ) > 1)  turnRight(6);


        if(distanceLeftRight(e.x + e.w , e.y + e.h) < 1)  turnLeft(6);
        if(distanceLeftRight(e.x + e.w ,e.y + e.h) > 1)  turnRight(6);
     }

	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean getActive() {
		return this.active;
	}
	public int getNumBullets() {
		return this.numBullets;
	}
	public void setNumBullets(int numBullets) {
		this.numBullets = numBullets;
	}
	public void fire() {
		projectiles[numBullets] = new Projectile(this.x ,
				this.y,
				5,
				this.A, game.projDmg);
		if(numBullets < game.BULLETS) {
			numBullets++;
		}else {
			numBullets = 0;
		}
	}
}
