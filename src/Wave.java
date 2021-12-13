import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Wave {
	private int waveNum;
	private int stageNum;
	private Enemy [] enemies ;
	Random rand;
	String waveName;
	public Wave (int waveNum, int stageNum) {
		this.waveNum = waveNum;
		this.waveName = "wave" + waveNum;
		this.stageNum = stageNum;
		setEnemies(load(waveName));
		rand = new Random();
		
	}

	public int getWaveNum() {
		return waveNum;
	}

	public void setWaveNum(int waveNum) {
		this.waveNum = waveNum;
	}

	public Enemy[] getEnemies() {
		return enemies;
	}

	public void setEnemies(Enemy[] enemies) {
		
		this.enemies = enemies;
	}
	
	public Enemy[] load(String waveName) {
	
		Enemy[] enemies;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(("/waves/stage" + stageNum + waveName)))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String[] tokens = line.split(", ");
            enemies = new Enemy[Integer.parseInt(tokens[0])];
            for(int i = 0; i < enemies.length; i ++) {
            	enemies[i] = newEnemy(tokens[i]);
            }
            return enemies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public Enemy newEnemy(String name) {
		if(name.equals("bat")) {
			return new Bat(
					"bat",
					10,
					2,
					2,
					false,
					1200,
					rand.nextInt(1000)%64 * 7,
					50,
					50,
					5
					);
			}
		if(name.equals("darkpnkmon")) {
			new DarkPinkMon(
					"darkpnkmon",
					20,
					15,
					50,
					false,
					1200,
					rand.nextInt(500-400)+400,
					25,
					50,
					1
					);
		}
		return null;
	}
}
