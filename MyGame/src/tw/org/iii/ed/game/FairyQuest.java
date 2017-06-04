package tw.org.iii.ed.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;


public class FairyQuest extends JPanel{
	private int viewW, viewH;
	private JPanel menuBar;
	private Image imgFairy = GameUtil.getImage("img/fairy_f02.png");
	private Image imgBG = GameUtil.getImage("img/BG003.jpg");
	private int mouseX, mouseY;
	private Timer timer = new Timer();
	private int n = 0;
	private int m = 0;
	
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
		timer.schedule(new ViewTask(),0, 16);
		add(menuBar, BorderLayout.NORTH);
		addMouseMotionListener(new MyMouseAdapter());
	}
	
	private class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX(); mouseY = e.getY();
		}
	}
	
	private class ViewTask extends TimerTask{
		@Override
		public void run() {
			n++;
			m++;
			if(n >= 18) n = 0;
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
//		g2d.setColor(new Color(0, 127, 128));
//		g2d.fillRect(0, 0, viewW, viewH);
		
		g2d.drawImage(imgFairy, mouseX, mouseY, mouseX + 100, mouseY + 84, 0 + n/6*50, 84, (n/6+1)*50, 126, null);
	}
	
	public static void main(String[] args){
		GameFrame gf = new GameFrame();
		FairyQuest fq = new FairyQuest();
		gf.add(fq);
		gf.launchFrame(1280, 640);
	}
	
}
