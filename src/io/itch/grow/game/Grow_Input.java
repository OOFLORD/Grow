package io.itch.grow.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Grow_Input extends KeyAdapter
{
	private Grow_Handler handler;
	
	public Grow_Input(Grow_Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			Grow_GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == Grow_ID.Quark)
			{
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_A) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_D) tempObject.setVelY(5);
			}
		}
		
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
}
