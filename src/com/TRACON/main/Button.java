package com.TRACON.main;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

//I don't think this should extend GameObject, but idk
public abstract class Button extends GameObject implements Clickable{
	
	//text to display on button
	protected String content;
	
	//rectangular size
	protected int width, height;
	
	private Button(int x, int y, Game game, String content)
	{
		this(x, y, game, content, 75, 30);
	}
	
	private Button(int x, int y, Game game, String content, int width, int height)
	{
		super(x, y, ID.BUTTON, game);
		
		this.content = content;	
		
		this.width = width;
		this.height = height;
	}

	@Override
	public void tick() {
		//Do nothing		
	}

	@Override
	public void updateTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void render(Graphics g);
	
	//How to render the text:
/*	{
		//Draw button outline
		g.drawRect(getX(), getY(), width, height);
		
		//get some data about the font (for centering the text)
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		//Calculate where the text should start using metrics
		int x = (width - metrics.stringWidth(content)) / 2 + getX();
		int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent() + getY();
		
		//draw text
		g.drawString(content,  x,  y);	
	}*/

	@Override
	public abstract void leftClickAction();
	@Override
	public abstract void rightClickAction();
	@Override
	public abstract void mousePressAction();
	@Override
	public abstract void mouseDragAction(MouseEvent e);
	@Override
	public abstract void mouseDragReleaseAction(MouseEvent e);

}
