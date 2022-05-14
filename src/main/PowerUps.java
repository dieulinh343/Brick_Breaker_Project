package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUps {
	private int PUx, PUy, speed;
	private int powerSize;
	Image pic;
	
	public PowerUps(int x, int y, int size, String s) {
		this.PUx = x;
		this.PUy = y;
		this.powerSize = size;
		
		this.speed = 1;
		
		try {
			pic = ImageIO.read(new File("src/"+s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(){
		PUy += speed;
		
		
	}
	
	public void draw(Graphics2D g, Component c) {
		 g.drawImage(pic, PUx, PUy, powerSize , powerSize, c);
		 
	}
	public void delPower() {
		this.powerSize = 0;
	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int) PUx,(int) PUy, powerSize, powerSize);
	}
	
	public double getPUx() {return this.PUx;}
	
	public double getPUy() {return this.PUy;}

}
