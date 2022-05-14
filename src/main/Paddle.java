package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	static private double x = MainMenu.WIDTH/2;
	private int width, height;
	
	public final int y = MainMenu.HEIGHT - 100;
	
	public Paddle(int w) {
		this.width = w;
		this.height = 10;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Constants.PADDLE_COLOR);
		g.fillRect((int) x, y, width, height);
		
	}
	
	public void mouseMoved(int mouseX) {
		this.x = mouseX;
		if (this.x > MainMenu.WIDTH - width) {
			this.x = MainMenu.WIDTH - width;
		} 
		if (this.x < 0) {
			this.x = 0;
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) x, y, width, height);
	} 
	
	public int getWidth() {return this.width;}
}
