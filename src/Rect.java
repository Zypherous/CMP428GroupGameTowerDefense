import java.awt.*;

// Class to represent Axis-Aligned Rectangles


public class Rect
{
//	int x;
//	int y;
	
	double x;
	double y;
	
//	int w;
//	int h;
	
	double h;
	double w;
	
	Color color = Color.RED;
	
	Image image;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	
	public Rect(int x, int y, int w, int h, Color color)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
	    setColor(color);
	}

	public Rect(double x,
				double y,
				double w,
				double h,
				String filename)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
		image = Toolkit.getDefaultToolkit().getImage(filename);
		
	}
	public void setBounds(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	
	public void moveLeft(int dx)
	{
		x -= dx;
	}
	
	public void moveRight(int dx)
	{
		x += dx;
	}
	
	public void moveUp(int dy)
	{
		y -= dy;
	}
	
	public void moveDown(int dy)
	{
		y += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		h += dh;
	}
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y     <= r.y + r.h);
	}
	
	public boolean contains(int mx, int my)
	{
		return ( mx > x   ) &&   
			   ( mx < x+w ) && 
			   ( my > y   ) && 
			   ( my < y+h );
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
//	public int respawnPoint(Rect r) {
//		
//		return this.x = r.x;
//	}
	
	public void draw(Graphics pen)
	{
		pen.setColor(color);
		
		pen.drawRect((int)x,(int) y,(int) w,(int) h);
		
		pen.drawImage(image,(int) this.x, (int)this.y, (int)this.w, (int)this.h,  null);
	}
}