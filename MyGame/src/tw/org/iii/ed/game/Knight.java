package tw.org.iii.ed.game;

public class Knight extends Enermy{

	public Knight(String type) {
		super(type);
		this.img = GameUtil.getImage("img/knight.png");
		this.x = 1300; this.y = 400;
		this.hp = this.maxHp = 500;
		this.hpBar = 80;
		this.dx = -(int)(2 + Math.random()* 2); this.dy = 0; 
		this.width = 140; this.height = 140;
		this.atk = 30;
	}

}
