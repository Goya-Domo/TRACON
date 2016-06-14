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
		
		for (GameObject gObject : game.getHandler().getObjectList())
		{
			if (gObject.contains(e.getPoint()))
			{
				if (gObject instanceof Aircraft)
				{
					if (e.getButton() == MouseEvent.BUTTON1)
					{
						Aircraft.selected = (Aircraft)gObject;
						
						System.out.println("Aircraft selected");
						
						return;
					}
					else
					{
						if (e.getButton() == MouseEvent.BUTTON2)
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
			if (Aircraft.selected.isBeingDragged())
			{
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
                
				Aircraft.selected.setGivenHeading(newHeading);
				Aircraft.selected.setDragged(false);
			}
		}		
	}
}
