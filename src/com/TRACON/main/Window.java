package com.TRACON.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	private static final long serialVersionUID = -4251413024404611L;
	
	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting this to true for now. Need to implement scrolling or panning in some way. And zoom
		frame.setResizable(true);
		frame.setLocationRelativeTo(null); //Windows starts in center of screen
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}	
}