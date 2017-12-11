package io.itch.grow.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Grow_Window extends Canvas
{
	private static final long serialVersionUID = 6755829471197407342L;

	public Grow_Window(int width, int height, String title, Grow_Game game)
	{
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
