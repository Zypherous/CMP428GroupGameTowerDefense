import java.awt.*;

public class Background
{
	Image image;
	

	public Background(String filename)
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	public void draw(Graphics pen)
	{
		pen.drawImage(image, 0, 0, null);
	}

}