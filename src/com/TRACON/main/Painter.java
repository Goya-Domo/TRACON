package com.TRACON.main;

import java.awt.Graphics;
import java.awt.Point;

public class Painter {
	
	private Point mouse;
	private int frames, numRunways, translateRunwaysX, translateRunwaysY;
	
	private int boundaryMilesWest, boundaryMilesEast, boundaryMilesNorth, boundaryMilesSouth;
	
	//temp: Number of pixels to move the left edge of the Sector to the right
	private final int mRight = 150;
	
	private int[] x = {(Game.WIDTH / 2) - (Game.PIXELSPERMILE * 25) + mRight, (Game.WIDTH / 2) + (Game.PIXELSPERMILE * 25), (Game.WIDTH / 2) + (Game.PIXELSPERMILE * 30), (Game.WIDTH / 2) + (Game.PIXELSPERMILE * 30),  (Game.WIDTH / 2) + (Game.PIXELSPERMILE * 25), (Game.WIDTH / 2) - (Game.PIXELSPERMILE * 25) + mRight, (Game.WIDTH / 2) - (Game.PIXELSPERMILE * 30) + mRight, (Game.WIDTH / 2) - (Game.PIXELSPERMILE * 30) + mRight};
	private int[] y = {(Game.HEIGHT / 2) - (Game.PIXELSPERMILE * 20), (Game.HEIGHT / 2) - (Game.PIXELSPERMILE * 20), (Game.HEIGHT / 2) - (Game.PIXELSPERMILE * 15), (Game.HEIGHT / 2) + (Game.PIXELSPERMILE * 15), (Game.HEIGHT / 2) + (Game.PIXELSPERMILE * 20), (Game.HEIGHT / 2) + (Game.PIXELSPERMILE * 20), (Game.HEIGHT / 2) + (Game.PIXELSPERMILE * 15), (Game.HEIGHT / 2) - (Game.PIXELSPERMILE * 15)};
	
	public Painter()
	{
		numRunways = 1;
		
		translateRunwaysX = -50;
	}
	
	public void render(Graphics g)
	{
		g.drawPolygon(x, y, 8);
		
		g.drawRect((Game.WIDTH / 2 + translateRunwaysX) - Game.PIXELSPERMILE, (Game.HEIGHT / 2 + translateRunwaysY) - (Game.PIXELSPERMILE / 2), Game.PIXELSPERMILE * 2, Game.PIXELSPERMILE / 5);
		g.drawRect((Game.WIDTH / 2 + translateRunwaysX) - Game.PIXELSPERMILE, (Game.HEIGHT / 2 + translateRunwaysY) + (Game.PIXELSPERMILE / 2), Game.PIXELSPERMILE * 2, Game.PIXELSPERMILE / 5);
		
        for(int rWay = 0; rWay < numRunways; rWay++)
        {
            for (int n = 0; n <= 3; n++)
            {
                g.drawLine( (Game.WIDTH / 2 + translateRunwaysX) + Game.PIXELSPERMILE * 4 + (n * Game.PIXELSPERMILE * 4), (Game.HEIGHT / 2 + translateRunwaysY) - (Game.PIXELSPERMILE / 2) + (Game.PIXELSPERMILE / 10) + (Game.PIXELSPERMILE * rWay), 
                    (Game.WIDTH / 2 + translateRunwaysX) + Game.PIXELSPERMILE * 4 + (n * Game.PIXELSPERMILE * 4) + (Game.PIXELSPERMILE * 2), Game.HEIGHT / 2 + translateRunwaysY - (Game.PIXELSPERMILE / 2) + (Game.PIXELSPERMILE / 10) + ((Game.PIXELSPERMILE) * rWay));
            }
        }
        
		if (Aircraft.selected != null)
		{
			if (Aircraft.selected.isBeingDragged())
			{				
				if (mouse != null)
				{
					int dx = (int)mouse.getX() - Aircraft.selected.getX();
					int dy = (int)mouse.getY() - Aircraft.selected.getY();
					
					g.drawLine(Aircraft.selected.getX(), Aircraft.selected.getY(), (int)mouse.getX(), (int)mouse.getY());
					
                    int newHeading = (int)Math.toDegrees(Math.atan2(dx, -1 * dy));

                    if (dx < 0)
                    {
                        newHeading += 360;                
                    }
                    else
                    {
                        if (newHeading > 360)
                        {
                            newHeading -= 360;
                        }
                        else
                        {
                            if (newHeading <= 0)
                            {
                                newHeading += 360;
                            }
                        }                        
                    }					
					g.drawString(String.valueOf(newHeading), (int)mouse.getX() - 20, (int)mouse.getY() - 20);
				}
			}
		}
		
		g.drawString("FPS: " + String.valueOf(frames), 30, 15);
	}
	
	public void updateMouse(Point a)
	{
		mouse = a;
	}
	
	public void updateFrames(int frames)
	{
		this.frames = frames;
	}
	
	public void toggleLeftRunway()
	{
		numRunways = (numRunways == 1) ? 2 : 1;
	}
	
	public void translate(int dx, int dy)
	{
		for (int ndx = 0; ndx < x.length; ndx++)
		{
			x[ndx] += dx;
		}
		
		for (int ndx = 0; ndx < y.length; ndx++)
		{
			y[ndx] += dy;
		}
		
		translateRunwaysX += dx;
		translateRunwaysY += dy;
	}
}
