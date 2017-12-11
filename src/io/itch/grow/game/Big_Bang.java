package io.itch.grow.game;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;

import javax.swing.Timer;

import io.itch.grow.game.Grow_Game.GAMESTATE;

public class Big_Bang extends KeyAdapter
{
	ActionListener fadeInText = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			bigbang = true;
		}
	};
	
	ActionListener bigbangInflate = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			alphachange = true;
		}
	};
	
	ActionListener inflation = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			bigbanggrow = true;
		}
	};
	
	ActionListener planckEpochStart = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			game.gameState = GAMESTATE.PlanckEpoch;
		}
	};
	
	Grow_Game game;
	
	Timer timer = new Timer(2000, fadeInText);
	Timer wait = new Timer(1000, bigbangInflate);
	Timer inflationTimer = new Timer(1000, inflation);
	Timer planckTimer = new Timer(500, planckEpochStart);
	
	Arc2D.Double eArcBackground = new Arc2D.Double(Grow_Game.WIDTH/2-23, Grow_Game.HEIGHT/2-31, 22, 22, 90, -360, Arc2D.OPEN);
	Arc2D.Double eArc = new Arc2D.Double(Grow_Game.WIDTH/2-23, Grow_Game.HEIGHT/2-31, 22, 22, 90, 0, Arc2D.OPEN);
	
	float alpha = 0.0f;
	double extent = 0;
	int bigbangsize = 0;
	int entercounter = 0;
	
	boolean bigbang = false;
	boolean epress = false;
	boolean bigbangstart = false;
	boolean alphachange = false;
	boolean bigbanggrow = false;
	boolean bigbangshrink = false;
	
	String bigbangtext = "Wait a second. That's too fast.";
	
	public Big_Bang(Grow_Game game)
	{
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_E)
		{
			if(alpha == 1.0f)
			{
				epress = true;
			}
		}
		else if (key == KeyEvent.VK_ENTER && bigbangsize >= Grow_Game.WIDTH && entercounter == 0)
		{
			bigbangtext = "Lets go back in time and slow down the speed.";
			entercounter++;
		}
		else if(key == KeyEvent.VK_ENTER && bigbangsize >= Grow_Game.WIDTH && entercounter == 1)
		{
			bigbangshrink = true;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == e.getKeyCode())
		{
			if(extent > -360)
			{
				extent = 0;
				epress = false;
			}
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
	
	public void tick() 
	{
		if(!bigbang)
		{
			timer.setRepeats(false);
			timer.start();
		}
		else
		{	
			if(extent <= -360)
			{
				if(!bigbangstart)
				{
					alpha = 0.0f;
					bigbangstart = true;
					wait.start();
				}
				
				if(alphachange)
				{
					if(alpha != 1.0f) alpha += 0.02f;
					if(alpha >= 1.0f) 
					{
						alpha = 1.0f;
						alphachange = false;
						inflationTimer.start();
					}
					else game.repaint();
				}
				
				if(bigbanggrow && bigbangsize <= Grow_Game.WIDTH && !bigbangshrink)
				{
					bigbangsize+= 1+bigbangsize/16;
				}
				else if(bigbangshrink)
				{
					if(bigbangsize != 0)
					{
						bigbangsize-= 1+bigbangsize/16;
					}
					else if(bigbangsize == 0)
					{
						
						planckTimer.start();
					}
				}
			}
			else
			{
				if(alpha != 1.0f) alpha += 0.01f;
				if(alpha >= 1.0f) alpha = 1.0f;
				else game.repaint();
			}
		}
	}

	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, Grow_Game.WIDTH, Grow_Game.HEIGHT);
			
		if(bigbang)
		{
		    if(extent <= -360)
		    {
		    	if(!bigbanggrow)
		    	{
			    	g2.setColor(Color.black);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				    drawCenteredString(g, "Hold    E    to Inflate", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
				    g2.setStroke(new BasicStroke(4));
				    g2.draw(eArcBackground);
				    g2.setColor(Color.white);
				    g2.setStroke(new BasicStroke(6));
				    g2.draw(eArc);
				    
			    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			    	g2.setColor(Color.white);
					g2.fillRect(0, 0, Grow_Game.WIDTH, Grow_Game.HEIGHT);
		    	}
		    	else
		    	{
		    		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
				    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		    		g2.setColor(Color.white);
					g2.fillRect(0, 0, Grow_Game.WIDTH, Grow_Game.HEIGHT);
		    		g2.setColor(Color.yellow);
		    		g2.fillOval(Grow_Game.WIDTH/2-(bigbangsize/2+10), Grow_Game.HEIGHT/2-(bigbangsize/2+20), bigbangsize, bigbangsize);
		    		
		    		if(bigbangsize >= Grow_Game.WIDTH)
		    		{
		    			g2.setColor(Color.black);
		    			drawCenteredString(g, bigbangtext, new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 20));
		    			g2.setFont(new Font("Arial", Font.PLAIN, 10));
		    			if(entercounter == 0)
		    			{
		    				g2.drawString("Click    Enter    to continue", Grow_Game.WIDTH/2+9, Grow_Game.HEIGHT/2+5);
		    				g2.drawRect(Grow_Game.WIDTH/2+40, Grow_Game.HEIGHT/2-5, 32, 12);
		    			}
		    			else
		    			{
		    				g2.drawString("Click    Enter    to continue", Grow_Game.WIDTH/2+81, Grow_Game.HEIGHT/2+5);
		    				g2.drawRect(Grow_Game.WIDTH/2+112, Grow_Game.HEIGHT/2-5, 32, 12);
		    			}
		    		}
		    	}
		    }
		    else
		    {
		    	g2.setColor(Color.black);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			    drawCenteredString(g, "Hold    E    to Inflate", new Rectangle(Grow_Game.WIDTH/2-50, Grow_Game.HEIGHT/2-25, 100, 10), new Font("", Font.PLAIN, 15));
			    g2.setStroke(new BasicStroke(4));
			    g2.draw(eArcBackground);
			    g2.setColor(Color.white);
			    g2.setStroke(new BasicStroke(7));
			    if(epress)
			    {
			    	extent-= 3;
			    }
			    eArc.setAngleExtent(extent);
			    g2.draw(eArc);
		    }
		}
	}
}
