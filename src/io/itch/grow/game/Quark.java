package io.itch.grow.game;

import java.awt.Color;
import java.awt.Graphics;

public class Quark extends Grow_GameObject
{
	public Quark(int x, int y, Grow_ID id)
	{
		super(x, y, id);
	}
	
	public void tick()
	{
		
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, y, 100, 100);
	}
}
