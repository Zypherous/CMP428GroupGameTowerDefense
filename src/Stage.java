
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
		this.waves = new Wave[numWaves];
		this.game = game;
	}
	
	public void setStage() {
		
		game.enemiess.clear();
		game.secondsToNextWave = timeBetweenWaves;
		loadWave(0);
	}
	public void loadWave(int waveNum) {
		for(int i = 0; i < waves[waveNum].getEnemies().length; i ++) {
			game.enemiess.add(waves[waveNum].getEnemies()[i]);
		}
	}

	public Wave[] getWaves() {
		return waves;
	}

	public void setWaves(Wave[] waves) {
		for(int i = 0; i < numWaves; i++) {
			waves[i] = new Wave(i, stageNum);
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
