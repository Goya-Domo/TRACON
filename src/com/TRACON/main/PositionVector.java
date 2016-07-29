/**
 * 
 * Handles all the movement and background location updating for aircraft
 * 
 */

package com.TRACON.main;

public class PositionVector {

    private Game game;
	
	private double exactX, exactY, xStep, yStep;
	
	private double climbRate = 1.0;
	
	//These might be removed, they will be calculated on the fly
	private double heading, speed, altitude;
	
	private int givenHeading, givenSpeed, givenAltitude;
	
	public PositionVector(int x, int y, int heading, int speed, int altitude, Game game)
	{
        this.game = game;

		this.exactX = (double)x;
		this.exactY = (double)y;
		
		this.heading = (double)heading;
		this.speed = (double)speed;		
		this.altitude = (double)altitude;
		
		this.givenHeading = heading;
		this.givenSpeed = speed;
		this.givenAltitude = altitude;
		
        updatePosition();
	}
		
	public void updatePosition()
	{
		if (givenHeading != heading)
		{
			if (givenHeading > heading + 0.01)
			{
				if (givenHeading - heading < 180)
				{
					heading += (double)Game.TURNRATE / 33;
				}
				else
				{
					heading -= (double)Game.TURNRATE / 33;
				}
			}
			else
			{
				if (givenHeading < heading - 0.01)
				{
					if (heading - givenHeading < 180)
					{
						heading -= (double)Game.TURNRATE / 32;
					}
					else
					{
						heading += (double)Game.TURNRATE / 32;
					}
				}
				else
				{                        
					heading = givenHeading;
				}
			}
		}
		
        if (heading <= 0)
        {
            heading += 360;
        }
        else
        {
            if (heading > 360)
            {
                heading -= 360;
            }
        }
        
        if (givenSpeed != speed)
        {
            if (givenSpeed < speed - 3)
            {
                speed -= ((double)9 / Game.SIMSPEED);
            }
            else
            {
                if (givenSpeed > speed + 3)
                {
                    speed += ((double)9 / Game.SIMSPEED);
                }
                else
                {
                    speed = givenSpeed;
                }
            }
        }
        
        if (givenAltitude != altitude)
        {
            if (givenAltitude < altitude - 1)
            {
            	altitude -= (climbRate / Game.SIMSPEED);
            }
            else
            {
                if (givenAltitude > altitude + 1)
                {
                	altitude += (climbRate / Game.SIMSPEED);
                }
                else
                {
                	altitude = givenAltitude;
                }
            }
        }
		
        double simSpeed = (((speed * game.getPixelsPerMile()) / 60) / (20 * game.SIMSPEED));
        double simHeading = calcSimHeading(heading);
        
        xStep = Math.cos(Math.toRadians(simHeading)) * simSpeed;
        yStep = Math.sin(Math.toRadians(simHeading)) * simSpeed * -1;
        
        exactX += xStep;
        exactY += yStep;
        
	}
	
    private double calcSimHeading(double heading)
    {
        double simHeading = 90 - heading;

        if (simHeading <= 0)
        {
            return simHeading + 360;
        }
        else
        {
            return simHeading;
        }
    }
    
    public int getX()
    {
    	if (exactX % 1 < 0.5)
    	{
    		return (int)exactX;
    	}
    	else
    	{
    		return (int)exactX + 1;
    	}
    }
    
    public int getY()
    {
    	if (exactY % 1 < 0.5)
    	{
    		return (int)exactY;
    	}
    	else
    	{
    		return (int)exactY + 1;
    	}
    }
    
    public int getGivenHeading()
    {
    	return givenHeading;
    }
    
    public int getGivenSpeed()
    {
    	return givenSpeed;
    }
    
    public int getGivenAltitude()
    {
    	return givenAltitude;
    }
    
    public double getHeading()
    {
        return heading;
    }
    
    public double getSpeed()
    {
    	return speed;
    }
    
    public double getAltitude()
    {
    	return altitude;
    }
    
    public void setGivenHeading(int heading)
    {
    	this.givenHeading = heading;
    }
    
    public void setGivenSpeed(int speed)
    {
    	this.givenSpeed = speed;
    }
    
    public void setGivenAltitude(int altitude)
    {
    	this.givenAltitude = altitude;
    }
    
    public double calcHeading()
    {
    	double realHeading = Math.toDegrees(Math.atan2(xStep,  -1 * yStep));
    	
        if (xStep < 0)
        {
            realHeading += 360;                
        }
        else
        {
            if (realHeading > 360)
            {
                realHeading -= 360;
            }
            else
            {
                if (realHeading <= 0)
                {
                    realHeading += 360;
                }
            }                        
        }
        
        return realHeading;
    }
    
    public int calcMinuteXStep()
    {
    	return (int)(xStep * Game.SIMSPEED * 20);
    }
    
    public int calcMinuteYStep()
    {
    	return (int)(yStep * Game.SIMSPEED * 20);
    }
}
