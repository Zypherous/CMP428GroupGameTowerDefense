
public class Button extends Sprite{

	Rect r;
	int cost;
	int upgradeLevel = 0;
	public Button(int x, int y, int w, int h, String name, String[] pose, int count, String filetype, int cost) {
		super(x, y, w, h, name, pose, count, filetype, 10000);
		this.cost = cost;
		r = new Rect(x, y, w, h);
	}
	
	

}
