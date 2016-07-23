package com.TRACON.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener{
	// Input mode
	private char mode;

	private String input, property;

	// Initialize mode to 'X' (none)
	public KeyboardHandler() {
		mode = 'X';
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());
		
		if (mode == 'X') // If no mode is active
		{
			// Set mode to the key pressed, if it's a s or d
			if (key.equalsIgnoreCase("a") || key.equalsIgnoreCase("s") || key.equalsIgnoreCase("d")
					|| key.charAt(key.length() - 1) == '/' || key.charAt(key.length() - 1) == '*' || key.charAt(key.length() - 1) == '-') 
			{
				mode = key.toUpperCase().charAt(key.length() - 1);
				
				switch (mode)
				{
				case '/':
					mode = 'D';
					break;
					
				case '*':
					mode = 'S';
					break;
					
				case '-':
					mode = 'A';
					break;
				}
				
				System.out.println(mode);
				switch (mode) {
				case 'A':
					property = "Altitude: ";
					break;

				case 'S':
					property = "Speed: ";
					break;

				case 'D':
					property = "Heading: ";
					break;
				}

				Datablock.getReadout().updateDatablock(property);
			} 
			else // Else some invalid button was pressed, so reset everything
			{
				Datablock.getReadout().updateDatablock("");
				mode = 'X';
			}
		} 
		else 
		{
			if (key.equalsIgnoreCase("a") || key.equalsIgnoreCase("s") || key.equalsIgnoreCase("d")
					|| key.charAt(key.length() - 1) == '/' || key.charAt(key.length() - 1) == '*' || key.charAt(key.length() - 1) == '-') 
			{
				mode = key.toUpperCase().charAt(key.length() - 1);
				
				input = "";
				
				switch (mode)
				{
				case '/':
					mode = 'D';
					break;
					
				case '*':
					mode = 'S';
					break;
					
				case '-':
					mode = 'A';
					break;
				}
				
				switch (mode) {
				case 'A':
					property = "Altitude: ";
					break;

				case 'S':
					property = "Speed: ";
					break;

				case 'D':
					property = "Heading: ";
					break;
				}

				Datablock.getReadout().updateDatablock(property);
			} 
			if (key.equals("Enter")) 
			{
				if (input != null) 
				{
					// Attempt to parse the input number
					int value = parseInput(input);
					if (value < 0) // values < 0 are invalid (see
									// parseInput())
					{
						// Reset everything and show an error message
						Datablock.getReadout().updateDatablock("Input error");
						input = "";
						property = "";
						mode = 'X';
					} 
					else 
					{
						switch (mode) 
						{
						case 'A':
							if (value > 100 || value < 20) // If altitude
															// value is
															// invalid (too
															// high or low)
							{
								Datablock.getReadout().updateDatablock("Altitude must be between 020 and 100");
								input = "";
								mode = 'X';
							} else {
								// If value is valid, pass it to the
								// selected aircraft
								Aircraft.getSelected().setGivenAltitude(value);

								Datablock.getReadout().updateDatablock("");
								input = "";
								property = "";
								mode = 'X';
							}
							break;

						case 'S': // Again, check for valid input and pass
									// it if it works
							if (value > 250) 
							{
								Datablock.getReadout().updateDatablock("Too fast!");
								input = "";
								mode = 'X';
							} 
							else 
							{
								if (value < 60) 
								{
									Datablock.getReadout().updateDatablock("Too slow for this type aircraft!");
									input = "";
									mode = 'X';
								} 
								else 
								{
									Aircraft.getSelected().setGivenSpeed(value);
									Datablock.getReadout().updateDatablock("");
									input = "";
									mode = 'X';
								}
							}
							break;

						case 'D':
							if (value < 0 || value > 360) {
								Datablock.getReadout().updateDatablock("Heading must be between 000 and 360");
								input = "";
								mode = 'X';
							} 
							else 
							{
								Aircraft.getSelected().setGivenHeading(value);

								Datablock.getReadout().updateDatablock("");
								input = "";
								property = "";
								mode = 'X';
							}
							break;

						default:
							mode = 'X';
						}
						// Reset the inputHandler for the next input
						input = "";
						mode = 'X';
					}
				} 
				else 
				{ // If input was empty, reset the handler
					Datablock.getReadout().updateDatablock("");
					property = "";
					mode = 'X';
				}
			}
			else 
			{
				if (Character.isDigit(key.charAt(key.length() - 1))) // Some number was
														// inputted, and the
														// handler is in a
														// valid mode
				{
					key = key.substring(key.length() - 1);
					// Add the number to the input string and display it
					if (input == null) {
						input = key;
					} else {
						input += key;
					}
					Datablock.getReadout().updateDatablock(property + input);
				} else {
					if (key == "Backspace") {
						// Remove the last character from the input String
						// and display result
						if (input.length() >= 1) {
							input = input.substring(0, input.length() - 1);
						} else {
							// Backspace was pressed with no input -- reset
							// the handler
							Datablock.getReadout().updateDatablock("");
							input = "";
							property = "";
							mode = 'X';
						}
						Datablock.getReadout().updateDatablock(property + input);
					} else {
						// Cancel input if escape pressed
						if (key == "Escape") {
							Datablock.getReadout().updateDatablock("");
							input = "";
							property = "";
							mode = 'X';
						}
					}
				}
			}
		}
	}

/*	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private int parseInput(String input) {
		// Attempt to parse the String into an int, if it throws, return -1
		int intValue;
		try {
			intValue = Integer.parseInt(input);
			return intValue;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
