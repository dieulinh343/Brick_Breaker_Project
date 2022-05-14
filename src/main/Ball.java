package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Constants;

public class Ball {
	static private double x = 200 ,y = 200;
	static private double dx =3, dy =5;
	private int ballSize;
	private boolean lose = false;
	
	
	public Ball(int size) {
		
		
		this.ballSize = size;
		
	}

	
	public void update() {
		setPosition();
	}
	
	public void setPosition() {
		x += dx;
		y += dy;
		
		if (x < 0) {
			this.dx = -this.dx;
		} 
		if (y < 0) {
			this.dy = -this.dy;
		}
		if (x > MainMenu.WIDTH - (ballSize*2-7)) {
			this.dx = -this.dx;
		}
		if (y > MainMenu.HEIGHT - ballSize*2) {
			this.lose = true;
		}
	}
	
	public boolean getLose() {return this.lose;}
	
	public void draw(Graphics2D g) {
		 g.setColor(Constants.BALL_COLOR);
		 g.setStroke(new BasicStroke(4));
		 g.drawOval((int) x, (int) y, ballSize, ballSize);
		 
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) x,(int) y, ballSize, ballSize);
	}
	
	public double getX() {return this.x;}
	
	public double getDY() {return this.dy;}
	public void setDY(double dy) {this.dy = dy;}
	
	public double getDX() {return this.dx;}
	public void setDX(double dx) {this.dx = dx;}
}
