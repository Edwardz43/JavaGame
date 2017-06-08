package tw.org.iii.ed.game;

import java.awt.Image;
import java.util.LinkedList;

public class Enermy {
	Image img;
	int hp, maxHp, width, height, 
		x, y, dx, dy, hpBar,
		atk, imgCount, deadCount;
	boolean hurt, died;
	String type;
	int shotTimes = 0;
	
	public Enermy (String type){
		this.type = type; this.died = false;
		this.imgCount = this.deadCount = 0; 
		switch(this.type){
			case "Shadow":
				this.img = GameUtil.getImage("img/shadow.png");
				this.x = 1300; this.y = 380 + (int)(Math.random()*80);
				this.hp = this.maxHp = 150;
				this.hpBar = 80;
				this.dx = -(int)(2 + Math.random()* 2); this.dy = 0; 
				this.width = 100; this.height = 100;
				this.atk = 10;
				break;	
			case "Grifon":
				this.img = GameUtil.getImage("img/grif.png");
				this.x = 1300; this.y = 80 + (int)(Math.random()*180);
				this.hp = this.maxHp = 120;
				this.hpBar = 80;
				this.dx = -(int)(3 + Math.random()* 2); this.dy = 0; 
				this.width = 100; this.height = 100;
				this.atk = 15;
				break;
		}
	}
	
	public void move(){
		if(!this.died){
			this.x += this.dx;
			this.y += this.dy;
			this.hpBar = (int)((float)this.hp / this.maxHp * 80);
		}else {
//			this.died = true;
		}
	}
	
	public void shot(LinkedList<Ball> balls, int fx, int fy){
		if(!this.died){
			int deltaX = (int)(3* Math.sin(Math.atan2(this.x - fx, this.y - fy)));
	        int deltaY = (int)(3* Math.cos(Math.atan2(this.x - fx, this.y - fy)));
			if(Math.random() * 200 < 1 ) balls.add(new Ball(this.x , this.y, this.atk, -deltaX, -deltaY, this.type));
		}
	}
	
	public void jump(){
		
	}
}
