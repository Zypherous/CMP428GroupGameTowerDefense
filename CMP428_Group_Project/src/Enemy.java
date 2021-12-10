import java.awt.*;

public class Enemy extends Rect implements Damage, Health
{
	String name;
	int health;
	int damage;
	int points;
	boolean dead = false;
	Image image;
	
	
	public Enemy(int health, int damage, int points, int x, int y, int w, int h, String filename) 
	{
		super(x, y, w, h);
		this.health = health;
		this.damage = damage;
		this.points = points;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		image = Toolkit.getDefaultToolkit().getImage(filename);
		
	}
	
	public void takeDamage(int damage) //health of object gets subtracted by damage of an object
	{
		health -= damage;
	}
	
	public boolean isDead()
	{
		if(health == 0) 
		{
			dead = true;
			return dead;
		} else
			return dead;
			
			
	} 
	
	
	@Override
	public void draw(Graphics pen)
	{
		pen.setColor(Color.RED);
		//pen.drawRect(x, y, w, h);
		pen.drawImage(image, x, y, null);
	}

	public boolean respawned(Line L)
	{
		if(this.x == L.Ax)
			return true;
		else
			return false;
	}
	@Override
	public void setHealth(int health) {
		// TODO Auto-generated method stub
		this.health += health;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}


}
