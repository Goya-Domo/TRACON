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
		//for each GameObject in Handler's list
		for (GameObject gObject : game.getHandler().getObjectList())
		{
			//if this object was clicked on
			//TODO Implement mouseClicked functions from superclass (dynamic binding)
			if (gObject.contains(e.getPoint()))
			{
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					gObject.leftClickAction();
				}
				else
				{
					if (e.getButton() == MouseEvent.BUTTON3)
					{
						gObject.rightClickAction();
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
			if (Aircraft.selected.isBeingDragged() || Aircraft.selected.contains(e.getPoint()))
			{
				Aircraft.selected.mouseDragAction(e);
			}
		}
	}
	
	public void mousePressed(MouseEvent e)
	{		
		for (GameObject gObject : game.getHandler().getObjectList())
		{			
			if (gObject.contains(e.getPoint()))
			{
				gObject.mousePressAction();
			}
		}		
	}
	
	public void mouseReleased(MouseEvent e)
	{		
		if (Aircraft.selected != null)
		{
			//If mouse released after being dragged from an aircraft
			if (Aircraft.selected.isBeingDragged())
			{
				Aircraft.selected.mouseDragReleaseAction(e);
			}
		}		
	}
}
