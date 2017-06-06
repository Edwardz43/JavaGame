package tw.org.iii.ed.game;

import java.awt.Image;
import java.util.LinkedList;

public class Enermy {
	Image img = GameUtil.getImage("img/shadow.png");
	int level, hp, maxHp, width, height, 
		x, y, dx, dy, exp,
		atk, imgCount, deadCount;
	boolean hurt, died;
	String type;
	LinkedList<Ball> balls;
	int shotTimes = 0;
	
	public Enermy (String type, int lv, int x, int y){
		this.level = lv;
		this.x = x; this.y = y;
		this.type = type; this.died = false;
		this.imgCount = this.deadCount = 0; 
		this.balls = new LinkedList();
		switch(this.type){
			case "Shadow":
				this.hp = this.maxHp = 50 + this.level*35;
				this.dx = -(int)(2 + Math.random()* 2); this.dy = 0; 
				this.width = 100; this.height = 100;
				this.atk = this.level * 10;
				this.exp = 50 + this.level * 27;
				break;
		}
	}
	
	void move(){
         this.x += this.dx;
         this.y += this.dy;
	}
}
