package com.TRACON.main;

import java.awt.Graphics;
import java.awt.Point;

public abstract class GameObject {
	
	protected int x, y;
	protected ID id;
	protected int size; //Radius of clickable area
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;		
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
		if (point.getX() > this.getX() - this.size && point.getX() < this.getX() + this.size) 
		{
			if (point.getY() < this.getY() + this.size && point.getY() > this.getY() - this.size) 
			{
				return true;
			}
			else
				return false;

		}
		else
			return false;
	}
}
