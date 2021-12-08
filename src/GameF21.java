import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class GameF21 extends GameBase{
	
	Timer timer;
	Background space = new Background("images/b_0.jpg");
	Circle c = new Circle(540,360,50,50);
	Tower t = new Tower(100, 64,7* 64, 239/2, 486/2);
	Enemy [] enemies = new Enemy[5];
	Line l = new Line(64,7*64,64,9*64);
	Random rand;
	Projectile [] projectiles = new Projectile[1000];
	static final int BULLETS = 1000;
	int numBullets = 0;
	int health = 100;
	int second = 0;
	int shotDelay = 10;
	long currentTime, lastUpdate;
	int points = 0;
	int enemiesKilled = 0;
	
//	Gun TEST = new Gun (64, 128, 50, 0);
	
	public void initialize() {
		currentTime = lastUpdate = System.currentTimeMillis();
		rand = new Random();
		for(int i = 0; i < enemies.length; i++) {
			newEnemy(i);
		}
		timer();
		timer.start();
	}	
	
	
	public void inGameLoop(){
//		if(pressing[UP])     c.moveForward(10);
		if(pressing[RT])     t.gun.turnRight(3);
		if(pressing[LT])     t.gun.turnLeft(3);
		
		if(pressing[SPACE] && currentTime - lastUpdate > (shotDelay*10)) {
			lastUpdate = currentTime;
			if(numBullets < BULLETS) {
				
				projectiles[numBullets] = new Projectile(t.gun.x ,
						t.y + (int)t.gun.r *2 + 20,
						5,
						t.gun.A, 1);
				
				numBullets++;
			}else {
				numBullets = 0;
			}
		}
		for(int i = 0; i < numBullets; i++) {
			projectiles[i].moveForward(15);
			for(int j = 0; j < enemies.length;j++) {
				if(projectiles[i].rect.overlaps(enemies[j].rect)) {
					enemies[j].health--;
					if(enemies[j].getHealth()<= 0) {
						enemies[j].takeDamage(projectiles[i].damage);
					}
					if(enemies[j].isDead()) {
						points += enemies[j].points;
						newEnemy(j);
						enemiesKilled++;
						
					}
				}
			}
		}
		for(int i =0; i < enemies.length;i++) {
			if(enemies[i].name.equals("bat")){
				enemies[i].chase(l, rand.nextInt(11-5)+5);
			}else {
				enemies[i].chase(l,rand.nextInt(4-1)+1);
			}
		}
		for(int i = 0; i < enemies.length; i++) {
			if(enemies[i].rect.overlaps(t)) {		
				newEnemy(i);
				health -= enemies[i].getDamage();
				t.gun.turnTowards(enemies[i].rect);
				//System.out.println(String.format("RECT[%d] x: %d, y: %d",i, rect[i].getX(),rect[i].getY()));
			}
			
		}
		currentTime = System.currentTimeMillis();
	}	
	public void paint(Graphics pen){
		Font font = new Font("Serif", Font.PLAIN, 32);
		pen.setFont(font);
		space.draw(pen);
		for(int i = 0; i < numBullets; i++) {
			projectiles[i].draw(pen);
		}
		t.draw(pen);
		for(int i =0; i < enemies.length;i++) {
			enemies[i].draw(pen);
		}
		
		pen.setColor(Color.GREEN);
		pen.drawString(String.format("Health: %d", health), 64, 64);
		pen.drawString(String.format("Time: %d seconds", second), 64*13, 64);
		pen.drawString(String.format("Killed: %d    Points: %d", enemiesKilled, points), 64*7, 64);
//		TEST.draw(pen);
	}	
	
	public void timer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
			}

		});
	}
	
	public void newEnemy(int i) {
		int nextEnemy = rand.nextInt(2);
		if( nextEnemy==1) {
			enemies[i] = new Bat(
				"bat",
				10,
				2,
				2,
				false,
				1200,
				rand.nextInt(1000)%64 * 7,
				50,
				50
				);
		}
		else {
			enemies[i] = new DarkPinkMon(
					"darkpinkmon",
					15,
					7,
					5,
					false,
					1200,
					rand.nextInt(500-400)+400,
					25,
					50
					);
		}
	}
}