package io.itch.grow.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import io.itch.grow.game.Grow_Game.GAMESTATE;

public class Info_HUD extends KeyAdapter
{
	Grow_Game game;
	
	boolean timePeriod = false;
	boolean age = false;
	boolean length = false;
	boolean scale = false;
	boolean text = false;
	boolean bigbang = false;
	
	int entercounter = 0;
	
	public Info_HUD(Grow_Game game)
	{
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER && entercounter == 0 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			timePeriod = true;
		}
		else if(key == KeyEvent.VK_ENTER && entercounter == 1 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			age = true;
		}
		else if(key == KeyEvent.VK_ENTER && entercounter == 2 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			length = true;
		}
		else if(key == KeyEvent.VK_ENTER && entercounter == 3 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			scale = true;
		}
		else if(key == KeyEvent.VK_ENTER && entercounter == 4 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			text = true;
		}
		else if(key == KeyEvent.VK_ENTER && entercounter == 5 && game.gameState == GAMESTATE.PlanckEpoch)
		{
			entercounter++;
			bigbang = true;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER)
		{
			
		}
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) 
	{
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
	
	public void drawCenteredStringVertical(Graphics g, String text, Rectangle rect, Font font) 
	{
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y;
	    g.setFont(font);
	    g.drawString(text, x, y);
	}

	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.PLAIN, 10));
		g2.drawString("Click    Enter    to continue", Grow_Game.WIDTH/2+250, Grow_Game.HEIGHT/2+250);
		g2.drawRect(Grow_Game.WIDTH/2+281, Grow_Game.HEIGHT/2+240, 32, 12);
		
		if(timePeriod)
		{	
			g2.setColor(Color.black);
			g2.fillRect(Grow_Game.WIDTH/2-400, Grow_Game.HEIGHT/2-300, 340, 30);
			g2.setFont(game.insaneHoursHUD);
			g2.setColor(Color.orange);
			g2.drawString("Time Period - " + game.TimePeriod, 10, 17);
			if(!age)
			{
				g2.setFont(new Font("Arial", Font.PLAIN, 10));
				g2.setColor(Color.black);
				drawCenteredString(g, "Gained time period information", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
			}
			
			if(age)
			{
				g2.setColor(Color.black);
				g2.fillRect(Grow_Game.WIDTH/2-60, Grow_Game.HEIGHT/2-300, 222, 30);
				g2.setFont(game.insaneHoursHUD);
				g2.setColor(Color.magenta);
				g2.drawString("Age - " + game.Age, 300, 17);
				if(!length)
				{
					g2.setFont(new Font("Arial", Font.PLAIN, 10));
					g2.setColor(Color.black);
					drawCenteredString(g, "Gained age of the universe", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
				}
				
				if(length)
				{
					g2.setColor(Color.black);
					g2.fillRect(Grow_Game.WIDTH/2+162, Grow_Game.HEIGHT/2-300, 222, 30);
					g2.setFont(game.insaneHoursHUD);
					g2.setColor(Color.cyan);
					g2.drawString("Length - " + game.Age, 515, 17);
					if(!scale)
					{
						g2.setFont(new Font("Arial", Font.PLAIN, 10));
						g2.setColor(Color.black);
						drawCenteredString(g, "Gained length of the universe", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
					}
					
					if(scale)
					{
						g2.setColor(Color.black);
						g2.fillRect(Grow_Game.WIDTH/2-360, Grow_Game.HEIGHT/2-240, 705, 5);
						g2.fillRect(Grow_Game.WIDTH/2-360, Grow_Game.HEIGHT/2-258, 5, 40);
						g2.fillRect(Grow_Game.WIDTH/2+340, Grow_Game.HEIGHT/2-258, 5, 40);
						if(!text)
						{
							g2.setFont(new Font("Arial", Font.PLAIN, 10));
							g2.setColor(Color.black);
							drawCenteredString(g, "Gained scale", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
						}
						
						if(text)
						{
							if(!bigbang)
							{
								g2.setFont(new Font("Arial", Font.PLAIN, 10));
								g2.setColor(Color.black);
								drawCenteredString(g, "Please wait a planck time (5.39E−44 seconds)", new Rectangle(Grow_Game.WIDTH/2-10, Grow_Game.HEIGHT/2-25, 1, 1), new Font("", Font.PLAIN, 15));
								drawCenteredStringVertical(g, "before the universe is the size of a planck length (1.6E−35 m)", new Rectangle(Grow_Game.WIDTH/2-9, Grow_Game.HEIGHT/2, 1, 1), new Font("", Font.PLAIN, 15));
								drawCenteredStringVertical(g, "and before physics make sense.", new Rectangle(Grow_Game.WIDTH/2-8, Grow_Game.HEIGHT/2+18, 1, 1), new Font("", Font.PLAIN, 15));
							}
							else if(bigbang)
							{
								game.gameState = GAMESTATE.QuarkEpoch;
							}
						}
					}
				}
			}
		}
	}
}