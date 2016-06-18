package com.TRACON.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

public abstract class GameObject implements Clickable{
	
	protected int x, y;
	protected ID id;
	protected int size; //Radius of clickable area
	protected Game game;
	
	GameObject(int x, int y, ID id, Game game)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
		this.game = game;
	}
	
	public abstract void tick();	
	public abstract void updateTick();
	public abstract void render(Graphics g);
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public ID getID()
	{
		return id;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setID(ID id)
	{
		this.id = id;
	}
	
	public boolean contains(Point point)
	{
		if ((int)point.getX() > this.getX() - this.size && (int)point.getX() < this.getX() + this.size) 
		{
			return ((int)point.getY() < this.getY() + this.size && (int)point.getY() > this.getY() - this.size);

		}
		else
			return false;
	}
	
	@Override
	public void leftClickAction()
	{
		
	}
	
	@Override
	public void rightClickAction()
	{
		
	}
	
	@Override
	public void mousePressAction()
	{
		
	}
	
	@Override
	public void mouseDragReleaseAction(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseDragAction(MouseEvent e)
	{
		
	}
}
