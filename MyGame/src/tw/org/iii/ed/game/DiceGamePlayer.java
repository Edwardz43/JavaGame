package tw.org.iii.ed.game;

import java.util.ArrayList;

public class DiceGamePlayer {
	ArrayList<Integer> result;
	private int score;
	
	public DiceGamePlayer() {
		this.score = 0; 
		this.result = new ArrayList<>();
	}
	
	public void setScore (int score) {
		this.score = score;
	}
	
	public int getScore () {return this.score;}
}
