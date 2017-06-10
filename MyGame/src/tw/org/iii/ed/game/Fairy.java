package tw.org.iii.ed.game;

import java.awt.Image;
import java.util.LinkedList;

public class Fairy {
	Image img = GameUtil.getImage("img/fairy_f02.png");
	Image imgHead = GameUtil.getImage("img/fairy_f14.png");
	int hp, maxHp, width, height, 
		x, y, dx, dy, speed,
		atk, imgCount, hurtCount, chagreCount, deadCount,
		chargeNum, hpBar;
	boolean hurt, died;
	String move, dir;
	int shotTimes = 0;
	
	public Fairy(int maxHp, int x, int y){
		this.hp = this.maxHp = maxHp;
		this.x = x; this.y = y;
		this.dx = this.dy = 0; this.speed = 5;
		this.width = 100; this.height = 84;
		this.move = "none"; this.hurt = false; this.died = false;
		this.atk = 30; this.dir ="r";
		this.imgCount = this.hurtCount = this.chagreCount = this.deadCount = this.chargeNum = 0;
	}
	
	public void move(){
		if(this.hp > 0){
            switch (this.move) {
                case "LEFT":
                	this.dx = -this.speed;
                	this.dir = "l";
                    break;
                case "RIGHT":
                	this.dx = this.speed;
                	this.dir = "r";
                    break;
                case "UP":
                	this.dy = -this.speed;  
                    break;
                case "DOWN":
                	this.dy = this.speed; 
                    break;
                case "h-NONE":
                	this.dx = 0;
                    break;
                case "v-NONE":
                	this.dy = 0;
                    break;
                default:
                	this.dx = 0;
                	this.dy = 0;
            }
            if (this.x + this.dx < 0 || this.x + this.dx + this.width >= 1280){
            	this.dx = 0; 
            }
            if (this.y + this.dy < 0 || this.y + this.dy +this.height > 580){
            	this.dy = 0; 
            }
            this.x = this.x + this.dx;
            this.y = this.y + this.dy;
            this.imgCount++;
    		if(this.imgCount >= 18) this.imgCount = 0;
        }
	}
	
	public void setMove(String move){
		this.move = move;
	}
	
	public void shot(LinkedList<Ball> balls){		
		balls.add(
			new Ball(this.x - 5, this.y + 2, this.atk, this.dir=="l"?-3:3, 0, "Fairy")
		);
	}
	
	public void recover(){
		this.hp = this.maxHp;
	}
}
