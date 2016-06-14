package com.TRACON.main;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class MouseInputHandler extends MouseInputAdapter{
	
	Game game;
	
	public MouseInputHandler(Game game)
	{
		this.game = game;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("click");
		
		//for each GameObject in Handler's list
		for (GameObject gObject : game.getHandler().getObjectList())
		{
			//if this object was clicked on
			if (gObject.contains(e.getPoint()))
			{
				if (gObject instanceof Aircraft)
				{
					//If it's a left click on an aircraft, select that aircraft
					if (e.getButton() == MouseEvent.BUTTON1)
					{
						Aircraft.selected = (Aircraft)gObject;
						
						System.out.println("Aircraft selected");
						
						return;
					}
					else
					{
						//if right click on an aircraft, toggle halo rendering
						if (e.getButton() == MouseEvent.BUTTON3)
						{
							if (gObject.contains(e.getPoint()))
							{
								((Aircraft)gObject).toggleHalo();
							}
						}
					}
				}
			}
		}		
		//Aircraft.selected = null;
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if (Aircraft.selected != null)
		{
			//If aircraft being dragged isn't selected, then select it
			if (Aircraft.selected.isBeingDragged() == false)
			{
				if (Aircraft.selected.contains(e.getPoint()))
				{
					game.getPainter().updateMouse(e.getPoint());
					Aircraft.selected.setDragged(true);
				}
			}
			else
			{
				game.getPainter().updateMouse(e.getPoint());
			}
		}
	}
	
	public void mousePressed(MouseEvent e)
 {
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			for (GameObject gObject : game.getHandler().getObjectList()) 
			{
				if (gObject.contains(e.getPoint())) 
				{
					if (gObject instanceof Aircraft) 
					{
						Aircraft.selected = (Aircraft)gObject;
						break;
					}
				}
			
			}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		System.out.println("Release");
		
		if (Aircraft.selected != null)
		{
			//If mouse released after being dragged from an aircraft
			if (Aircraft.selected.isBeingDragged())
			{
				//calculate the new heading and send it to the aircraft
				int dx = e.getX() - Aircraft.selected.getX();
				int dy = e.getY() - Aircraft.selected.getY();
				
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
                
                //Send new heading to a/c
				Aircraft.selected.setGivenHeading(newHeading);
				Aircraft.selected.setDragged(false);
			}
		}		
	}
}
