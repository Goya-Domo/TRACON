package com.TRACON.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -1314911017221634787L;
	
	public static final int WIDTH = 1366, HEIGHT = WIDTH / 12*9;
	
	public static final int PIXELSPERMILE = 20, SIMSPEED = 180, TURNRATE = 3;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Painter painter;
	
	public Game()
	{
		new Window(WIDTH, HEIGHT, "TRACON", this);
		
		MouseInputHandler mousey = new MouseInputHandler(this);
		this.addMouseListener(mousey);
		this.addMouseMotionListener(mousey);
		
		handler = new Handler();
		
		Aircraft aircraft = new Aircraft(100, 100, ID.AIRCRAFT, this, 160, 180, 55);
		handler.addObject(aircraft);
		aircraft.setGivenHeading(90);
		
		handler.addObject(new Aircraft(500, 500, ID.AIRCRAFT, this, 160, 180, 55));	
	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try 
		{
			thread.join();
			running = false;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long updateTimer = System.currentTimeMillis();
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			
			if (running)
			{
				render();
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				painter.updateFrames(frames);	
				
				frames = 0;
				
				if (System.currentTimeMillis() - updateTimer > 3000)
				{
					updateTimer += 3000;
					updateTick();
				}
			}
		}
		stop();
	}
	
	private void tick()
	{
		handler.tick();
	}
	
	private void updateTick()
	{
		handler.updateTick();
	}

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if (painter == null)
		{
			painter = new Painter();
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		painter.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public Handler getHandler()
	{
		return handler;
	}
	
	public Painter getPainter()
	{
		return painter;
	}
	
	public static void main(String args[])
	{
		new Game();
	}
	
}
