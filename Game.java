import java.awt.*;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends GameBase 
{
	Timer timer;
	Background grass = new Background("../image/Grass_Field.png");
	Tower castle = new Tower(10, 90, 160, 220, 490, "../image/tower.png");
	Enemy enemy1 = new Enemy(10, 5, 15, 1000, 430, 50, 100, "../image/Rock.png");
	Line respawn = new Line(2000, 100, 2000, 200);
	Enemy[] gang = new Enemy[8];
	int second = 0;
	int secondsToNextWave = 10;
	long currentTime, lastUpdate;
	int current_points = 0;
	int total_points = 0;
	
	
	public void initialize() 
	{
		currentTime = lastUpdate = System.currentTimeMillis();
		timer();
		timer.start();
		
	}	
	
	
	private void timer() 
	{
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				second++;
				if(secondsToNextWave == 0) {
					secondsToNextWave = 11;
				}
				secondsToNextWave--;
			}

			});
		}
		
	


	public void inGameLoop() 
	{
		
		
			
			enemy1.moveLeft(5);
			if(enemy1.isDead()) 
			{
				current_points += enemy1.points;
				total_points += enemy1.points;
			}
		
		
	}	
	
	public void paint(Graphics pen)
	{
		
		grass.draw(pen);
		castle.draw(pen);
		enemy1.draw(pen);
		pen.setColor(Color.BLACK);
		pen.drawString(String.format("Health: %d", castle.health), 64, 64);
		pen.drawString(String.format("Next Wave: %d seconds", secondsToNextWave), 64*12, 64);
		
		
		//respawn.draw(pen);
		if(enemy1.overlaps(castle))
		{
			castle.takeDamage(enemy1.damage);
			enemy1.respawnPoint(respawn);
			
		} 
		if(castle.isDead())
			castle.setPosition(-1000, -1000);
		
		
	}	
	
	
	
}
