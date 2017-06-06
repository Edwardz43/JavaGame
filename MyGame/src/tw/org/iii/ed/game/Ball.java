package tw.org.iii.ed.game;

public class Ball {
	int x, y, width, height, dx, dy, power, imgCount;
	boolean hit;
	
	public Ball (int x, int y, int power, int dx, int dy){
		this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 100;
        this.dx = dx*3;
        this.dy = dy*3;
        this.power = 0;
        this.hit = false;
        this.imgCount = 0;
	}
	
	void move(){
		this.x += this.dx;
		this.y += this.dy;
	}
}
