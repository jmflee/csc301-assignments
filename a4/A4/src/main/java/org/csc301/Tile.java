package org.csc301;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	private static final int SIZE = 32;
	int xNot, yNot;
	int x, y;
	RunGame game;
	
	
	public Tile(int x, int y, RunGame game) {
		this.xNot = x;
		this.yNot = y;
		this.game = game;
	}
	
	public void tick(RunGame game) {
		this.game = game;
		y = yNot + game.yOffset;
		x = xNot + game.xOffset;
	}
	
	public void renderWater(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE -1, SIZE -1);
	}

	public void renderIslands(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE -1, SIZE -1);
	}
	
	public void renderBoat(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE -1, SIZE -1);
	}
	
	public void renderTreasure(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE -1, SIZE -1);
	}
	
	public void renderPath(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE -1, SIZE -1);
	}
}
