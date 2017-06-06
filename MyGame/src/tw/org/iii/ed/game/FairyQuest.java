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
	private Image imgFairy = GameUtil.getImage("img/fairy_f02.png");
	private Image imgBG = GameUtil.getImage("img/BG003.jpg");
	private Image imgMigicBall = GameUtil.getImage("img/magicball.png");
	private Image imgBigBall = GameUtil.getImage("img/bigball.png");
	private Timer timer = new Timer();
	private int m = 0;
	private Fairy fairy = new Fairy(1, 500, 100, 100);
	private static int scale;
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
		timer.schedule(new ViewTask(),0, 20);
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
					fairy.shot();
					break;
				case 37:
					fairy.setMove("LEFT");
					System.out.println(fairy.move);
					break;
				case 38:
					fairy.setMove("UP");
					System.out.println(fairy.move);
					break;
				case 39:
					fairy.setMove("RIGHT");
					System.out.println(fairy.move);
					break;
				case 40:
					fairy.setMove("DOWN");
					System.out.println(fairy.move);
					break;	
			}
		}
		
		public void keyReleased(KeyEvent e){
			switch (e.getKeyCode()){
				case 37: case 39:
					fairy.setMove("h-NONE");
					System.out.println(fairy.move);
					break;
				case 38: case 40:
					fairy.setMove("v-NONE");
					System.out.println(fairy.move);
					break;
			}
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			fairy.move();
			if(fairy.balls != null){
				for(int i = 0; i < fairy.balls.size(); i++) {
					Ball ball = fairy.balls.get(i);
					ball.move();
					ball.imgCount++;
					if(ball.imgCount >= 180) ball.imgCount = 0;					
				}
			}
			fairy.imgCount++;
			if(fairy.imgCount >= 18) fairy.imgCount = 0;
			m++;
			if(m >= 788) m = 0;
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		viewW = getWidth(); viewH=getHeight();
		Graphics2D g2d= (Graphics2D)g;
		g2d.clearRect(0, 0, viewW, viewH);
		
		g2d.drawImage(imgBG, 0, 0, viewW, viewH, 0 + m, 0, 1280 + m, 600, null);
		
		if(fairy.balls != null){
			for(int i = 0; i < fairy.balls.size(); i++){
				Ball ball = fairy.balls.get(i);
				g2d.drawImage(
						imgMigicBall, ball.x, ball.y,
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
		g2d.drawImage(
				imgFairy, fairy.x, fairy.y, 
				fairy.x + fairy.width, fairy.y + fairy.height,
				0 + fairy.imgCount/6*50, 84, 
				(fairy.imgCount/6+1)*50, 126, null
		);
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
