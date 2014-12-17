package Maze;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #1
 * Due: September 14, 2011
 * Redo due date: October 14, 2011
 * Program tries to find a path to the end of a designated maze file of 0s and 1s. If maze
 * is not found then the program returns the maze has no solution.
 */

import java.util.Scanner;
import java.io.*;

public class MazeSolver
{
	public static void processMaze(String mazeFile, int startRow, int startCol, int endRow, int endCol)
	{
		//Tests to see if the file is found or not.
		try 
		{
			//If the file is found, it reads in the size of the maze.
			Scanner mazeInput = new Scanner(new File(mazeFile));
			
			int numRows = mazeInput.nextInt();
			int numCols = mazeInput.nextInt();
			
			Maze maze = new Maze(mazeInput, numRows, numCols);
			
			mazeInput.close();
			
			//If the inputs are outside the boundaries of the maze,
			//it automatically prints "no solution".
			if((startRow < 0) || (endRow > numRows) || (startCol < 0) || (endCol > numCols))
				System.out.println("Maze has no solution");
			else
				maze.solveMaze(startRow, startCol, endRow, endCol);
		}
		catch(FileNotFoundException e)
		{
			//If the file does not exist the program stops.
			System.err.print(e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("What is the name of your maze file? ");
		String mazeFilePath = scan.nextLine();
		
		System.out.print("What is the starting row? ");
		int startRow = scan.nextInt();
		
		System.out.print("What is the starting column? ");
		int startCol = scan.nextInt();
		
		System.out.print("What is the ending row? ");
		int endRow = scan.nextInt();
		
		System.out.print("What is the ending column? ");
		int endCol = scan.nextInt();

		processMaze(mazeFilePath, startRow, startCol, endRow, endCol);
	}
}
