import java.awt.*;

public class Animation
{
	Image[] image;
	
	int delay;
	
	final int duration;
	
	int current = 1;
	
	public Animation(String name, int count, String filetype, int delay)
	{
		image  = new Image[count];
		

		for(int i = 0; i < count; i++)
			
			image[i] = Toolkit.getDefaultToolkit().getImage(name + i + "." + filetype);
		
		
		this.delay = delay;
		
		duration   = delay;
	}
	
	
	public Image currentImage()
	{
		delay--;
		
		if(delay == 0)
		{
			if(current < image.length-1)  current++;
			
			else                          current = 1;
			
			delay = duration;
		}
		
		return image[current];
	}
	
	
	public Image stillImage()
	{
		return image[0];
	}

}