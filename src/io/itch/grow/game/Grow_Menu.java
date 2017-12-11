package io.itch.grow.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import io.itch.grow.game.Grow_Game.GAMESTATE;

public class Grow_Menu extends MouseAdapter
{		
	Grow_Game game;
	
	//Start Button
	int x1[] = { 15, 15, 230, 215 };
	int y1[] = { 150, 180, 180, 150 };
	int largex1[] = { 15, 15, 255, 235 };
	int largey1[] = { 150, 185, 185, 150 };
	Polygon poly1 = new Polygon(x1, y1, 4);
	boolean starthover = false;
	
	//Options Button
	int x2[] = { 15, 15, 230, 215 };
	int y2[] = { 200, 230, 230, 200 };
	int largex2[] = { 15, 15, 255, 235 };
	int largey2[] = { 200, 235, 235, 200 };
	Polygon poly2 = new Polygon(x2, y2, 4);
	boolean optionshover = false;
	
	//Info Button
	int x3[] = { 15, 15, 230, 215 };
	int y3[] = { 250, 280, 280, 250 };
	int largex3[] = { 15, 15, 255, 235 };
	int largey3[] = { 250, 285, 285, 250 };
	Polygon poly3 = new Polygon(x3, y3, 4);
	boolean infohover = false;
	
	//Exit Button
	int x4[] = { 15, 15, 230, 215 };
	int y4[] = { 300, 330, 330, 300 };
	int largex4[] = { 15, 15, 255, 235 };
	int largey4[] = { 300, 335, 335, 300 };
	Polygon poly4 = new Polygon(x4, y4, 4);
	boolean exithover = false;
	
	boolean start = false;
	float alpha = 0.0f;
	float wait = 0.0f;
	
	public Grow_Menu(Grow_Game game)
	{
		this.game = game;
	}

	public void mouseMoved(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(poly1.contains(mx, my) && !start) // Start Button hover handling 
		{
			poly1 = new Polygon(largex1, largey1, 4);
			starthover = true;
		}
		else if(poly2.contains(mx, my) && !start) //Options Button hover handling
		{
			poly2 = new Polygon(largex2, largey2, 4);
			optionshover = true;
		}
		else if(poly3.contains(mx, my) && !start) //Info Button hover handling
		{
			poly3 = new Polygon(largex3, largey3, 4);
			infohover = true;
		}
		else if(poly4.contains(mx, my) && !start) //Exit Button hover handling
		{
			poly4 = new Polygon(largex4, largey4, 4);
			exithover = true;
		}
		else //Reset if mouse is not hovering over a button
		{
			if(start)
			{
				
			}
			else
			{
				poly1 = new Polygon(x1, y1, 4);
				starthover = false;
			}
			poly2 = new Polygon(x2, y2, 4);
			poly3 = new Polygon(x3, y3, 4);
			poly4 = new Polygon(x4, y4, 4);
			optionshover = false;
			infohover = false;
			exithover = false;
		}
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(poly1.contains(mx, my)&& !start) // Start Button click 
		{
			start = true;
		}
		else if(poly2.contains(mx, my) && !start) //Options Button click
		{
			game.gameState = GAMESTATE.Options;
		}
		else if(poly3.contains(mx, my)&& !start) //Info Button click
		{
			game.gameState = GAMESTATE.Info;
		}
		else if(poly4.contains(mx, my)&& !start) //Exit Button click
		{
			game.gameState = GAMESTATE.Exit;
		}
	}
	
	public void tick() 
	{
		if(start == true)
		{
			if(wait >= 1)
		    {
				alpha += 0.01f;
		    }
		    if (alpha >= 1.0f)
		    {
		    	alpha = 1.0f;
		    	game.gameState = GAMESTATE.BigBang;
		    }
		    else game.repaint();
		    wait += 0.02f;
		}
	}

	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(game.magnetoTitle);
		g2.setColor(Color.blue);
		g2.drawString("Grow", 10, 65);
		g2.setColor(Color.white);
		
		//Start Button
		if(!starthover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.fillPolygon(poly1);
			g2.setColor(Color.black);
			g2.drawString("Start", 20, 175);
			g2.setColor(Color.white);
		}
		else if(starthover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 35));
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillPolygon(poly1);
			g2.setColor(Color.black);
			g2.drawString("Start", 20, 180);
			g2.setColor(Color.white);
		}
		
		//Options Button
		if(!optionshover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.fillPolygon(poly2);
			g2.setColor(Color.black);
			g2.drawString("Options", 20, 223);
			g2.setColor(Color.white);
		}
		else if(optionshover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 35));
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillPolygon(poly2);
			g2.setColor(Color.black);
			g2.drawString("Options", 20, 227);
			g2.setColor(Color.white);
		}
		
		//Info Button
		if(!infohover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.fillPolygon(poly3);
			g2.setColor(Color.black);
			g2.drawString("Info", 20, 275);
			g2.setColor(Color.white);
		}
		else if(infohover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 35));
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillPolygon(poly3);
			g2.setColor(Color.black);
			g2.drawString("Info", 20, 280);
			g2.setColor(Color.white);
		}
	
		//Exit Button
		if(!exithover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.fillPolygon(poly4);
			g2.setColor(Color.black);
			g2.drawString("Exit", 20, 325);
			g2.setColor(Color.white);
		}
		else if(exithover)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 35));
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillPolygon(poly4);
			g2.setColor(Color.black);
			g2.drawString("Exit", 20, 330);
			g2.setColor(Color.white);
		}
		
		if (start == true)
		{
			g2.setColor(Color.white);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.fillRect(0, 0, Grow_Game.WIDTH, Grow_Game.HEIGHT);
		    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
	}
}
