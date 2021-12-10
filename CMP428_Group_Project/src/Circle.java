import java.awt.*;

public class Circle
{
	double x;
	double y;
	
	double r;
	
	int A;
	
	double ux;
	double uy;
	
	double Nx;
	double Ny;
	
	
	double vx = 0;
	double vy = 0;
	
	Color fillColor = Color.YELLOW;
	Color drawColor = Color.BLACK;
	
	
	public Circle(double x, double y, double r, int A)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		
		this.A = A;
		
		ux = Lookup.cos[A];
		uy = Lookup.sin[A];
		
		Nx = -uy;
		Ny =  ux;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.setColor(fillColor);
		pen.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		
		pen.setColor(drawColor);
		pen.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));		
		pen.drawLine((int)(x), (int)(y), (int)(x + r * ux), (int)(y + r * uy));
	}
	
	public void setColor(Color c)
	{
		fillColor = c;
	}
	
	
	public double distanceLeftRight(double Px, double Py)
	{
		return (Px - x) * Nx + (Py - y) * Ny;
	}

	public double distanceInFront(double Px, double Py)
	{
		return (Px - x) * ux + (Py - y) * uy;
	}
	
	
	public void turnTowards(Circle c)
	{
		if(distanceLeftRight(c.x, c.y)  <  10)   turnLeft(3);
		
		if(distanceLeftRight(c.x, c.y)  >  10)  turnRight(3);
	}
	
	public void turnTowards(Line l) {
		
		if(distanceLeftRight(l.Ax,l.By) < 50) turnLeft(3);
		if(distanceLeftRight(l.Ax, l.By) >50) turnRight(3);
		
	}
	
	public void turnTowards(Rect r) {
		
		if(distanceLeftRight(r.x,r.y) < 50) turnLeft(3);
		if(distanceLeftRight(r.x, r.y) >50) turnRight(3);
		
	}
	
	public void chase(Circle c)
	{
		
		//if(distanceInFront(c.x, c.y) >= -10)
		{
		   turnTowards(c);
		
		   if(distanceInFront(c.x, c.y) > r + c.r + 200)		moveForward(8);
		}
	}
	
	public void chase(Line l)
	{
		
		//if(distanceInFront(c.x, c.y) >= -10)
		{
		   turnTowards(l);
		
		   if(distanceInFront(l.Ax, l.By) > r +  l.Ax + 200)		moveForward(4);
		   if(distanceInFront(l.Ax, l.By) < r +  l.Ax + 200)		moveForward(4);
		}
	}
	
	public void chase(Rect r)
	{
		
		//if(distanceInFront(c.x, c.y) >= -10)
		{
		   turnTowards(r);
		
		   if(distanceInFront(r.x, r.y) > this.r +  r.x + 200)		moveForward(4);
		   if(distanceInFront(r.x, r.y) < this.r +  r.x + 200)		moveForward(4);
		}
	}
	
	
	public boolean overlaps(Circle c)
	{
		double dx = x - c.x;
		double dy = y - c.y;
		double sr = r + c.r;
		
		double d2 = dx*dx + dy*dy; 
		
		double r2 = sr*sr;
		
		return d2 < r2;
	}
	
	/*
	 * public void pushedBackBy(Circle c) { double dx = x - c.x; double dy = y -
	 * c.y; double sr = c.r + r;
	 * 
	 * double d = Math.sqrt(dx*dx + dy*dy);
	 * 
	 * double p = sr - d;
	 * 
	 * double ux = dx / d; double uy = dy / d;
	 * 
	 * x += p * ux; y += p * uy; }
	 */
	
	public void bumpsInto(Circle c)
	{
		double dx = x - c.x;
		double dy = y - c.y;
		double sr = c.r + r;
		
		double d = Math.sqrt(dx*dx + dy*dy);
		
		double p = sr - d;
		
		double ux = dx / d;
		double uy = dy / d;
		
		x += .5 * p * ux;
		y += .5 * p * uy / 4;
		
		c.x -= .5 * p * ux;
		c.y -= .5 * p * uy;
	
	}
	
	public boolean overlaps(Line L)
	{
		return L.distanceFrom(x, y) - r < 0;   			
	}
	
	public boolean overlaps(Rect r)
	{
		return r.distanceFrom(x, y) - this.r < 0;   			
	}
	
	public void pushedBackBy(Line L)
	{
		double d = L.distanceFrom(x, y);
		
		x += (r - d) * L.Nx;
		y += (r - d) * L.Ny;
	}
	
	public void moveForward(int d)
	{
		x += d * ux;
		y += d * uy;
	}
	
	public void moveBackward(int d)
	{
		x -= d * ux;
		y -= d * uy;
	}
	
	public void move()
	{
		x += vx;
		y += vy;
	}
	
	public void setVelocity(int speed, double ux, double uy)
	{
		this.ux = ux;
		this.uy = uy;
		
		vx = speed * ux;
		vy = speed * uy;		
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void shoot(Circle bullet)
	{
		bullet.setPosition(x + r * ux , y + r * uy);
		
		bullet.setVelocity(30, ux, uy);
	}
	
		
	public void turnLeft(int dA)
	{
		A -= dA;
				
		if(A < 0  )  A += 360;
		
		ux = Lookup.cos[A];
		uy = Lookup.sin[A];
		
		Nx = -uy;
		Ny =  ux;
	}
	
	public void turnRight(int dA)
	{
		A += dA;
		
		if(A > 359)  A -= 360;		
		
		ux = Lookup.cos[A];
		uy = Lookup.sin[A];
		
		Nx = -uy;
		Ny =  ux;
	}
	

}