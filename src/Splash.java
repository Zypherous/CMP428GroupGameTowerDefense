import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Splash {

	private Background bg;
	private GameF21 game;
	Splash(Background bg, GameF21 game){
		this.game = game;
		this.bg =bg;
	}
	 public void draw(Graphics g) {
         //setup pen color
         g.setColor(Color.black);
         g.fillRect(0,0,1080,720);
         bg.draw(g);

         g.setColor(Color.red);

         g.setFont(new Font("TimesRoman",Font.BOLD,55)); //SET UP TEXT STYLE AND SIZE

         //g.drawString("Tower Defense",230,300);
         g.drawString("TowerDefense",1080/2 - g.getFontMetrics().stringWidth("TowerDefense")/2,200);
//         g.drawString("select game mode:",320,400);// if we want to set up game mode ADD on here..
     //    g.drawString("Heaven Mode  ^_^ ",150,300);// if we want to set up game mode ADD on here..
     //    g.drawString("Hell Mode  O_O ",150,400);// if we want to set up game mode ADD on here..

     }
}
