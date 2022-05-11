package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MouseListener MouseListener;
	private int mouseX;
	
	PowerUps powerups; //them
	Ball ball;
	Paddle paddle;
	Map map;
	HUD HUD;
	
	public GamePanel() {
		init();
	}
	
	public void init() {
		mouseX = 0;
		ball = new Ball();
		paddle = new Paddle();
		map = new Map(6, 10);
		HUD = new HUD();
		powerups = new PowerUps(250, 200, "powerUps1"); //them
		MouseListener = new MouseListener();
		addMouseMotionListener(MouseListener);
		
		running = true;
		image = new BufferedImage(MainMenu.WIDTH, MainMenu.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	@Override
	public void run() {
		while (running) { 
			update();
			
			draw();
			
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkCollisions() {
		Rectangle ballRect = ball.getRect();
		Rectangle paddleRect = paddle.getRect();
		
		if (ballRect.intersects(paddleRect)) {
			ball.setDY(-ball.getDY());
			if(ball.getX() < mouseX + paddle.getWidth()/4) {
				ball.setDX(ball.getDX() - 0.5);
			}
			if(ball.getX() < mouseX + paddle.getWidth() && ball.getX() > mouseX + paddle.getWidth()/4) {
				ball.setDX(ball.getDX() + 0.5);
			}
		}
		
		int[][] mapArray = map.getMapArray();
		A: for (int row = 0; row < mapArray.length; row++) {
			for (int col = 0; col < mapArray[0].length; col++) {
				if (mapArray[row][col] > 0) {
						
					int brickx = col*map.getBrickWidth() + map.HOR_PAD; 
					int bricky = row*map.getBrickHeight() + map.VERT_PAD;
					int width = map.getBrickWidth();
					int height = map.getBrickHeight();
					
					Rectangle brickRect = new Rectangle(brickx, bricky, width, height); 
					
					if (ballRect.intersects(brickRect)) {
						map.hitBrick(row, col);
						ball.setDY(-ball.getDY());
						HUD.addScore();
						
						break A;
					}
				}
			}
		}
	}
	
	public void update() {
		checkCollisions();
		ball.update();
	}
	
	public void draw() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, MainMenu.WIDTH, MainMenu.HEIGHT); 
		
		ball.draw(g);
		paddle.draw(g);
		map.draw(g);
		HUD.draw(g);
		powerUps.draw(g);
		
		if (map.winCheck() == true) {
			drawWin();
		}
		if (ball.getLose() == true) {
			drawLose();
		}
	}
	
	public void drawWin() {
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Win!", 240, 250);
	}
	
	public void drawLose() {
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Lose", 255, 250);
		g.drawString("Score: " + HUD.getScore(), 255, 275);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, 0, 0, MainMenu.WIDTH, MainMenu.HEIGHT, null);
		g2.dispose();
		
	}
	
	private class MouseListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			paddle.mouseMoved(e.getX()-50);
		}
	}
}
	