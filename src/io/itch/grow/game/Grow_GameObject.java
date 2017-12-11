package io.itch.grow.game;

import java.awt.Graphics;

public abstract class Grow_GameObject 
{
	protected int x, y;
	protected Grow_ID id;
	protected int velX, velY;
	
	public Grow_GameObject(int x, int y, Grow_ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) { this.x = x; }

	
	public int getX() { return x; }
	
	public void setY(int y) { this.y = y; }
	
	public int getY() { return y; }
	
	public void setID(Grow_ID id) { this.id = id; }
	
	public Grow_ID getID() { return id; }
	
	public void setVelX(int velX) { this.velX = velX; }
	
	public int getVelX() { return velX; }
	
	public void setVelY(int velY) { this.velY = velY; }
	
	public int getVelY() { return velY; }
}
