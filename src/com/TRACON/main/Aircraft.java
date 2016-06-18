package com.TRACON.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Aircraft extends GameObject implements Clickable{

	private PositionVector posVector;

	private int xInOneMinute;
	private int yInOneMinute;
	
	private boolean dragged, haloOn;
	
	public static Aircraft selected;
		
	public Aircraft(int x, int y, ID id, Game game, int heading, int speed, int alt)
	{
		super(x, y, id, game);
		
		size = 6;
		
		posVector = new PositionVector(x, y, heading, speed, alt);
		
		xInOneMinute = x + posVector.calcMinuteXStep();
		yInOneMinute = y + posVector.calcMinuteYStep();
	}

	//regular tick
	@Override
	public void tick()
	{
		posVector.updatePosition();
	}
	
	//"Sweep" tick, updates radar (apparent) position
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
		
		//Draw target and leader line
		g.drawRect(x - 4, y - 4, 8, 8);
		g.drawLine(x, y, xInOneMinute, yInOneMinute);
		
		//If this is the selected a/c, draw a circle around it
		if (Aircraft.selected == this)
		{
			//1 mile radius circle around target
			g.drawOval(this.x - (1 * Game.PIXELSPERMILE), this.y - (1 * Game.PIXELSPERMILE), (2 * Game.PIXELSPERMILE), (2 * Game.PIXELSPERMILE));
		}
		
		//If applicable, draw 3 mile halo
		if (this.isHaloOn())
		{
			g.drawOval(this.x - (3 * Game.PIXELSPERMILE), this.y - (3 * Game.PIXELSPERMILE), (6 * Game.PIXELSPERMILE), (6 * Game.PIXELSPERMILE));
		}
	}
	
	@Override
	public void leftClickAction()
	{
		
	}
	
	@Override
	public void rightClickAction()
	{
		this.toggleHalo();
	}
	
	@Override
	public void mousePressAction()
	{
		Aircraft.selected = this;
	}
	
	@Override
	public void mouseDragAction(MouseEvent e)
	{		
		if (Aircraft.selected == this) 
		{
			if (this.contains(e.getPoint())) 
			{
				dragged = true;	
				
				game.getPainter().updateMouse(e.getPoint());
			}
			else
			{
				game.getPainter().updateMouse(e.getPoint());
			}
		}
	}
	
	@Override
	public void mouseDragReleaseAction(MouseEvent e)
	{
		if (dragged) 
		{
			//calculate the new heading and send it to the aircraft
			int dx = e.getX() - this.getX();
			int dy = e.getY() - this.getY();
			
			int newHeading = (int) Math.toDegrees(Math.atan2(dx, -1 * dy));
			
			if (dx < 0) {
				newHeading += 360;
			} else {
				if (newHeading > 360) 
				{
					newHeading -= 360;
				} else {
					if (newHeading <= 0) 
					{
						newHeading += 360;
					}
				}
			}
			//Send new heading to a/c
			this.setGivenHeading(newHeading);
			dragged = false;
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
