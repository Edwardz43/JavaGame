package tw.org.iii.ed.game;

import java.awt.Image;

public class Ball {
	int x, y, width, height, dx, dy, power, imgCount;
	boolean hit;
	String type;
	Image img;
	
	
	public Ball (int x, int y, int power, int dx, int dy, String type){
		this.x = x;
        this.y = y;
        this.power = power;
        this.hit = false;
        this.imgCount = 0;
        this.type = type;
        switch(this.type){
        case "Fairy":
        	this.img =  GameUtil.getImage("img/magicball.png");
        	this.width = 100;
            this.height = 100;
            this.dx = dx*4;
            this.dy = dy*4;
        	break;
        case "Shadow":
        	this.img = GameUtil.getImage("img/shadowball.png");
        	this.width = 50;
            this.height = 50;
            this.dx = dx*3;
            this.dy = dy*3;
        	break;
        case "Grifon":
        	this.img = GameUtil.getImage("img/grifball.png");
        	this.width = 60;
            this.height = 60;
            this.dx = dx*4;
            this.dy = dy*4;
        	break;
        }
         
	}
	
	public void move(){
		this.x += this.dx;
		this.y += this.dy;
	}
}
