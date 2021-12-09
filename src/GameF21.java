import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
	Font font = new Font("Serif", Font.PLAIN, 32);
	Font font2 = new Font("Serif", Font.PLAIN, 12);
	static final int BULLETS = 1000;
	int numBullets = 0;
	int health = 100;
	int second = 0;
	int shotDelay = 10;
	long currentTime, lastUpdate;
	int points = 0;
	int enemiesKilled = 0;
	int secondsToNextWave = 10;
	int projDmg = 1;
	
	Gun TEST = new Gun (64, 128, 50, 0);
	
	Rect r = new Rect((int) damage.x, (int)damage.y, (int)damage.w, (int)damage.h);
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
						t.gun.A, projDmg);
				
				numBullets++;
			}else {
				numBullets = 0;
			}
		}
		for(int i = 0; i < numBullets; i++) {
			projectiles[i].moveForward(15);
			for(int j = 0; j < enemies.length;j++) {
				if(projectiles[i].rect.overlaps(enemies[j].rect) && !projectiles[i].hit) {
					enemies[j].takeDamage(projectiles[i].damage);
					projectiles[i].hit = true;
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
		
		pen.setFont(font);
		space.draw(pen);
		for(int i = 0; i < numBullets; i++) {
			if(!projectiles[i].hit) {
				projectiles[i].draw(pen);
			}
		}
		t.draw(pen);
		for(int i =0; i < enemies.length;i++) {
			enemies[i].draw(pen);
		}
		damage.draw(pen);
		pen.setColor(Color.GREEN);
		pen.drawString(String.format("Health: %d", health), 64, 64);
		pen.drawString(String.format("Next Wave: %d seconds", secondsToNextWave), 64*12, 64);
		pen.drawString(String.format("Killed: %d    Points: %d", enemiesKilled, points), 64*6, 64);
		pen.setFont(font2);
		pen.drawString(String.format("Dmg: %d", projDmg), 64, 64*5 );
		pen.drawString(String.format("Cost: %d", damage.cost), 64, 64*5 + 24 );
		pen.drawString(String.format("Level: %d", damage.upgradeLevel), 64, 64*5 + 48 );
		//		TEST.draw(pen);
		
		pen.setColor(Color.RED);
		r.draw(pen);
		
	}	
	
	public void timer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(secondsToNextWave == 0) {
					secondsToNextWave = 11;
				}
				secondsToNextWave--;
			}

		});
	}
	
	public void newEnemy(int i) {
		int nextEnemy = rand.nextInt(2);
		if( nextEnemy==1) {
			enemies[i] = new Bat(
				"bat",
				5,
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
					8,
					7,
					50,
					false,
					1200,
					rand.nextInt(500-400)+400,
					25,
					50
					);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(damage.r.contains(mx, my) && points >= damage.cost) {
			points -= damage.cost;
			damage.upgradeLevel++;
			damage.cost += damage.cost + 100*damage.upgradeLevel;
			setProjDmg(damage.upgradeLevel);
			
		}
	}
	
	public void setProjDmg(int dmg) {
		projDmg += dmg*2;
	}
}