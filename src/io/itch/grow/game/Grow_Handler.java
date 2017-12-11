package io.itch.grow.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Grow_Handler 
{
	LinkedList<Grow_GameObject> object = new LinkedList<Grow_GameObject>();
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			Grow_GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			Grow_GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(Grow_GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(Grow_GameObject object)
	{
		this.object.remove(object);
	}
}
