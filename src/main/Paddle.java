package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

	private double x;
	private int width, height;
	
	public final int y = MainMenu.HEIGHT - 100;
	
	public Paddle() {
		this.width = 75;
		this.height = 10;
		x = MainMenu.WIDTH/2 - this.width/2;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
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
