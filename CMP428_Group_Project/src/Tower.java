import java.awt.*;

public class Tower extends Rect implements Health, Damage
{
	int health;
	boolean destroyed = false;
	Image image;

	public Tower(int health, int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		this.health = health;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public Tower(int health, int x, int y, int w, int h, String filename) 
	{
		super(x, y, w, h);
		this.health = health;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	@Override
	public void setHealth(int health) 
	{
		// TODO Auto-generated method stub
		this.health += health;
	}

	@Override
	public int getHealth() 
	{
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public boolean isDead() 
	{
		if(health <= 0) destroyed = true;
			return destroyed;
	}
	
	public void draw(Graphics pen)
	{
		pen.setColor(Color.DARK_GRAY);
		//pen.drawRect(x, y, w, h);
		if(!isDead())
		{
			pen.drawImage(image, 100, 250, null);
		} else
			pen.drawImage(image, -1000, -1000, null);
	}
	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
			health -= damage;
	}
	
	
}
