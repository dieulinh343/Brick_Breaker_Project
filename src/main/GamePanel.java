package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import main.Constants;


public class GamePanel extends JPanel implements Runnable {
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MouseListener MouseListener;
	private int mouseX;
	int paddleWidth = 75;
	int ballSize = 15; 
	int powerSize1=20, powerSize2 =20;
	
	PowerUps powerup1, powerup2, powerup3; //them
	Ball ball;
	Paddle paddle;
	Map map;
	HUD HUD;
	int count1, count2, count3, count4, count5 =0;
	
	public GamePanel() {
		init();
	}
	
	public void init() {
		mouseX = 0;
		ball = new Ball(ballSize);
		paddle = new Paddle(paddleWidth);
		map = new Map(6, 10);
		HUD = new HUD();
		powerup1 = new PowerUps(190, 75, powerSize1, "powerUp1.png");
		powerup2 = new PowerUps(287, 166, powerSize2, "powerUp4.png");//them
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
		Rectangle powerupRect1 = powerup1.getRect();
		Rectangle powerupRect2 = powerup2.getRect();
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
		
		if (powerupRect1.intersects(paddleRect) && count1 < 2) {
			paddleWidth += 30;
			paddle = new Paddle(paddleWidth);
			count1 +=1;
			if (count1 ==1) {
				powerup1.delPower();
			}
		}
		
		if (powerupRect2.intersects(paddleRect) && count2 < 2) {
			ballSize += 10;
			ball = new Ball(ballSize);
			count2 +=1;
			if (count2 ==1) {
				powerup2.delPower();
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
						if (row == 1 && col == 2 && count3 == 0) {
							map.hitBrick(row, col);
							ball.setDY(-ball.getDY());
							HUD.addScore();
							count3+=1;
							powerSize1=35;
							powerup1 = new PowerUps(190, 75, powerSize1, "powerUp1.png");
						}
						else if (row == 5 && col == 4 && count4 == 0){
							
							map.hitBrick(row, col);
							ball.setDY(-ball.getDY());
							HUD.addScore();
							count4 +=1;
							powerSize2 = 35;
							powerup2 = new PowerUps(287, 166, powerSize2, "powerUp4.png");
						}
						else {
						map.hitBrick(row, col);
						ball.setDY(-ball.getDY());
						HUD.addScore();
						
						break A;
					}
					}
				}
			}
		}
	}
	
	public void update() {
		checkCollisions();
		if (count5 <100) {count5+=1;
		}
		else{ball.update();}
		if (powerSize1 == 35) {
			powerup1.move();
		}
		if (powerSize2 == 35) {
			powerup2.move();
		}
		
	}
	
	public void draw() {
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, MainMenu.WIDTH, MainMenu.HEIGHT); 
		
		ball.draw(g);
		paddle.draw(g);
		map.draw(g);
		HUD.draw(g);
		powerup1.draw(g, this);
		powerup2.draw(g, this);
		
		if (map.winCheck() == true) {
			drawWin();
			
		}
		if (ball.getLose() == true) {
			drawLose();
			
		}
	}
	
	public void drawWin() {
		running = false;
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Win!", 255, 250);
		
		g.setFont(new Font("serif",Font.BOLD, 20));
		g.drawString("Press Enter to Restart", 220, 270);
	}
	
	public void drawLose() {
		running = false;
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("You Lose", 255, 250);
		g.drawString("Score: " + HUD.getScore(), 255, 275);
		
		g.setFont(new Font("serif",Font.BOLD, 20));
		g.drawString("Press Enter to Restart", 220, 295);
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
	