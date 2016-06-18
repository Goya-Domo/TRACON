package com.TRACON.main;

import java.awt.event.MouseEvent;

public interface Clickable {

	public void leftClickAction();
	public void rightClickAction();
	public void mousePressAction();
	public void mouseDragAction(MouseEvent e);
	public void mouseDragReleaseAction(MouseEvent e);
	
}
