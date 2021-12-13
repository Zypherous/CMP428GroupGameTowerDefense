import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Button extends Sprite{

	GameF21 game;
	Rect r;
	String type;
	Font font = new Font("Serif", Font.BOLD, 12);
	Image image = Toolkit.getDefaultToolkit().getImage("images/b_0.jpg");
	int cost;
	int upgradeLevel = 0;
	public Button(int x, int y, int w, int h, String name, String[] pose, int count, String filetype, int cost, GameF21 game, String type) {
		super(x, y, w, h, name, pose, count, filetype, 10000);
		this.game = game;
		this.cost = cost;
		this.type = type;
		r = new Rect(x, y, w, h);
	}
	@Override
	public void draw(Graphics pen) {
		pen.drawImage(image, (int)x, (int)y, (int) w, (int) h, null);
		r.draw(pen);
		pen.setFont(font);
		pen.setColor(Color.BLUE);
		pen.drawImage(animation[0].currentImage(), (int)x, (int)y, 64, 64, null);
		if(this.type.equals("UpgradeDamage")){
				pen.drawString(String.format("Current Damage: %d", game.projDmg), (int)this.x + this.w +1, (int)this.y + 12*3 );
		}
		if(this.type.equals("MoreGuns")) {
			pen.drawString(String.format("Numvber of Guns: %d", game.t.numGuns), (int)this.x + this.w +1, (int)this.y + 12*3 );			
		}
		if(this.type.equals("ShotDelay")) {
			pen.drawString(String.format("Shot Delay: %d", game.shotDelay), (int)this.x + this.w +1, (int)this.y + 12*3 );						
		}
		pen.drawString(String.format("Cost: %d", this.cost), (int)this.x + this.w +1, (int)this.y + 12*4 );
		pen.drawString(String.format("Level: %d", this.upgradeLevel), (int)this.x + this.w +1, (int)this.y + 12*5  );
		
	}

}
