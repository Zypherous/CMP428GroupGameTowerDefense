import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class GameF21 extends GameBase{
	
	Timer timer;
	Splash splash;
	Stage stage;
	Circle c = new Circle(540,360,50,50);
	Tower t = new Tower(20, 40,6* 64, 239/2, 486/2, this);
	Enemy [] enemies = new Enemy[5];
	Line l = new Line(40,7*64,64,9*64);
	Random rand;
	Font font = new Font("Serif", Font.PLAIN, 32);
	List<Enemy> enemiess;
	Button damage = new Button(64, 64*2, 64,64, "images/Sword", new String[] {""}, 1, "png", 10, this , "UpgradeDamage");
	Button gunUpgrade = new Button(64, 64*4, 64,64, "images/ART_GUN", new String[] {""}, 1, "png", 1000, this, "MoreGuns" );
	Button startButton = new Button(this.getWidth()/2, this.getHeight()/2, 128, 128, "images/Start-Button", new String[] {""}, 1, "png", 0, this, "startButton" );
	static final int BULLETS = 1000;
	int numBullets = 0;
	int health;
	int second = 0;
	int shotDelay = 30;
	long currentTime, lastUpdate;
	int points = 0;
	int enemiesKilled = 0;
	int secondsToNextWave = 10;
	int projDmg = 1;
	boolean start = false;

	
	Rect r = new Rect((int) damage.x, (int)damage.y, (int)damage.w, (int)damage.h);
	public void initialize() {
		currentTime = lastUpdate = System.currentTimeMillis();
		rand = new Random();
		splash = new Splash(new Background("images/splash.png"), this);
		this.health = t.getHealth();
		timer();
		timer.start();
		stage = new Stage(1, new Background("images/Grass_field.jpeg"), 15, 4, this);
		stage.loadWave(1);
	}	
	
	
	public void inGameLoop(){
//		if(pressing[UP])     c.moveForward(10);
		for(int i = 0; i < t.guns.length; i++) {
			if(pressing[RT]  && t.guns[i].getActive())     t.guns[i].turnRight(3);
			if(pressing[LT] && t.guns[i].getActive())     t.guns[i].turnLeft(3);
		}

		if(pressing[SPACE] && currentTime - lastUpdate > (shotDelay*10)) {
			lastUpdate = currentTime;
			if(numBullets < BULLETS) {
				for(int i = 0; i < t.guns.length; i++) {
					if(t.guns[i].getActive()) {
						t.guns[i].fire();
						}
					}
				}
				numBullets++;
			}else {
				numBullets = 0;
		}
		for(int i = 0; i < numBullets; i++) {
			for(int j = 0; j < t.numGuns; j++) {
				if(t.guns[j].getActive()) {
					t.guns[j].projectiles[i].shoot();
				}
			}
			enemiess.forEach(enemy ->{
				for(Gun gun: t.guns) {
					if(gun.getActive() && gun.projectiles[i].rect.overlaps(enemy.rect) && gun.projectiles[i].hit) {
								enemy.takeDamage(gun.projectiles[i].damage);
								gun.projectiles[i].hit = true;
								if(enemy.isDead()) {
									points += enemy.points;
									enemiess.remove(enemy);
									enemiesKilled++;
								}
					}
					};
			});
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
				t.health -= enemies[i].getDamage();
				if(t.isDead()) {
					t.destroy();
				}
				newEnemy(i);
				t.gun.turnTowards(enemies[i].rect);
				//System.out.println(String.format("RECT[%d] x: %d, y: %d",i, rect[i].getX(),rect[i].getY()));
			}
			
		}
		currentTime = System.currentTimeMillis();
	}	
	public void paint(Graphics pen){
		
		pen.setFont(font);
		if(start) {
			stage.getBgImg().draw(pen);
			for(int i = 0; i < numBullets; i++) {
				if(!projectiles[i].hit) {
					projectiles[i].draw(pen);
				}
				if(!projectiles1[i].hit) {
					projectiles1[i].draw(pen);
				}
				if(!projectiles2[i].hit) {
					projectiles2[i].draw(pen);
				}
			}
			t.draw(pen);
			for(int i =0; i < enemies.length;i++) {
				enemies[i].draw(pen);
			}
			damage.draw(pen);
			gunUpgrade.draw(pen);
			pen.setColor(Color.BLACK);
			pen.drawString(String.format("Health: %d", t.health), 64, 64);
			pen.drawString(String.format("Next Wave: %d seconds", secondsToNextWave), 64*12, 64);
			pen.drawString(String.format("Killed: %d    Points: %d", enemiesKilled, points), 64*6, 64);
		}
		else {
			splash.draw(pen);
			
		}

//		pen.drawString(String.format("Dmg: %d", projDmg), 64, 64*5 );
//		pen.drawString(String.format("Cost: %d", damage.cost), 64, 64*5 + 24 );
//		pen.drawString(String.format("Level: %d", damage.upgradeLevel), 64, 64*5 + 48 );
//		for(int i = 0; i < t.guns.length; i++) {
//			if(t.guns[i].active) {
//				t.guns[i].draw(pen);
//			}
//		}
		
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
	
//	public void newEnemy(int i) {
//		int nextEnemy = rand.nextInt(2);
//		if( nextEnemy==1) {
//			enemies[i] = new Bat(
//				"bat",
//				5,
//				2,
//				2,
//				false,
//				1200,
//				rand.nextInt(1000)%64 * 7,
//				50,
//				50
//				);
//		}
//		else {
//			enemies[i] = new DarkPinkMon(
//					"darkpinkmon",
//					10,
//					15,
//					50,
//					false,
//					1200,
//					rand.nextInt(500-400)+400,
//					25,
//					50
//					);
//		}
//	}
//	
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
		if(gunUpgrade.r.contains(mx, my) && t.numGuns !=3) {
			t.guns[t.numGuns].setActive(true);
			points -= gunUpgrade.cost;
			t.numGuns++;
		}
		if(startButton.r.contains(mx,my)) {
			start = true;
		}
	}
	
	public void setProjDmg(int dmg) {
		projDmg += dmg*2;
	}
}