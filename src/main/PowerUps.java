package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUps {
	private double PUx, PUy, speed;
	Image pic;
	
	public PowerUps(int x, int y, String s) {
		this.PUx = x;
		this.PUy = y;
		
		this.speed = 1;
		
		try {
			pic = ImageIO.read(new File("src/"+s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void extendPaddle(){
		PUy += speed;
		
		
	}
	
	public void draw(Graphics2D g) {
		 g.setColor(Color.DARK_GRAY);
		 g.setStroke(new BasicStroke(4));
		 g.drawOval((int) , (int) y, ballSize, ballSize);
		 
	}
	
	public void draw(Graphics2D g) {
		 g.setColor(Color.DARK_GRAY);
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
