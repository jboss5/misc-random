/* Jordan Bossman
 * CSC 360
 * Programming Assignment #4
 * Due: November 30, 2011
 * Class file that runs the game engine
 * for the game "Minesweeper".
 */
package Minesweeper;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.File;

public class Minesweeper
{
	private int[][] grid;
	private int[][] mineLocations;
	private int[][] undoRecorder;
	private JButton lastButtonClicked;
	private int numMines = 0;
	private long startTime;
	private long endTime;
	public final static int EASY_MAZE_MINE_COUNT = 10;
	public final static int MED_MAZE_MINE_COUNT = 40;
	public final static int HARD_MAZE_MINE_COUNT = 99;
	private final int DEFAULT_VALUE = 0;
	private final int MINE = 1;
	private final int NUMBERED_POSITION = 2;
	private final int BUTTON_NOT_VISIBLE = 3;
	private final int FLAGGED_BUTTON = 4;
	
	public Minesweeper(int row, int col, int numMines)
	{
		//Constructor for a minesweeper object.
		//Creates three arrays: one for managing everything
		//but the mines. Another managing the information for the
		//undo sequence, and the last one managing the mines and 
		//numbers on the grid.
		grid = new int[row][col];
		mineLocations = new int[row][col];
		undoRecorder = new int[row][col];
		this.numMines = numMines;
	}
	
	public int getNumMines()
	{
		//Returns the number of mines.
		return numMines;
	}
	
	public boolean isMine(int row, int col)
	{
		//When passing in a row and column, it returns if at the
		//current location there is a mine or not.
		return mineLocations[row][col] == MINE;
	}
	
