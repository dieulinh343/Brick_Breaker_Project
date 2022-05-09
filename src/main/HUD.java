package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {
	public int score;
	
	// Constructor
	public HUD() {
		init();
	}
	
	public void init() {
		this.score = 0;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("Score: " + this.score, 20, 20);
	}
	
	public int getScore() {return this.score;}

	public void addScore() {this.score += 1;}
}
