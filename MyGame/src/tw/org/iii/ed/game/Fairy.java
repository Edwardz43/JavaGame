package tw.org.iii.ed.game;

import java.util.LinkedList;

public class Fairy {
	int level, hp, maxHp, width, height, 
				exp, levelupExp, x, y, dx, dy, speed,
				atk, imgCount, hitCount, chagreCount, deadCount,
				chargeNum;
	boolean hurt, died;
	String move, dir;
	LinkedList<Ball> balls;
	int shotTimes = 0;
	
	public Fairy(int lv, int maxHp, int x, int y){
		this.level = lv;
		this.hp = this.maxHp = maxHp;
		this.x = x; this.y = y;
		this.dx = this.dy = 0; this.speed = 5;
		this.width = 100; this.height = 84;
		this.move = "none"; this.hurt = false; this.died = false;
		this. atk = 6; this.dir ="r";
		this.imgCount = this.hitCount = this.chagreCount = this.deadCount = this.chargeNum = 0; 
		this.exp = this.levelupExp = 0;
		this.balls = new LinkedList();
	}
	
	void move(){
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
        }
	}
	
	void setMove(String move){
		this.move = move; 
	}
	
	void shot(){
//		
		this.balls.add(new Ball(this.x - 5, this.y + 2, 0, 3, 0));
//		System.out.println(this.balls.size());
		//		System.out.println("shot");
//		this.shotTimes++;
	}

}
