package com.TRACON.main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Datablock implements Clickable{
	
	private String content;
	
	private int x, y, fontSize;
	
	private static Datablock readout;

	public Datablock(int x, int y, String content)
	{
		this.x = x;
		this.y = y;
		
		this.content = content;
	}
	
	@Override
	public void leftClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragAction(MouseEvent e) 
	{
		this.x = e.getX();
		this.y = e.getY();
	}

	@Override
	public void mouseDragReleaseAction(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void updateDatablock(int x, int y, String content)
	{
		this.x = x;
		this.y = y;
		
		this.content = content;
	}
	
	public void updateDatablock(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void updateDatablock(String content)
	{
		this.content = content;
	}
	
	public void render(Graphics g)
	{
		g.drawString(content, x, y);
	}
	
	public static Datablock getReadout()
	{
		return readout;
	}
	
	public static void setReadout(Datablock readout)
	{
		Datablock.readout = readout;
	}
}
