/*
 * Jordan Bossman
 * GameClient.java
 * CSC 460
 * 
 * This program runs a client for the Foobar game.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient 
{
	public static void main(String[] args) 
	{
		try(Socket s = new Socket("127.0.0.1", 8241))
		{  
			//Setup the input and output streams for the client.
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedReader sys = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			String input = "", myinp = "";
			String[] question = new String[5];
			int i = 0;
			
			//Print out the starting information for the game
			System.out.println("---------------------------------------------");
			System.out.println("Welcome to FooBar the game!");
			System.out.println("How to play:");
			System.out.println("You will see a question with 4 answers, enter the 3 numbers of the correct answers");
			System.out.println("Example: if answers 1, 3 and 4 were correct, type in 134");
			System.out.println("You can quit at any time by answering with a \"q\" or \"Q\"");
			System.out.println("---------------------------------------------");
			System.out.println("\n");
			
			//If the user presses "q" or "Q" and or the server sends an "end", then the game is over.
			while(!myinp.equalsIgnoreCase("q") && !input.equals("end"))
			{
				//Read the current question & it's errors into an array.
				while(!(input = in.readLine()).equals("."))
				{
					question[i] = input;
					i++;
				}
				
				i=0;

				//Output the question and its answers.
				System.out.println("---------------------------------------------");
				System.out.println(question[0]);
				for(int k = 1; k < question.length; k++)
					System.out.println(k + ") " + question[k]);
				System.out.println("---------------------------------------------");
				
				//Get the user's response, then go into an infinite loop until the user inputs a correct answer.
				myinp = sys.readLine();
				while(true)
				{
					//Check to make sure the input is only 1-4
					if(myinp.contains("0") || myinp.contains("5") || myinp.contains("6") || myinp.contains("7") || myinp.contains("8") || myinp.contains("9"))
					{
						System.out.println("Please make sure that there are no values higher than 4. Please try again...");
						myinp = sys.readLine();
					}
					else
					{
						//Check to make sure the input has no multiples (222, 122, 344, etc)
						if((Integer.valueOf(myinp) % 111) == 0 || myinp.contains("11") || myinp.contains("22") || myinp.contains("33") || myinp.contains("44"))
						{	
							System.out.println("Please make sure you have only entered 1, 2 or 3 and no multiples of each. Please try again...");
							myinp = sys.readLine();
						}
						else
						{
							//Check to make sure the input is at least 1 number up to 3 numbers
							if(myinp.length() < 0 || myinp.length() > 3)
							{
								System.out.println("Make sure that you have not entered more than 3 or less than 1 digit. Please try again...");
								myinp = sys.readLine();
							}
							else 
								break;
						}
					}
				}
			
				out.println(myinp);
				out.println(".");
			
				//Print out the score information from the server
				input = in.readLine();
				System.out.println(input);
				input = in.readLine();
				System.out.println(input);
				input = in.readLine();
				System.out.println();
			}
			
			//Read in the final score for the game.
			input = in.readLine();
			System.out.println(input);
			
			sys.close();
			out.close();
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
