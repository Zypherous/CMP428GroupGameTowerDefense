import java.awt.Graphics;

public class Line
{
	int Ax;
	int Ay;
	
	int Bx;
	int By;
	
	
	double Nx;
	double Ny;
	
	double c;
	
	public Line(int Ax, int Ay, int Bx, int By)
	{
		this.Ax = Ax;
		this.Ay = Ay;

		this.Bx = Bx;
		this.By = By;
		
		double vx = Ax - Bx;
		double vy = Ay - By;
		
		double mag = Math.sqrt(vx*vx + vy*vy);
		
		
		Nx = -vy / mag;
		Ny =  vx / mag; 
		
		c = Ax * Nx + Ay * Ny;
	}
	
	public double distanceFrom(double Px, double Py)
	{
		return Px * Nx + Py * Ny - c;
	}

	public void draw(Graphics pen)
	{
		pen.drawLine(Ax, Ay, Bx, By);
	}
	
	public void moveLeft(int dx)
	{
		Ax -= dx;
		Bx -= dx;
	}
	
	public void moveRight(int dx)
	{
		Ax += dx;
		Bx += dx;
	}
	
	public void moveUp(int dy)
	{
		Ay -= dy;
		By -= dy;
	}
	
	public void moveDown(int dy)
	{
		Ay += dy;
		By += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
		Ax += dx;
		Ay += dy;
		
		Bx += dx;
		By += dy;
	}


}