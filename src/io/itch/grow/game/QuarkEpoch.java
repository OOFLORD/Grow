package io.itch.grow.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class QuarkEpoch 
{
	Grow_Game game;
	
	public QuarkEpoch(Grow_Game game)
	{
		this.game = game;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(Grow_Game.WIDTH/2-400, Grow_Game.HEIGHT/2-300, 340, 30);
		g2.setFont(game.insaneHoursHUD);
		g2.setColor(Color.orange);
		g2.drawString("Time Period - " + game.TimePeriod, 10, 17);
		g2.setColor(Color.black);
		g2.fillRect(Grow_Game.WIDTH/2-60, Grow_Game.HEIGHT/2-300, 222, 30);
		g2.setFont(game.insaneHoursHUD);
		g2.setColor(Color.magenta);
		g2.drawString("Age - " + game.Age, 300, 17);
		g2.setColor(Color.black);
		g2.fillRect(Grow_Game.WIDTH/2+162, Grow_Game.HEIGHT/2-300, 222, 30);
		g2.setFont(game.insaneHoursHUD);
		g2.setColor(Color.cyan);
		g2.drawString("Length - " + game.Length, 515, 17);
		g2.setColor(Color.black);
		g2.fillRect(Grow_Game.WIDTH/2-360, Grow_Game.HEIGHT/2-240, 705, 5);
		g2.fillRect(Grow_Game.WIDTH/2-360, Grow_Game.HEIGHT/2-258, 5, 40);
		g2.fillRect(Grow_Game.WIDTH/2+340, Grow_Game.HEIGHT/2-258, 5, 40);
	}
}
