/*
 * Jordan Bossman
 * GameThread.java
 * CSC 460
 * 
 * Runnable implementation that runs the multi-threaded 
 * aspect of the Foobar game.
 */

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class GameThread implements Runnable 
{
	private Socket client;
	private ArrayList<String> questions;
	
	public GameThread(Socket socket, ArrayList<String> questions)
	{
		//Grab the client socket & the questions ArrayList from the server
		this.client = socket;
		this.questions = questions;
	}
	
	@Override
	public void run() 
	{
		System.out.println("Client and server connected");
		try
		{
			//Setup the input and output streams
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			int score = 0, total = 0;
			
			//Loop for each question and answers set
			for(int i = 0; i < questions.size(); i+=6)
			{
				//Print the question & it's possible answers
				for(int k = i; k < (i+5); k++)
					out.println(questions.get(k));

				out.println("."); //Used to keep the client and server in sync while talking
				String io, input = "";

				while(!(io = in.readLine()).equals("."))
				{
					input = io;
				}
				
				//Compute the score from the client
				if(input.contains(questions.get(i+5)))
				{
					score = (-250);
					total-=250;
				}
				else
				{
					score=(250*input.length());
					total+=score;
				}
				
				//Based off of the score the client got for that question, output the proper line
				if(score == 750)
					out.println("You got all the answers! Your points from the question: " + score);
				else if(score >= 250)
					out.println("You got the answers partially correct. Your points from the question: " + score);
				else
					out.println("You got the answers wrong. Your points from the question: " + score);

				//Print out the total points
				out.println("Total points: " + total);
				
				//Used to tell the client the game is finished
				if(!((i+6) < questions.size()))
					out.println("end");
				else
					out.println();
			}
			
			//When the game is done, print this line
			out.println("Game Over! Your final score is: " + total);
			
			in.close();
			out.close();
			client.close();
		}
		catch(SocketException e)
		{
			System.out.println("Client disconnected abruptly...");
		}
		catch(IOException e)
		{
			System.out.println("Server encountered a critical error, exiting...");
			e.printStackTrace();
		}
	}

}