	public boolean isMine(JButton[][] buttons, JButton button)
	{
		//When passing in the array of buttons and the button
		//pressed, returns if the current location has a mine
		//in it or not.
		
		//Determines the row & column in the gridLayout and grid/mineLocation
		//arrays of the button clicked.
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(buttons[i][k] == button)
					return mineLocations[i][k] == MINE;
			}
		}
		
		return false;
	}

	public void addFlag(JButton[][] buttons, JButton button)
	{
		//Adds a flag to a given spot.
		//Also sets the picture to that of a flag.
		File file = new File("minesweeperFlag.gif");
		ImageIcon icon = new ImageIcon(file.toString());
		Image image = icon.getImage();
		image = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);

		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(buttons[i][k] == button)
				{
					grid[i][k] = FLAGGED_BUTTON;
					
					//If the picture exists, display it; otherwise
					//display a text version of the picture (flag).
					if(file.exists())
						button.setIcon(icon);
					else
					{
						button.setText("F");
						button.setForeground(Color.RED);
						button.setFont(button.getFont().deriveFont(Font.BOLD));
					}
					button.setHorizontalAlignment(JButton.CENTER);
					button.setVerticalAlignment(JButton.CENTER);
					return;
				}
			}
		}
	}
	
	public void deleteFlag(JButton[][] buttons, JButton button)
	{
		//Deletes a flag at a given spot.
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(buttons[i][k] == button)
				{
					grid[i][k] = DEFAULT_VALUE;
					buttons[i][k].setIcon(null);
					return;
				}
			}
		}
	}
	
	public boolean isFlag(int row, int col)
	{
		//If given the row and column, returns if the current spot
		//holds a flag or not.
		return grid[row][col] == FLAGGED_BUTTON;
	}
	
	public boolean isFlag(JButton[][] buttons, JButton button)
	{
		//If given the array of buttons and the button pressed,
		//returns whether at the location is a flag or not.

		//Given the button pressed, determines the row and column
		//of which button was clicked.
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(buttons[i][k] == button)
					return grid[i][k] == FLAGGED_BUTTON;
			}
		}
		return false;
	}
	
	public boolean isQuestionMark(JButton button)
	{
		//Returns whether the given button has a "?" on it.
		return button.getText().equals("?");
	}

	public void fillMines(int numMines, JLabel[][] labels)
	{
		//Fills in all the spots on the grid with the number of mines needed.
		//Also sets the buttons to have the button picture & resizes the picture
		//to fit into the grid.
		Random random = new Random();
		
		File file = new File("minesweeperMine.gif");
		ImageIcon icon = new ImageIcon(file.toString());
		Image image = icon.getImage();
		image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);

		int row = 0;
		int col = 0;
		int x = 0;
		
		//Creates the number of mines needed in random spots.
		while(x < numMines)
		{
			row = random.nextInt(grid.length);
			col = random.nextInt(grid[row].length);
			if(mineLocations[row][col] != MINE)
			{
				mineLocations[row][col] = MINE;
				x++;
			}
		}

		//Creates the labels where the mines are at.
		for(int i = 0; i < labels.length; i++)
		{
			for(int k = 0; k < labels[i].length; k++)
			{
				if(mineLocations[i][k] == MINE)
				{
					//If the picture exists, display it; otherwise
					//display a text version the picture (mine).
					if(file.exists())
						labels[i][k].setIcon(icon);
					else
						labels[i][k].setText("M");
					
					labels[i][k].setHorizontalAlignment(JLabel.CENTER);
					labels[i][k].setVerticalAlignment(JLabel.CENTER);
				}
			}
		}
		
		fillNumbers(labels);
	}
	
	private void fillNumbers(JLabel[][] labels)
	{
		//Fills in the numbers of the amount of mines around a certain location.
		//It checks the 8 spots surrounding the beginning spot and adds them all
		//together to create the number at the beginning spot. Then according
		//to how high the number is, gives it a color.
		int numMines = 0;
	
		for(int i = 0; i < labels.length; i++)
		{
			for(int k = 0; k < labels[i].length; k++)
			{
				//Checks the south square for a mine.
				if(i > 0 && isMine(i - 1, k))
					numMines++;
				
				//Checks the north square for a mine.
				if(i < labels.length - 1 && isMine(i + 1, k))
					numMines++;
				
				//Checks the west square for a mine.
				if(k > 0 && isMine(i, k - 1))
					numMines++;
				
				//Checks the east square for a mine.
				if(k < labels[i].length - 1 && isMine(i, k + 1))
					numMines++;
				
				//Checks the northeast square for a mine.
				if(i < labels.length - 1 && k < labels[i].length - 1 && isMine(i + 1, k + 1))
					numMines++;
				
				//Checks the southwest square for a mine.
				if(i > 0 && k > 0 && isMine(i - 1, k - 1))
					numMines++;
				
				//Checks the northwest square for a mine.
				if(i < labels.length - 1 && k > 0 && isMine(i + 1, k - 1))
					numMines++;
				
				//Checks the southeast square for a mine.
				if(k < labels[i].length - 1 && i > 0 && isMine(i - 1, k + 1))
					numMines++;
				
				if(numMines > 0 && !isMine(i, k))
				{
					mineLocations[i][k] = NUMBERED_POSITION;
					labels[i][k].setText(((Integer)numMines).toString());
					labels[i][k].setHorizontalAlignment(JLabel.CENTER);
					labels[i][k].setVerticalAlignment(JLabel.CENTER);
					if(numMines == 1)
					{
						labels[i][k].setFont(labels[i][k].getFont().deriveFont(Font.BOLD));
						labels[i][k].setForeground(Color.BLUE);
					}
					else if(numMines == 2)
					{
						labels[i][k].setFont(labels[i][k].getFont().deriveFont(Font.BOLD));
						Color color = new Color(0,128,0);
						labels[i][k].setForeground(color);
					}
					else if(numMines >= 3)
					{
						labels[i][k].setFont(labels[i][k].getFont().deriveFont(Font.BOLD));
						labels[i][k].setForeground(Color.RED);
					}
				}
				
				numMines = 0;
			}
		}
	}
	
	public void showSpaces(JButton button, JLabel[][] labels, JButton[][] buttons)
	{
		//This method mainly just determines where the button was clicked, then passes that 
		//information on to sub-methods that make the buttons invisible at certain locations.
		int row = 0;
		int col = 0;
		for(int i = 0; i < buttons.length; i++)
		{
			for(int k = 0; k < buttons[i].length; k++)
			{
				if(buttons[i][k] == button)
				{
					row = i;
					col = k;
					break;
				}
			}
		}
		
		if(!labels[row][col].getText().equals(""))
		{
			removeButton(row,col,buttons);
			buttons[row][col].setVisible(false);
			return;
		}
		else
		{
			showNorth(row, col, labels, buttons);
			showSouth(row, col, labels, buttons);
			showEast(row, col, labels, buttons);
			showWest(row, col, labels, buttons);
		}
	}

	private void showWest(int row, int col, JLabel[][] labels, JButton[][] buttons)
	{
		//Makes the buttons invisible to the left half of the grid.
		for(int i = row; i < buttons.length; i++)
		{
			for(int k = col; k > 0; k--)
			{
				if(!isMine(i,k) && !isFlag(i,k))
					removeButton(i,k,buttons);
				else
					return;
			}
		}
	}
	
	private void showNorth(int row, int col, JLabel[][] labels, JButton[][] buttons)
	{
		//Makes the buttons invisible towards the top of the grid.
		for(int i = row; i > 0; i--)
		{
			for(int k = col; k > 0; k--)
			{
				if(!isMine(i,k) && !isFlag(i,k))
					removeButton(i,k,buttons);
				else
					return;
			}
		}
	}
	
	private void showEast(int row, int col, JLabel[][] labels, JButton[][] buttons)
	{
		//Makes the buttons invisible towards the right of the grid.
		for(int i = row; i > 0; i--)
		{
			for(int k = col; k < buttons[i].length; k++)
			{
				if(!isMine(i,k) && !isFlag(i,k))
					removeButton(i,k,buttons);
				else
					return;
			}
		}
	}
	
	private void showSouth(int row, int col, JLabel[][] labels, JButton[][] buttons)
	{
		//Makes the buttons invisible towards the bottom of the grid.
		for(int i = row; i < buttons.length; i++)
		{
			for(int k = col; k < buttons[i].length; k++)
			{
				if(!isMine(i,k) && !isFlag(i,k))
					removeButton(i,k,buttons);
				else
					return;
			}
		}
	}
		
	public void showMines(JButton[][] buttons, JLabel[][] labels)
	{
		//When called, shows all the mines on the map.
		//Then if the mine has been flagged properly, it displays
		//a different image from that of a normal mine to tell the user
		//that they correctly found that mine.
		File file = new File("minesweeperFlaggedMine.gif");
		ImageIcon icon = new ImageIcon(file.toString());
		Image image = icon.getImage();
		image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		
		for(int i = 0; i < mineLocations.length; i++)
		{
			for(int k = 0; k < mineLocations[i].length; k++)
			{
				if(mineLocations[i][k] == MINE)
				{
					if(grid[i][k] == FLAGGED_BUTTON)
					{
						//If the picture exists, display it; otherwise
						//display a text version of the picture (flagged mine).
						if(file.exists())
							labels[i][k].setIcon(icon);
						else
							labels[i][k].setText("Flagged");
					}
					
					removeButton(i,k,buttons);	
					buttons[i][k].setVisible(false);
				}
			}
		}
	}
	
	public void removeButton(JButton[][] buttons, JButton button)
	{
		//Determines the (row,col) of the given button, then sets
		//it to invisible.
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(buttons[i][k] == button)
				{
					grid[i][k] = BUTTON_NOT_VISIBLE;
					buttons[i][k].setVisible(false);
					return;
				}
			}
		}
	}
	
	public void removeButton(int row, int col, JButton[][] buttons)
	{
		//Sets the button at the given location to be invisible.
		grid[row][col] = BUTTON_NOT_VISIBLE;
		buttons[row][col].setVisible(false);
	}
	
	public void undoMove(JButton[][] buttons)
	{
		//Using the information saved in the undo array, undoes the last move.
		lastButtonClicked.setVisible(true);
		
		for(int i = 0; i < undoRecorder.length; i++)
		{
			for(int k = 0; k < undoRecorder[i].length; k++)
			{
				grid[i][k] = undoRecorder[i][k];
				
				if(grid[i][k] != BUTTON_NOT_VISIBLE)
					buttons[i][k].setVisible(true);
			}
		}
	}
	
	public void updateUndo(JButton button)
	{
		//Updates the undo array and lastButtonClicked for use of then undoMove() method.
		for(int i = 0; i < undoRecorder.length; i++)
		{
			for(int k = 0; k < undoRecorder[i].length; k++)
				undoRecorder[i][k] = grid[i][k];
		}
		
		lastButtonClicked = button;
	}
	
	public boolean isEnd()
	{
		//Checks to see whether all the buttons have been flagged or made invisible.
		for(int i = 0; i < grid.length; i++)
		{
			for(int k = 0; k < grid[i].length; k++)
			{
				if(mineLocations[i][k] == MINE && grid[i][k] != FLAGGED_BUTTON)
					return false;
				else if(grid[i][k] != BUTTON_NOT_VISIBLE
						&& grid[i][k] == FLAGGED_BUTTON && mineLocations[i][k] != MINE)
					return false;
			}
		}
		return true;
	}
	
	public boolean checkEnd(JButton[][] buttons, JButton button)
	{
		//Determines the button pressed at (row,col) then passes it to isEnd()
		//to determine if all the buttons have been made invisible or flagged.
		for(int i = 0; i < buttons.length; i++)
		{
			for(int k = 0; k < buttons[i].length; k++)
			{
				if(buttons[i][k] == button)
				{
					if(isEnd())
						return true;
				}
			}
		}
		
		return false;

	}
	
	public void doEndProcess(JFrame frame, JButton[][] buttons, JLabel[][] labels, boolean win)
	{
		//Does the operations at the end of the game; whether win or loss. Shows a JOptionPane
		//which tells the time for completion in seconds and asks the user whether they want
		//to close the program or undo the last move and try again.
		Object[] endOptions = new Object[] { "End Game", "Undo Last Move" };
		timerStop();
		showMines(buttons, labels);
		long totalTime = endTime - startTime;
		String str = String.format("It took you %d seconds.", totalTime);
		
		if(win)
		{
			//If user won, this executes.
			String endString = String.format("You have won! %s", str);
			int end = JOptionPane.showOptionDialog(frame, endString,
			 "You have won!", JOptionPane.YES_NO_OPTION, 
			 JOptionPane.INFORMATION_MESSAGE, null, endOptions, null);
		
			if(end == 0)
				frame.dispose();
			else if(end == 1)
			{
				try
				{
					undoMove(buttons);
				}
				catch(NullPointerException e)
				{
					JOptionPane.showMessageDialog(frame, "Cannot undo last action, closing...", "Error",
							JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			}
		}
		else
		{
			//If user lost, this executes.
			String endString = String.format("You have lost! %s", str);
			int end = JOptionPane.showOptionDialog(frame, endString, 
					 "You have lost!", JOptionPane.YES_NO_OPTION, 
					 JOptionPane.INFORMATION_MESSAGE, null, endOptions, endOptions[1]);
				
			if(end == 0)
				frame.dispose();
			else if(end == 1)
			{
				try
				{
					undoMove(buttons);
				}
				catch(NullPointerException e)
				{
					JOptionPane.showMessageDialog(frame, "Cannot undo last action, closing...", "Error",
							JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			}
		}
	}
	
	public void timerStart()
	{
		//Starts the timer and converts it to seconds.
		startTime = System.currentTimeMillis() / 1000;
	}
	
	public void timerStop()
	{
		//Ends the timer and converts it to seconds.
		endTime = System.currentTimeMillis() / 1000;
	}
}