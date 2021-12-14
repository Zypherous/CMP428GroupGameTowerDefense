
public  class Stage {

	private GameF21 game;
	private Wave[] waves;
	private int timeBetweenWaves;
	private int numWaves;
	private int stageNum;
	private Background bgImg;
	public Stage (int stageNum, Background bgImg, int timeBetweenWaves, int numWaves, GameF21 game){
		this.timeBetweenWaves = timeBetweenWaves;
		this.stageNum = stageNum;
		this.bgImg = bgImg;
		this.numWaves = numWaves;
		this.waves = new Wave[numWaves];
		this.game = game;
	}
	
	public void setStage() {
		game.secondsToNextWave = timeBetweenWaves;
		setWaves();
		loadWave(1);
	}
	public void loadWave(int waveNum) {
		for(int i = 0; i < waves[waveNum-1].getEnemies().length; i ++) {
			game.enemiess.add(waves[waveNum].getEnemies()[i]);
		}
	}

	public Wave[] getWaves() {
		return waves;
	}

	public void setWaves() {
		for(int i = 0; i < numWaves; i++) {
			waves[i] = new Wave(i+1, stageNum);
		}
	}

	public int getTimeBetweenWaves() {
		return timeBetweenWaves;
	}

	public void setTimeBetweenWaves(int timeBetweenWaves) {
		this.timeBetweenWaves = timeBetweenWaves;
	}

	public int getNumWaves() {
		return numWaves;
	}

	public void setNumWaves(int numWaves) {
		this.numWaves = numWaves;
	}

	public Background getBgImg() {
		return bgImg;
	}

	public void setBgImg(Background bgImg) {
		this.bgImg = bgImg;
	}
	
}
