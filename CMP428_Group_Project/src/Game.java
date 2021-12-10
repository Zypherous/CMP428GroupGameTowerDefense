import java.awt.*;

public class Game extends GameBase
{
	Background grass = new Background("../image/Grass_Field.png");
	Tower castle = new Tower(10, 108, 256, 175, 360, "../image/tower.png");
	Enemy enemy1 = new Enemy(10, 5, 15, 1000, 430, 50, 100, "../image/Rock.png");
	Line respawn = new Line(2000, 100, 2000, 200);
	Enemy[] gang = new Enemy[8];
	int current_points = 0;
	int total_points = 0;
	
	
	public void initialize() {
		
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
