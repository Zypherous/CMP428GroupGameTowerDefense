import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
	List<Enemy> copyOfEnemies;
	Button damage = new Button(64, 64*2, 64,64, "images/Sword", new String[] {""}, 1, "png", 10, this , "UpgradeDamage");
	Button gunUpgrade = new Button(64, 64*4, 64,64, "images/ART_GUN", new String[] {""}, 1, "png", 1000, this, "MoreGuns" );
	Button startButton;
	static final int BULLETS = 1000;
	int numBullets = 0;
	int health;
	int second = 0;
	int shotDelay = 30;
	long currentTime, lastUpdate;
	int points = 100000;
	int enemiesKilled = 0;
	int secondsToNextWave = 10;
	int projDmg = 1;
	int waveNum;
	boolean start = false;
	
	public void initialize() {
		startButton = new Button(1080/2 -64, 300 -64, 64, 64, "images/Start-Button", new String[] {""}, 1, "png", 0, this, "startButton" );
		enemiess = new ArrayList<>();
		copyOfEnemies = new ArrayList<>();
		currentTime = lastUpdate = System.currentTimeMillis();
		rand = new Random();
		splash = new Splash(new Background("images/splash.png"), this);
		this.health = t.getHealth();
		timer();
		
		stage = new Stage(1, new Background("images/Grass_field.jpeg"), 15, 4, this);
		stage.setStage();
		this.waveNum = 1;
	}	
	
	
	public void inGameLoop(){
		if(start) {
			
			timer.start();
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
							t.guns[i].fire(numBullets);
							}
						}
					
					numBullets++;
				}else {
					numBullets = 0;
				}
			}
			for(int i = 0; i < numBullets; i++) {
				for(int j = 0; j < t.numGuns; j++) {
					if(t.guns[j].getActive()) {
						t.guns[j].projectiles[i].shoot();
					}
				}
				}
			if(!enemiess.isEmpty()) {	
				sortEnemiesByX();

				updateEnemies();
//				enemiess.forEach(enemy-> {
//					if(enemy.rect.overlaps(t)) {		
//						t.health -= enemy.getDamage();
//						if(t.isDead()) {
//							t.destroy();
//						}
//						enemiess.remove(enemy);
//					}
//				});
				
			//Auto aim
//				t.gun.turnTowards(enemiess.get(0));
			}
		}else {
			start = false;
		}

			currentTime = System.currentTimeMillis();
			if(t.isDead()) {
				t.destroy();
				this.start = false;
				enemiess.clear();
				stage.setStage();
				stage.loadWave(1);
				t.reset();
				timer.stop();
				timer();
			}
	}
		
	public void paint(Graphics pen){
		
		pen.setFont(font);
		if(start) {
			stage.getBgImg().draw(pen);
			t.draw(pen);
			for(Gun gun : t.guns) {
				if(gun.getActive()) {
					for(int i = 0; i < numBullets; i++) {
						if(!gun.projectiles[i].hit) {
							gun.projectiles[i].draw(pen);
						}
					}
				}
			}
			drawEnemies(pen);
			damage.draw(pen);
			gunUpgrade.draw(pen);
			pen.setColor(Color.BLACK);
			pen.drawString(String.format("Health: %d", t.health), 64, 64);
			pen.drawString(String.format("Next Wave: %d seconds", secondsToNextWave), 64*12, 64);
			pen.drawString(String.format("Killed: %d    Points: %d", enemiesKilled, points), 64*6, 64);
		}
		else {
			splash.draw(pen);
			startButton.draw(pen);
		}
	}	
	
	public void timer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(secondsToNextWave <= 0 && waveNum < stage.getNumWaves()) {
					waveNum++;
					if(waveNum < stage.getNumWaves())
					{
						stage.loadWave(waveNum);
						secondsToNextWave = stage.getTimeBetweenWaves();
					}
				}else {
					timer.stop();
				}
				secondsToNextWave--;
			}

		});
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
	
	public void sortEnemiesByX() {
		enemiess.sort(Comparator.comparing(enemy -> enemy.getX()));
	}
	
	public void updateEnemies() {
		for(int i = 0; i < enemiess.size();i++) {
			enemiess.get(i).chase(l,enemiess.get(i).speed);
			if(enemiess.get(i).rect.overlaps(t)) {
				enemiess.get(i).setHitTower(true);
				enemiess.get(i).takeDamage(enemiess.get(i).getHealth());
			}
		}
		for(int i = 0; i < enemiess.size(); i++) {
			for(Gun gun: t.guns) {
				for(int bullet= 0; bullet< numBullets; bullet++) {
					if(gun.getActive() && gun.projectiles[bullet].rect.overlaps(enemiess.get(i).rect) && !gun.projectiles[bullet].hit) {
						enemiess.get(i).takeDamage(gun.projectiles[bullet].damage);
								gun.projectiles[bullet].hit = true;
								if(enemiess.get(i).isDead()) {
									points += enemiess.get(i).points;
									
									enemiesKilled++;
								}
					}
				}
			}
			if(enemiess.get(i).rect.overlaps(t)) {	
				enemiess.get(i).takeDamage(enemiess.get(i).getHealth());
			}
		}
		copyOfEnemies = enemiess.stream().filter(enemy ->enemy.isDead())
		.collect(Collectors.toList());
		copyOfEnemies.forEach(enemy ->{
			if(enemy.isHitTower()) t.health -= enemy.getDamage();
		});
		copyOfEnemies.clear();
		enemiess.removeIf(enemy -> (enemy.isDead()));
	}
	
	public void drawEnemies(Graphics g) {
		for(int i = 0; i < enemiess.size();i++) {
			enemiess.get(i).draw(g);
		}		
	}
}