/*
 * Jordan Bossman
 * GameServer.java
 * CSC 460
 * 
 * Java file that runs a multi-threaded server for playing of the
 * Foobar game.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class GameServer 
{
	public static void main(String[] args) 
	{
		//Find the location of the questions file
		System.out.println("Please enter the location of the questions file:");
		Scanner scan = new Scanner(System.in);
		String loc = scan.nextLine();
		scan.close();
		
		//Load the questions into a data structure for use amongst threads
		ArrayList<String> questions;
		try
		{
			questions = new ArrayList<String>();
			scan = new Scanner(new File(loc));
			
			while(scan.hasNextLine())
				questions.add(scan.nextLine());
			
			scan.close();
		}
		catch (Exception e)
		{
			System.out.println("Error finding questions file... Closing....");
			return;
		}
		System.out.println("Questions file found! Server started.");
		
		//Start the server's socket & wait for a client connection, then pass
		//that client to a separate thread.
		try(ServerSocket serverSocket = new ServerSocket(8241);)
		{
			while(true)
			{
				//Accept a client connection and throw it to a thread for execution
				Socket s = serverSocket.accept();
				
				GameThread thread = new GameThread(s, questions);
				new Thread(thread).start();	
			}
		}
		catch (SocketException e)
		{
			System.out.println("Client disconnected");
		}
		catch (IOException e)
		{
			System.out.println("Server error...");
			e.printStackTrace();
		}
	}
}
