package UpdatedMaze;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #3
 * Due: November 2, 2011
 * Maze object class for solving, building and printing of a path through a maze.
 */

import java.util.*;

public class Maze 
{
    private int[][] maze;
	private final int MAZE_PATH = 2;
	private final int MOVABLE_SPACE = 0;
	private final int DEAD_END = 3;
	
	public Maze(Scanner input, int row, int col) 
	{
		//Constructs a maze object.
		maze = new int[row][col];
		buildMaze(input);
	}
	
	private void buildMaze(Scanner mazeFile)
	{
		//Uses the scanner provided to build a 2D array which is the maze.
		for(int i = 0; i < maze.length; i++)
		{
			for(int k = 0; k < maze[i].length; k++)
				maze[i][k] = mazeFile.nextInt();
		}
	}
	
	public boolean findPath(int startX, int startY, int endX, int endY)
	{
		//Determines if the current position is at the end or not. If not
		//program recursively finds the next path.
		if(startX == endX && startY == endY)
			return true;
		
		if(testPath(startX + 1, startY, endX, endY))
			return true;
		else if(testPath(startX - 1, startY, endX, endY))
			return true;
		else if(testPath(startX, startY + 1, endX, endY))
			return true;
		else if(testPath(startX, startY - 1, endX, endY))
			return true;
		else
			return false;
	}
	
	private boolean testPath(int currentX, int currentY, int endX, int endY)
	{
		//Determines if the current location is a space it can move to.
		if(currentX < 0 || currentY < 0 || currentX >= maze.length || currentY >= maze[currentX].length)
			return false;

		if(movableSpace(currentX,currentY))
		{
			maze[currentX][currentY] = MAZE_PATH;
			//If the current space is a valid space it returns true.
			//Otherwise it is a dead end.
			if(findPath(currentX, currentY, endX, endY))
				return true;
			maze[currentX][currentY] = DEAD_END;
		}
		return false;
	}
	
	public boolean movableSpace(int row, int col)
	{
		//Returns true if the current space = 0 (MOVABLE_SPACE).
		return maze[row][col] == MOVABLE_SPACE;
	}

	public String solveMazeString(int currentX, int currentY, int endX, int endY)
	{
		//Solves the maze and outputs either "no solution" or the correct path from start to end.
		//Puts everything into a string for easy output in a JOptionPane or a JLabel.		
		StringBuffer solvedString = new StringBuffer();
		
		if(findPath(currentX, currentY, endX, endY))
		{
			//Loop travels through the maze finding all of the MAZE_PATH values and how they correspond
			//to one another, which represents the correct path from start to end. 
			do
			{
				solvedString.append(String.format("[%d,%d]\n",currentX,currentY));

				if((currentY > 0) && (maze[currentX][currentY - 1] == MAZE_PATH))
				{
					currentY--;
					maze[currentX][currentY] = 1;
				}
				else if((currentY < maze[currentX].length - 1) && (maze[currentX][currentY + 1] == MAZE_PATH))
				{
					currentY++;
					maze[currentX][currentY] = 1;
				}
				else if((currentX > 0) && (maze[currentX - 1][currentY] == MAZE_PATH))
				{   
					currentX--;
					maze[currentX][currentY] = 1;
				}
				else if((currentX < maze.length - 1) && (maze[currentX + 1][currentY] == MAZE_PATH))
				{
					currentX++;
					maze[currentX][currentY] = 1;
				}
				
			} while(!(currentX == endX && currentY == endY));
			
			solvedString.append(String.format("[%d,%d]\n",currentX,currentY));
			return solvedString.toString();
		}
		else
			return "The maze has no solution.";
	}
	
	public void solveMazeConsole(int currentX, int currentY, int endX, int endY)
	{
		//Solves the maze and outputs either "no solution"or the correct path from start to end.
		//Prints everything to the console.
		if(!findPath(currentX, currentY, endX, endY))
			System.out.println("Maze has no solution");
		else
		{
			//Loop travels through the maze finding all of the MAZE_PATH values and how they correspond
			//to one another, which represents the correct path from start to end. 
			do
			{
				System.out.printf("[%d,%d]\n",currentX,currentY);

				if((currentY > 0) && (maze[currentX][currentY - 1] == MAZE_PATH))
				{
					currentY--;
					maze[currentX][currentY] = 1;
				}
				else if((currentY < maze[currentX].length - 1) && (maze[currentX][currentY + 1] == MAZE_PATH))
				{
					currentY++;
					maze[currentX][currentY] = 1;
				}
				else if((currentX > 0) && (maze[currentX - 1][currentY] == MAZE_PATH))
				{   
					currentX--;
					maze[currentX][currentY] = 1;
				}
				else if((currentX < maze.length - 1) && (maze[currentX + 1][currentY] == MAZE_PATH))
				{
					currentX++;
					maze[currentX][currentY] = 1;
				}
				
			} while(!(currentX == endX && currentY == endY));

			System.out.printf("[%d,%d]\n",endX,endY);
		}
	}
}