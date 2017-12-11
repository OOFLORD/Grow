package io.itch.grow.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Grow_Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -6225809831567069206L;
	public final static int WIDTH = 800;
	public final static int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private Grow_Handler handler;
	private Grow_Menu menu;
	private Big_Bang bb;
	private Info_HUD IHUD;
	private QuarkEpoch qe;
	
	boolean is_running = false;
	
	public String TimePeriod = "Planck Epoch";
	public String Age = "???";
	public String Length = "???";
	
	Font magnetoTitle;
	Font insaneHoursHUD;
	
	public enum GAMESTATE
	{
		Menu,
		BigBang,
		PlanckEpoch,
		QuarkEpoch,
		Options,
		Info,
		Exit
	};
	
	public GAMESTATE gameState = GAMESTATE.Menu;
	
	public Grow_Game() throws FontFormatException, IOException
	{
		handler = new Grow_Handler();
		menu = new Grow_Menu(this);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addKeyListener(new Grow_Input(handler));
		this.setIgnoreRepaint(true);
		
		File f = new File("src/io/itch/grow/game/res/magneto.ttf");
		FileInputStream in = new FileInputStream(f);
		Font magneto = Font.createFont(Font.TRUETYPE_FONT, in);
		magnetoTitle = magneto.deriveFont(75f);
		
		
		File f2 = new File("src/io/itch/grow/game/res/insanehours.ttf");
		FileInputStream in2 = new FileInputStream(f2);
		Font insaneHours = Font.createFont(Font.TRUETYPE_FONT, in2);
		insaneHoursHUD = insaneHours.deriveFont(12f);
		
		new Grow_Window(WIDTH, HEIGHT, "Grow", this);
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		bb = new Big_Bang(this);
		this.addKeyListener(bb);
		
		IHUD = new Info_HUD(this);
		this.addKeyListener(IHUD);
		
		qe = new QuarkEpoch(this);
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		is_running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			is_running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run() 
	{
		long lastTime = System.nanoTime();
		double ns = 1000000000D / 60D;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int ticks = 0;
		while(is_running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			boolean shouldRender = false;
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}
			if(shouldRender)
			{
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer >= 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames + ", UPS: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		handler.tick();
		
		if(gameState == GAMESTATE.Menu)
		{
			menu.tick();
		}
		else if(gameState == GAMESTATE.BigBang)
		{
			bb.tick();
		}
		else if(gameState == GAMESTATE.PlanckEpoch)
		{
			IHUD.tick();
		}
		else if(gameState == GAMESTATE.QuarkEpoch)
		{
			qe.tick();
		}
		else if(gameState == GAMESTATE.Options)
		{
			
		}
		else if(gameState == GAMESTATE.Info)
		{
			
		}
		else if(gameState == GAMESTATE.Exit)
		{
			is_running = false;
			System.exit(0);
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == GAMESTATE.Menu)
		{
			menu.render(g);
		}
		else if(gameState == GAMESTATE.BigBang)
		{   
			bb.render(g);
		}
		else if(gameState == GAMESTATE.PlanckEpoch)
		{
			g2.setColor(Color.white);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			IHUD.render(g);
		}
		else if(gameState == GAMESTATE.QuarkEpoch)
		{
			g2.setColor(Color.white);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			qe.render(g);
		}
		else if(gameState == GAMESTATE.Options)
		{
			
		}
		else if(gameState == GAMESTATE.Info)
		{
			
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[])
	{
		try 
		{
			new Grow_Game();
		} 
		catch (FontFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}