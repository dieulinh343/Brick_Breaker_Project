package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	private int[][] map;
	private int brickHeight, brickWidth;
	
	public final int HOR_PAD = 80, VERT_PAD = 50;
	
	public Map(int row, int col) {
		initMap(row, col);
		this.brickHeight = (MainMenu.HEIGHT/2 - 2*VERT_PAD)/row; 
		this.brickWidth = (MainMenu.WIDTH - 2*HOR_PAD)/col;
	}
	
	public void initMap(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int random = (int) (Math.random()*3+1); 
				map[i][j] = random;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				if (map[row][col] > 0) {
					if (map[row][col] == 1) {
						g.setColor(new Color(200, 200, 200));
					}
					if (map[row][col] == 2) {
						g.setColor(new Color(125, 125, 125));
					}
					if (map[row][col] == 3) {
						g.setColor(new Color(50,50,50));
					}
					g.fillRect(col*this.brickWidth + this.HOR_PAD, row*this.brickHeight + this.VERT_PAD, this.brickWidth, this.brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawRect(col*this.brickWidth + this.HOR_PAD, row*this.brickHeight + this.VERT_PAD, this.brickWidth, this.brickHeight);
				}
			}
		}
	}
	
	public int getBrickWidth() {return brickWidth;}
	public int getBrickHeight() {return brickHeight;}
	
	public int[][] getMapArray() {return map;}
		
	public void setBrick(int row, int col, int value) {
		map[row][col] = value;
	}

	public void hitBrick(int row, int col) {
		map[row][col] -= 1; 
		if (map[row][col] < 0) {
			map[row][col] = 0;
		}
	}
	
	
	public boolean winCheck() {
		boolean win = false;
		int HP = 0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				HP += map[row][col];
			}
		}
		if (HP == 0) {win = true;}
		
		return win;
	}
}
