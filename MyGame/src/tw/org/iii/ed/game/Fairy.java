package tw.org.iii.ed.game;

public class Fairy {
	int level, hp, maxHp, width, height, 
				exp, levelupExp, x, y, dx, dy, speed,
				atk, imgCount, hitCount, chagreCount, deadCount,
				chargeNum;
	boolean hurt, died;
	String move, dir;
	
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
	}
	
	void move(){
		if(hp > 0){
            switch (move) {
                case "LEFT":
                    dx = -speed;
                    dir = "l";
                    break;
                case "RIGHT":
                    dx = speed;
                    dir = "r";
                    break;
                case "UP":
                    dy = -speed;  
                    break;
                case "DOWN":
                    dy = speed; 
                    break;
                case "h-NONE":
                    dx = 0;
                    break;
                case "v-NONE":
                    dy = 0;
                    break;
                default:
                    dx = 0;
                    dy = 0;
            }
            if (x + dx < 0 || x + dx + width >= 1000){
                dx = 0; 
            }
            if (y + dy < 0 || y + dy +height > 600){
                dy = 0; 
            }
            x = x + dx;
            y = y + dy;
        }
	}

}
