package tw.org.iii.ed.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;


public class FairyQuest extends JPanel{
	private int viewW, viewH;
	private JPanel menuBar;
	private Image imgBG = GameUtil.getImage("img/BG003.jpg");
	private Timer timer = new Timer();
	private int m = 0, enemyCount = 0;
	private Fairy fairy = new Fairy(400, 100, 100);
//	private static int scale;
	LinkedList<Enermy> enermies = new LinkedList<Enermy>();
	LinkedList<Ball> enermyBalls = new LinkedList<Ball>();
	LinkedList<Ball> fairyBalls = new LinkedList<Ball>();
//	private Ball ball;  
//	private LinkedList<Ball> balls;
	
	public FairyQuest() {
		setLayout(new BorderLayout());
		
		menuBar = new JPanel();
		menuBar.setLayout(new FlowLayout());
//		start = new JButton("開始遊戲");
//		menuBar.add(start,FlowLayout.LEFT);
//		start.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				startgame();
//			}
//		});
		timer.schedule(new Animation(),0, 16);
		add(menuBar, BorderLayout.NORTH);
//		addMouseMotionListener(new MyMouseAdapter());
		addKeyListener(new MyKeyListener());
		setFocusable(true);
//		requestFocusInWindow();

	}
	    
	private class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
//			System.out.println(e.getKeyCode());
			switch (e.getKeyCode()){
				case 32:
					fairy.shot(fairyBalls);
					break;
				case 37:
					fairy.setMove("LEFT");
					//System.out.println(fairy.move);
					break;
				case 38:
					fairy.setMove("UP");
					//System.out.println(fairy.move);
					break;
				case 39:
					fairy.setMove("RIGHT");
					//System.out.println(fairy.move);
					break;
				case 40:
					fairy.setMove("DOWN");
					//System.out.println(fairy.move);
					break;	
			}
		}
		
		public void keyReleased(KeyEvent e){
			switch (e.getKeyCode()){
				case 37: case 39:
					fairy.setMove("h-NONE");
					//System.out.println(fairy.move);
					break;
				case 38: case 40:
					fairy.setMove("v-NONE");
					//System.out.println(fairy.move);
					break;
			}
		}
	}
	
	private class Animation extends TimerTask{
		@Override
		public void run() {
			//妖精
			updateFairy();
			
			//怪物
			updateEnermy();
			
			//子彈
			updateBalls ();
			 
			m++;
			if(m >= 788) m = 0;
			repaint();
		}
	}
	
	void updateFairy(){
		fairy.move();
		fairy.hpBar = fairy.died ? 0 : (int)((float)fairy.hp / fairy.maxHp * 400);
		if(fairy.hurt && fairy.hurtCount <= 600) fairy.hurtCount++;
		if(fairy.hurtCount > 60) {
			fairy.hurtCount = 0;
			fairy.hurt = false;
		}
		if(fairyBalls != null){
			for(int i = 0; i < fairyBalls.size(); i++) {
				Ball ball = fairyBalls.get(i);
				ball.move();
				hitCheck(ball);
				ball.imgCount++;
				if(ball.imgCount >= 180) ball.imgCount = 0;					
			}
		}
	}
	
	void updateEnermy(){
		if(Math.random() * 200 < 1) enermies.add(new Enermy("Shadow"));
		if(Math.random() * 200 < 1) enermies.add(new Enermy("Grifon"));
//		if(enermys.length == 0) Math.random() > 0.5 ? enermys.push(new enermyShadow()) : enermys.push(new enermyHarpy());    
		if(enermies != null){
			for(int i = 0; i < enermies.size(); i++){
				Enermy e = enermies.get(i);
				if(e.died){
					e.imgCount++;
					if(e.imgCount >= 30){
						enermies.remove(enermies.indexOf(e));
						enemyCount++;
						if(enemyCount % 10 == 0) enermies.add(new Enermy("Knight"));
					}
				}else{
					e.shot(enermyBalls, fairy.x, fairy.y);
					e.imgCount++;
					if(e.imgCount >= 18) e.imgCount = 0;
					e.move();
					if(e.x + e.width < -20 ) enermies.remove(enermies.indexOf(e));
				}
			}
		}
		
	}
	
	void updateBalls (){
		if(enermyBalls != null){
			for(int j = 0; j < enermyBalls.size(); j++) {
				Ball ball = enermyBalls.get(j);
				ball.move();
				hitCheck(ball);
				ball.imgCount++;
				switch(ball.type){
					case "Fairy":
						if(ball.imgCount >= 180) ball.imgCount = 0;
						break;
					case "Shadow":
						if(ball.imgCount >= 90) ball.imgCount = 0;
					case "Grifon":
						if(ball.imgCount >= 30) ball.imgCount = 0;
				}
									
			}
		}
	}
	
	void hitCheck(Ball ball){
		//攻擊命中
		switch(ball.type){
			case "Fairy":
				for(int i = 0; i < enermies.size(); i++){
					Enermy e = enermies.get(i);
		            if (e.hp > 0 && ball.hit == false) {
		            	if ((
	                		ball.x + ball.dx >= e.x - e.width *0.5 && 
		                    ball.x - ball.dx <= e.x + e.width *0.5 &&
		                    ball.y + ball.dy >= e.y - e.height *0.5 && 
		                    ball.y - ball.dy <= e.y + e.height *0.5
		                    ))
		                {
		            		ball.hit = true;
		            		e.hp -= ball.power;
		            		if(e.hp <= 0) {
		            			e.died = true;e.imgCount = 0; 
		            		}
		                	fairyBalls.remove(fairyBalls.indexOf(ball));	
		                 }
//		                    if(ball.power ==0 )balls.splice(balls.indexOf(ball), 1);
		            }
		        }
				break;
			default:
				if (fairy.hp > 0 && fairy.hurt == false) {
				    if (
				        ball.x + ball.dx  >= fairy.x - fairy.width *0.2 && 
				        ball.x - ball.dx  <= fairy.x + fairy.width *0.5 &&
				        ball.y + ball.dy  >= fairy.y - fairy.height *0.3 && 
				        ball.y - ball.dy  <= fairy.y + fairy.height *0.7 
				        ){
				    		ball.hit = true;
				    		fairy.hurt = true;
				    		fairy.hp -= ball.power;
				    		enermyBalls.remove(enermyBalls.indexOf(ball));
//				    		fairy.hurt = true;
				    		System.out.println("hit");
				    		System.out.println("hp : "+ fairy.hp+", hp bar : "+ fairy.hpBar);
//							reduceHp(ball.power);
				    		//test
//							damage += ball.power;		
				    	}
				}
				
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		viewW = getWidth(); viewH=getHeight();
		Graphics2D g2d= (Graphics2D)g;
		g2d.clearRect(0, 0, viewW, viewH);
		
		//背景
		g2d.drawImage(imgBG, 0, 0, viewW, viewH, 0, 0, 1280, 600, null);
		
		//主角子彈
		if(fairyBalls != null){
			for(int i = 0; i < fairyBalls.size(); i++){
				Ball ball = fairyBalls.get(i);
				if(!ball.hit)
				g2d.drawImage(
						ball.img, ball.x, ball.y,
						ball.x + ball.width, ball.y + ball.height,
						320*(ball.imgCount/6%3), 240*(ball.imgCount/18),
						320*(ball.imgCount/6%3+1), 240*(ball.imgCount/18+1), null
				);
				
//				g2d.drawImage(
//						imgBigBall, ball.x, ball.y,
//						ball.x + ball.width, ball.y + ball.height,
//						480*(ball.imgCount/6%5), 480*(ball.imgCount/30),
//						480*(ball.imgCount/6%5+1), 480*(ball.imgCount/30+1), null
//				);
			}
		}
		
		//怪物
		if(enermies != null){
			for(int i = 0; i < enermies.size(); i++){
				Enermy e = enermies.get(i);
				if(!e.died){
					switch(e.type){
						//Shadow
						case "Shadow":
							g2d.drawImage(
									e.img, e.x, e.y, e.x + e.width, e.y + e.height, 
									0 + e.imgCount / 6 *60, 60, (e.imgCount/6+1)*60, 120, null);
							break;
						//Grif	
						case "Grifon":
							g2d.drawImage(
									e.img, e.x, e.y, e.x + e.width, e.y + e.height, 
									0 + e.imgCount / 6 *45, 50, (e.imgCount/6+1)*45, 100, null);
							break;
						//Knight
						case "Knight":
							g2d.drawImage(
									e.img, e.x, e.y, e.x + e.width, e.y + e.height, 
									0 + e.imgCount / 6 *60, 70, (e.imgCount/6+1)*60, 140, null);
							break;
					}
				}else{
					e.img = GameUtil.getImage("img/boom.png");
					g2d.drawImage(
							e.img, e.x, e.y, e.x + e.width, e.y + e.height, 
							0 + e.imgCount /6 * 240, 0, (e.imgCount /6 + 1) * 240, 240, null);
				}
				if(e.hp < e.maxHp && e.hp > 0){
					g2d.setColor(Color.RED);
					g2d.fillRect(e.x + 5, e.y - 12 , 80, 6);
					g2d.setColor(Color.YELLOW);
					g2d.fillRect(e.x + 5, e.y - 12 , e.hpBar, 6);
				}
			}
		}
		
		//怪物子彈
		if(enermyBalls != null){	
			for(int i = 0; i < enermyBalls.size(); i++){
				Ball ball = enermyBalls.get(i);
				if(!ball.hit)
					switch(ball.type){
					case "Shadow":
						g2d.drawImage(
								ball.img, ball.x, ball.y,
								ball.x + ball.width, ball.y + ball.height,
								120*(ball.imgCount/6%5), 120*(ball.imgCount/30),
								120*(ball.imgCount/6%5+1), 120*(ball.imgCount/30+1), null
						);
						break;
					case "Grifon":
						g2d.drawImage(
								ball.img, ball.x, ball.y,
								ball.x + ball.width, ball.y + ball.height,
								192*(ball.imgCount/3%5), 192*(ball.imgCount/15),
								192*(ball.imgCount/3%5+1), 192*(ball.imgCount/15+1), null
						);
						break;
				}
			}
		}
		
		//主角
		if(fairy.hurt){
			if(fairy.imgCount % 4 ==0){
				if(fairy.dir == "l")
					g2d.drawImage(
							fairy.img, fairy.x, fairy.y, 
							fairy.x + fairy.width, fairy.y + fairy.height,
							0 + fairy.imgCount/6*50, 42, 
							(fairy.imgCount/6+1)*50, 83, null
					);
				else 	
				g2d.drawImage(
						fairy.img, fairy.x, fairy.y, 
						fairy.x + fairy.width, fairy.y + fairy.height,
						0 + fairy.imgCount/6*50, 84, 
						(fairy.imgCount/6+1)*50, 126, null
				);
			}
		}else{
			if(fairy.dir == "l")
				g2d.drawImage(
						fairy.img, fairy.x, fairy.y, 
						fairy.x + fairy.width, fairy.y + fairy.height,
						0 + fairy.imgCount/6*50, 42, 
						(fairy.imgCount/6+1)*50, 83, null
				);
			else 	
			g2d.drawImage(
					fairy.img, fairy.x, fairy.y, 
					fairy.x + fairy.width, fairy.y + fairy.height,
					0 + fairy.imgCount/6*50, 84, 
					(fairy.imgCount/6+1)*50, 126, null
			);
		}
		
		
		//主角狀態
		g2d.drawImage(fairy.imgHead, 0, 10, 90, 100, 97, 0, 192, 96,  null);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(92, 24, 404, 24);
		g2d.setColor(fairy.hpBar <= 80 ? Color.red : Color.GREEN);
		g2d.fillRect(94, 26, fairy.hpBar > 0 ? fairy.hpBar : 0, 20);
	}
	
	public static void main(String[] args){
		GameFrame gf = new GameFrame();
		FairyQuest fq = new FairyQuest(); 
		gf.add(fq);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		double width = screenSize.getWidth();
//		double height = screenSize.getHeight();
//		scale = (int)Math.min(width/1280.0, height/600.0);
//		gf.launchFrame((int)(width*0.8), (int)(height*0.8));
		gf.launchFrame(1280, 600);
	}
}
