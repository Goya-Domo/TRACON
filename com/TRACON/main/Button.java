package com.TRACON.main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Button extends GameObject{
	
	private String content;
	
	public Button(int x, int y, Game game, String content)
	{
		super(x, y, ID.BUTTON, game);
		
		this.content = content;	
	}

	@Override
	public void tick() {
		//Do nothing		
	}

	@Override
	public void updateTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragAction(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragReleaseAction(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
