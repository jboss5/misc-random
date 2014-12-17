package UpdatedMaze;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #3
 * Due: November 2, 2011
 * Creates a Maze GUI for solving a maze and solves the maze from [0,0]
 * to [length,width].
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class MazeGui extends JFrame
{
	public MazeGui(String title, int numRows, int numCols, Maze maze)
	{
		//Constructor to make the main GUI for the program.
		super(title);
		
		JPanel mazePanel = new JPanel();
		
		//Panel that contains the start/end row/column labels and text fields. 
		//Also contains the solve button.
		JPanel solvePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,5));
		
		JLabel startRow = new JLabel("Start row:");
		JTextField startRowInput = new JTextField(3);
		solvePanel.add(startRow);
		solvePanel.add(startRowInput);
		
		JLabel endRow = new JLabel("End row:");
		JTextField endRowInput = new JTextField(3);T
		solvePanel.add(endRow);
		solvePanel.add(endRowInput);
		
		JLabel startCol = new JLabel("Start column:");
		JTextField startColInput = new JTextField(3);
		solvePanel.add(startCol);
		solvePanel.add(startColInput);
		
		JLabel endCol = new JLabel("End column:");
		JTextField endColInput = new JTextField(3);
		solvePanel.add(endCol);
		solvePanel.add(endColInput);
		
		JButton solveButton = new JButton("Solve Maze");
		solvePanel.add(solveButton);
		
		//The key panel contains the color "red" and "black" then 
		//explains what they mean in the picture of the maze.
		JPanel key = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JPanel movableKey = new JPanel();
		JButton moveableButton = new JButton();
		moveableButton.setBackground(Color.RED); 
		JLabel moveableLabel = new JLabel(" - Moveable Space");
		movableKey.add(moveableButton);
		movableKey.add(moveableLabel);		
		
		JPanel wallKey = new JPanel();
		JButton wallButton = new JButton();
		wallButton.setBackground(Color.BLACK);
		JLabel wallLabel = new JLabel(" - Wall");
		wallKey.add(wallButton);
		wallKey.add(wallLabel);
		
		key.add(movableKey);
		key.add(wallKey);

		//Creates the maze picture. Adds in coordinates of each spot and colors
		//representing whether the spot is a movable spot or a wall.
		JPanel mazeButtonPanel = new JPanel(new GridLayout(numRows, numCols));
		for(int i = 0; i < numRows; i++)
		{
			for(int k = 0; k < numCols; k++)
			{
				if(maze.movableSpace(i,k))
				{
					JButton jbt = new JButton(String.format("[%d,%d]",i,k));
					jbt.setForeground(Color.BLACK);
					jbt.setBackground(Color.RED);
					mazeButtonPanel.add(jbt);
				}
				else
				{
					JButton jbt = new JButton(String.format("[%d,%d]",i,k));
					jbt.setForeground(Color.RED);
					jbt.setBackground(Color.BLACK);
					mazeButtonPanel.add(jbt);
				}			
			}
		}
		
		//Adds in all the panels to the main mazePanel.
		mazePanel.add(solvePanel);
		mazePanel.add(key);
		mazePanel.add(mazeButtonPanel);
		//Adds the mazePanel to the main GUI.
		add(mazePanel);
	}
	
	public static void main(String[] args) 
	{
		//Program asks for the file containing the maze. If the file does not exist or cannot
		//be read, it tells the user the file does not exist or cannot be read. Otherwise the 
		//program goes and sets up the GUI and maze picture then solves the maze, displaying 
		//the solved path in a JOptionPane or displaying that is has no solution.
		String mazeFileName = JOptionPane.showInputDialog(null, "Enter the file name containing the maze",
				                                          "Maze File Input", JOptionPane.QUESTION_MESSAGE);
		File mazeFile = new File(mazeFileName);
		Maze maze;
		int numRows = 0;
		int numCols = 0;

		try 
		{
			if(mazeFile.canRead())
			{
				//If the file exists and can be read, continue.
				try
				{
					Scanner mazeInput = new Scanner(mazeFile);
					numRows = mazeInput.nextInt();
					numCols = mazeInput.nextInt();
					maze = new Maze(mazeInput, numRows, numCols);
					mazeInput.close();
				}
				catch (InputMismatchException e)
				{
					//If a InputMismatchException is thrown (the input in the file is not the
					//right type), then this message is displayed and ends the program.
					JOptionPane.showMessageDialog(null, String.format("The input in the file \"%s\" is not of the right type.", mazeFile),
												  "File Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//Creates the main GUI.
				MazeGui mazeGui = new MazeGui("Solve a Maze", numRows, numCols, maze);
				mazeGui.setSize(700,385);
				mazeGui.setLocationRelativeTo(null);
				mazeGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mazeGui.setVisible(true);
				
				//Creates the output string for the JOptionPane.
				StringBuffer outputString = new StringBuffer(String.format("The path from [%d,%d] to [length,width]:\n", 0, 0));
				outputString.append(maze.solveMazeString(0, 0, numRows - 1, numCols - 1));
				JOptionPane.showMessageDialog(mazeGui, outputString.toString(), "Maze Output", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//If the file cannot be read, this message is displayed.
				JOptionPane.showMessageDialog(null, String.format("The file \"%s\" does not exist or cannot be read", mazeFile),
											  "File Error", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch (FileNotFoundException e) 
		{
			//If a FileNotFoundException is thrown (the file does not exist), then this message is displayed.
			JOptionPane.showMessageDialog(null, String.format("The file \"%s\" does not exist or cannot be read", mazeFile),
					                      "File Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
