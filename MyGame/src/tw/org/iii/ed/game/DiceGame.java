package tw.org.iii.ed.game;

import java.util.ArrayList;
import java.util.HashSet;

public class DiceGame {

	public static void main(String[] args) {
		
		ArrayList<Integer> res = res();
		System.out.println("--------");
		for(int i : res) {
			System.out.print(i+" ");
		}
	}
	
	public static ArrayList<Integer> res(){
		ArrayList<Integer> record = new ArrayList<>();
		HashSet<Integer> len = new HashSet<>();
		for(int i = 0; i < 4; i ++) {
			int temp = (int)(Math.random() * 6 +1);
			System.out.println(temp);
			if(record.contains(temp)) {
				record.remove(record.indexOf(temp));
			}else 
				record.add(temp);
		}
		return record;
	}
}
