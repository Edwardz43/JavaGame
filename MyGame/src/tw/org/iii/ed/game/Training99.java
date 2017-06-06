package tw.org.iii.ed.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Training99 extends JPanel{
	private Ball ball;
	private Timer timer;
	private int viewW, viewH;
	private LinkedList<Ball> balls;
	private int gameStage;
	private long startTime, endTime;
	private JPanel menuBar;
	private JButton start;
	private int shipX, shipY;
	
	public Training99(){
		setLayout(new BorderLayout());
		
		menuBar = new JPanel();
		menuBar.setLayout(new FlowLayout());
		start = new JButton("開始遊戲");
		menuBar.add(start,FlowLayout.LEFT);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startgame();
			}
		});
		balls = new LinkedList<>();
		add(menuBar, BorderLayout.NORTH);
		gameStage = 0;
		addMouseMotionListener(new MyMouseAdapter());
	}
	public static void main(String[] args){
		GameFrame gf = new GameFrame();
		Training99 t99 = new Training99();
		gf.add(t99);
		gf.launchFrame(800, 600);
	}
	
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
				shipX = e.getX()-20; shipY = e.getY()-20;	
		}
	}
	
	protected void startgame() {
		if(gameStage != 1){
			timer = new Timer();
			balls.clear();
			gameStage = 1;
			startTime = System.currentTimeMillis();
			timer.schedule(new ViewTask(),0, 16);
			timer.schedule(new createBall(), 1000 , 70);
		}
	}
	
	protected String rank(double time){
		String res ="";
		if(time < 5) res += "雜魚";
		if(time >= 5 && time < 10) res += "課長";
		if(time >= 10 && time < 15) res += "部長";
		if(time >= 15 && time < 20) res += "所長";
		if(time >= 20 && time < 25) res += "處長";
		if(time >= 25 && time < 30) res += "局長";
		if(time >= 30 && time < 35) res += "社長";
		if(time >= 35 && time < 40) res += "總理大臣";
		if(time >= 40 && time < 45) res += "大統領";
		if(time >= 45 && time < 50) res += "聖人";
		if(time >= 50 && time < 55) res += "宇宙人";
		if(time >= 55) res += "神";
		return res;
	}
	
	private class createBall extends TimerTask{
		@Override
		public void run() {
			if(gameStage == 1){
				int n = (int)(Math.random()*4);
				switch(n){
				case 1:
					ball = new Ball((int)(Math.random()*viewW+1),0);
					balls.add(ball);
					break;
				case 2:
					ball = new Ball(0,(int)(Math.random()*viewH+1));
					balls.add(ball);
					break;
				case 3:
					ball = new Ball(viewW,(int)(Math.random()*viewH+1));
					balls.add(ball);
					break;
				case 4:
					ball = new Ball((int)(Math.random()*viewW+1),viewH);
					balls.add(ball);
					break;
				}
			}
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			if(gameStage == 1){
				for(Ball ball : balls){
					if(ball.x >-20 && ball.x < viewW+20 && ball.y >= 0-20 && ball.y < viewH+20){
						ball.x += ball.dx;
						ball.y += ball.dy;
						if(ball.x+10 >= shipX  && ball.x - 10 <= shipX+10 && ball.y +10 >= shipY && ball.y -10 <= shipY + 10 ){
							gameStage = 2;
							timer.cancel();
							endTime = System.currentTimeMillis()- startTime;
							System.out.println("playTime:"+endTime/1000.0);
						}
					}
				}
			}
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		viewW = getWidth(); viewH=getHeight();
		Graphics2D g2d= (Graphics2D)g;
		g2d.clearRect(0, 0, viewW, viewH);
		if(gameStage == 0){
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, viewW, viewH);
			String Title = "特訓久久";
			g2d.setColor(Color.red);
			g2d.setFont(new Font(null,Font.CENTER_BASELINE, 50));
			g2d.drawString(Title, viewW/2-100,viewH/2);
		}
		
		if(gameStage == 1){
			g2d.setColor(new Color(219, 255, 255));
			g2d.fillRect(0, 0, viewW, viewH);
			
			g2d.setColor(new Color(91, 91, 174));
			g2d.fillOval(shipX, shipY, 40, 40);
			
			for(int i = 0; i < balls.size(); i++){
				if(balls.get(i).x >-20 && balls.get(i).x < viewW+20 && balls.get(i).y >= 0-20 && balls.get(i).y < viewH+20)
				g2d.setColor(new Color(107, 107, 107));
				g2d.fillOval(balls.get(i).x, balls.get(i).y, 20, 20);
			}
		}
		
		if(gameStage == 2){
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, viewW, viewH);
			g2d.setColor(Color.red);
			g2d.setFont(new Font(null,Font.CENTER_BASELINE, 30));
			g2d.drawString("Game Over !", viewW/2-100,viewH/2);
			g2d.drawString("Record : "+endTime/1000.0, viewW/2-100,viewH/2+30);
			g2d.drawString("Rank : "+rank(endTime/1000.0), viewW/2-100,viewH/2+60);
		}
	}
	
	private class Ball {
		int x, y, dx, dy;
		Ball(int x, int y){
			this.x = x; this.y = y; 
			dx = (x == shipX )? 0 : (x > shipX ? -5-(int)(Math.random()*3) : 5+(int)(Math.random()*3)); 
			dy = (y == shipY )? 0 : (y > shipY ? -5-(int)(Math.random()*3) : 5+(int)(Math.random()*3));
		}

	}
}
