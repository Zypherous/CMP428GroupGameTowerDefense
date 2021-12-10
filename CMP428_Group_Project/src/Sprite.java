import java.awt.Graphics;

public class Sprite
{
	// Position P
	double x;
	double y;
	
	// Velocity V
	double vx = 0;
	double vy = 0;
	
	// Accerleration a
	double ax = 0;
	double ay = gravity;
	
	
	int w;
	int h;
	
	
	final static double gravity = 0.3; 
	
	
	
	
	Animation[] animation;
	
	boolean moving = false;
	
	final int UP = 0;
	final int DN = 1;
	final int LT = 2;
	final int RT = 3;
	
	int action = DN;
	
	boolean highlighted = false;
	boolean selected    = false;
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype, int delay)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		animation = new Animation[pose.length];
		
		for(int i = 0; i < animation.length; i++)
			
			animation[i] = new Animation(name + pose[i], count, filetype, delay);
	}
	
	
	
	public void draw(Graphics pen)
	{
   	   if(moving)
	
		   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
		   
	   else

		   pen.drawImage(animation[action].stillImage(), (int)x, (int)y, null);
	   
   	   if(highlighted)   pen.drawRect((int)x, (int)y, w, h);
   	   
	   moving = false;
	}
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setAccelleration(double ax, double ay)
	{
		this.ax = ax;
		this.ay = ay;
	}
	
	public void jump()
	{
		vy = -8;
	}
	
	public void move()
	{
		// Moved based on current velocity
		x += vx;  
		y += vy;
		
		// Adjust Velocity based on current acceleration
		vx += ax;
		vy += ay;
	}
	
	
	public void moveUp(int dy)
	{
		y -= dy;
		
		moving = true;
		
		action = UP;
	}

	public void moveDown(int dy)
	{
		y += dy;
		
		moving = true;
		
		action = DN;
	}

	public void moveLeft(int dx)
	{
		x -= dx;
		
		moving = true;
		
		action = LT;
	}

	public void moveRight(int dx)
	{
		x += dx;
		
		moving = true;
		
		action = RT;
	}

	public void goLeft(int dx)
	{
		vx = -dx;
		
		moving = true;
		
		action = LT;
	}

	public void goRight(int dx)
	{
		vx = +dx;
		
		moving = true;
		
		action = RT;
	}

	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y     <= r.y + r.h);
	}
	
	
	public void pushUpFrom(Rect platform)
	{
		y -= y + h  - platform.y;
		
		vx = 0;
		vy = 0;
	}
	
	public boolean contains(int mx, int my)
	{
		return ( mx > x   ) &&   
			   ( mx < x+w ) && 
			   ( my > y   ) && 
			   ( my < y+h );
	}
	
	public void highlight()
	{
		highlighted = true;
	}
	
	public void dehighlight()
	{
		highlighted = false;
	}
	
	public void select()
	{
		selected = true;
	}
	
	public void deselect()
	{
		selected = false;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
}
