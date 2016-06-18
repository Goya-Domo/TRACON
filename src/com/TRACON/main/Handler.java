package com.TRACON.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	private LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick()
	{
		for (GameObject gObject : objects)
		{
			gObject.tick();
		}
	}
	
	public void updateTick()
	{
		for (GameObject gObject : objects)
		{
			gObject.updateTick();
		}
	}
	
	public void render(Graphics g)
	{
		for (GameObject gObject : objects)
		{
			gObject.render(g);
		}
		
		if (Datablock.getReadout() != null)
		{
			Datablock.getReadout().render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.objects.remove(object);
	}
	
	public LinkedList<GameObject> getObjectList()
	{
		return objects;
	}
}
