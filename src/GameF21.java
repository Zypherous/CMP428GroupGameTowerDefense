import java.awt.Graphics;

public class GameF21 extends GameBase{
	
	Background space = new Background("images/b_0.jpg");
	Circle c = new Circle(540,360,50,50);
	
	public void initialize() {
		
		
	}	
	
	
	public void inGameLoop(){
		if(pressing[UP])     c.moveForward(10);
		if(pressing[RT])     c.turnRight(3);
		if(pressing[LT])     c.turnLeft(3);
		
		
	}	
	public void paint(Graphics pen){
		space.draw(pen);
		c.draw(pen);
		
	}	
	
	
	
}