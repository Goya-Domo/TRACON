package com.TRACON.main;

import java.awt.Color;
import java.awt.Graphics;

public class Aircraft extends GameObject {

	private PositionVector posVector;

	private int xInOneMinute;
	private int yInOneMinute;
	
	private boolean dragged, haloOn;
	
	public static Aircraft selected;
		
	public Aircraft(int x, int y, ID id, int heading, int speed, int alt)
	{
		super(x, y, id);
		
		size = 6;
		
		posVector = new PositionVector(x, y, heading, speed, alt);
		
		xInOneMinute = x + posVector.calcMinuteXStep();
		yInOneMinute = y + posVector.calcMinuteYStep();
	}

	@Override
	public void tick()
	{
		posVector.updatePosition();
	}
	
	@Override
	public void updateTick()
	{		
		x = posVector.getX();
		y = posVector.getY();

		xInOneMinute = x + posVector.calcMinuteXStep();
		yInOneMinute = y + posVector.calcMinuteYStep();
	}

	@Override
	public void render(Graphics g) 
	{		
		g.setColor(Color.GREEN);
		g.drawRect(x - 4, y - 4, 8, 8);
		g.drawLine(x, y, xInOneMinute, yInOneMinute);
		
		if (Aircraft.selected == this || haloOn)
		{
			g.drawOval(this.x - (3 * Game.PIXELSPERMILE), this.y - (3 * Game.PIXELSPERMILE), (6 * Game.PIXELSPERMILE), (6 * Game.PIXELSPERMILE));
		}
	}
	
	public boolean isBeingDragged()
	{
		return dragged;
	}
	
	public boolean isHaloOn()
	{
		return haloOn;
	}
		
    public void setGivenHeading(int heading)
    {
    	posVector.setGivenHeading(heading);
    }
    
    public void setGivenSpeed(int speed)
    {
    	posVector.setGivenSpeed(speed);
    }
    
    public void setGivenAltitude(int altitude)
    {
    	posVector.setGivenAltitude(altitude);
    }
    
    public void setDragged(boolean drag)
    {
    	dragged = drag;
    }
    
    public void setHalo(boolean haloOn)
    {
    	this.haloOn = haloOn;
    }
    
    public void toggleHalo()
    {
    	this.haloOn = !haloOn;
    }
}
