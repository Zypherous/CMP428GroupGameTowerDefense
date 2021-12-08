import java.awt.Font;
import java.awt.*;
import java.awt.Graphics;
import java.util.Random;

public class GameF21 extends GameBase{
	
	Background space = new Background("images/b_0.jpg");
	Circle c = new Circle(540,360,50,50);
	Tower t = new Tower(100, 64,5* 64, 239/2, 486/2);
	Enemy [] enemies = new Enemy[5];
	Line l = new Line(64,400,64,500);
	Random rand;
	int health = 100;
	
	public void initialize() {
		rand = new Random();
		for(int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy(
					"bat",
					10,
					2,
					5,
					false,
					1280,
					rand.nextInt(600)%64 * 10,
					50,
					50
					);
		}
		
	}	
	
	
	public void inGameLoop(){
		if(pressing[UP])     c.moveForward(10);
		if(pressing[RT])     c.turnRight(3);
		if(pressing[LT])     c.turnLeft(3);
		for(int i =0; i < enemies.length;i++) {
			enemies[i].chase(l, rand.nextInt(2)+1);
		}
		for(int i = 0; i < enemies.length; i++) {
			if(enemies[i].rect.overlaps(t)) {		
				enemies[i] = new Enemy(
						"bat",
						10,
						2,
						5,
						false,
						1280,
						rand.nextInt(1000)%64 * 15,
						50,
						50
						);
				health -= enemies[i].damage;
				//System.out.println(String.format("RECT[%d] x: %d, y: %d",i, rect[i].getX(),rect[i].getY()));
			}
			
		}
	}	
	public void paint(Graphics pen){
		Font font = new Font("Serif", Font.PLAIN, 32);
		pen.setFont(font);
		space.draw(pen);
		
		t.draw(pen);
		for(int i =0; i < enemies.length;i++) {
			enemies[i].draw(pen);
		}
		pen.setColor(Color.GREEN);
		pen.drawString(String.format("Health: %d", health), 64, 64);
	}	
	
	
	
}