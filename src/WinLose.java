import javax.swing.JOptionPane;

public class WinLose {

// The object that will be taken in to get information from	
	Tower tower;
	int score;
	int winScore;
	
// Enter the tower object that you want keep track of its health, score added from destroyed enemies, The Winning score for the game	
	public WinLose(Tower tower, int score, int Winscore) {
		this.winScore = winScore;
		this.tower = tower;
		this.score = score;
	}
// If the towers health reaches zero JOptionPane should ask if player wants to quit or keep playing 
// the tower health is reset to the towers original health.
	public void deathMessage() {
			
		if(tower.getHealth() == 0) {
			int End = JOptionPane.showConfirmDialog(null , "Do you want to give up?","Continue?",JOptionPane.YES_NO_OPTION);
			if(End == JOptionPane.YES_OPTION) {
				System.exit(0);
			}else if(End == JOptionPane.NO_OPTION) {
				 tower.setHealth(tower.health);
			}
		}
	}

// If the player reaches the set Winning score from adding up the defeated enemies score 
//JOptionPane should ask if they want to play again score is set to 0 to let player play again
	public void winMessage(Sprite sprite,Rect rect) {
		if(score == winScore) {
			int Winner = JOptionPane.showConfirmDialog(null , "Do you want to play again?","Winner",JOptionPane.YES_NO_OPTION);
			if(Winner == JOptionPane.YES_OPTION) {
				score = 0;
			}else if(Winner == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
	}
	
	
	
	
	
	
	
	
}
