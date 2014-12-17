package SortComparisons; /**
 * SortComparison.java
 * Jordan Bossman
 * 3/25/2012
 * Main class file to create arrays of Candidates and sort them.
 * The time will be tracked for how long each sort takes and outputted to a file.
 */
 
import java.io.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class SortComparison 
{
	public static void main(String[] args)
	{
		Random rand = new Random(); //New random number generator.
		File file = new File("sortoutput.txt");//The file to write the output into.
		PrintWriter writer = null; //The writer to write to the output file.
		try
		{
			writer = new PrintWriter(file);
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"Error", 
				"There has been a fatal error with the output file. Closing...", JOptionPane.ERROR_MESSAGE);
		}

		writer.println("\t1000\t10000\t100000\t500000");

		long[] results1 = sort(rand, 1000); //Time array for the 1,000 Candidate array sorts.
		long[] results2 = sort(rand, 10000); //Time array for the 10,000 Candidate array sorts.
		long[] results3 = sort(rand, 100000); //Time array for the 100,000 Candidate array sorts.
		long[] results4 = sort(rand, 50000); //Time array for the 500,000 Candidate array sorts.
		
		final int QS = 0; //Quick sort index for the time values in the given time arrays.
		final int MERGE = 1; //Merge sort index for the time values in the given time arrays.
		final int HEAP = 2; //Heap sort index for the time values in the given time arrays.
		final int SEL = 3; //Selection sort index for the time values in the given time arrays.
		writer.println(String.format("Quick Sort\t%d\t%d\t%d\t%d", results1[QS], results2[QS], results3[QS], results4[QS]));
		writer.println(String.format("Merge Sort\t%d\t%d\t%d\t%d", results1[MERGE], results2[MERGE], results3[MERGE],  results4[MERGE]));
		writer.println(String.format("Heap Sort\t%d\t%d\t%d\t%d", results1[HEAP], results2[HEAP], results3[HEAP], results4[HEAP]));
		writer.println(String.format("Selection Sort\t%d\t%d\t%d\t%d", results1[SEL], results2[SEL], results3[SEL], results4[SEL]));
		writer.close();		
	}    
		
	private static long[] sort(Random rand, int num)
	{
		//Method creates four identical Candidate arrays to sort using
		//quick sort, merge sort, heap sort and selection sort, ecords the times
		//of these sorts, and determines the worst case times, and returns a long array
		//containing the worst case time for each sort given the number of objects to sort.
		final int SIM = 5; //Number of simulations for each sorting run.
		long[] quickResults = new long[SIM]; //Time results for quck sort.
		long[] mergeResults = new long[SIM]; //Time results for merge sort.
		long[] heapResults = new long[SIM]; //Time results for heap sort.
		long[] selectionResults = new long[SIM]; //Time results for selection sort.
		long[] results = new long[4]; //Worst case time results for all four sorts.
		long time1; //Time before the sort happens.
		long time2; //Time after the sort happens.
		
		for(int i = 0; i < SIM; i++)
		{	
			//Create the four identical arrays.	
			Candidate[] quick = new Candidate[num];
			Candidate[] merge = new Candidate[num];
			Candidate[] heap = new Candidate[num];
			Candidate[] selection = new Candidate[num];
		
			fillArrays(rand, quick, heap, merge, selection);
		
			//Sort the arrays using each of the four sorts.
			time1 = System.currentTimeMillis();
			Sort.quickSort(quick);
			time2 = System.currentTimeMillis();
			quickResults[i] = time2 - time1;
			
			time1 = System.currentTimeMillis();
			Sort.mergeSort(merge);
			time2 = System.currentTimeMillis();
			mergeResults[i] = time2 - time1;
			
			time1 = System.currentTimeMillis();
			Sort.heapSort(heap);
			time2 = System.currentTimeMillis();
			heapResults[i] = time2 - time1;
			
			time1 = System.currentTimeMillis();
			Sort.selectionSort(selection);
			time2 = System.currentTimeMillis();
			selectionResults[i] = time2 - time1;			
		}
		
		results[0] = findWorstCase(quickResults);
		results[1] = findWorstCase(mergeResults);
		results[2] = findWorstCase(heapResults);
		results[3] = findWorstCase(selectionResults);
		
		return results;		
	}

	private static long findWorstCase(long[] array)
	{
		//Given an array of longs, determines and returns the highest value in the array.
		long high = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] > high)
				high = array[i];
		}
		
		return high;
	}

	private static void fillArrays(Random rand, Candidate[] quickArray, Candidate[] heapArray,
	     										Candidate[] mergeArray, Candidate[] selArray)
	{
		//Fills each array using the random number generator passed in with the same
		//Candidate object information.
		int length = quickArray.length; //Number of Candidates to make.
		StringBuffer string = new StringBuffer(); //Houses the String for each Candidate.
		for(int i = 0; i < length; i++)
		{
			int serial = rand.nextInt(10000) + 1; //Random serial number between 1-10,000.
			for(int j = 0; j < 10; j++)
			{
				char character = (char)(rand.nextInt(26) + 'A'); //Random uppercase character.
				string.append(character);
			}
			
			quickArray[i] = new Candidate(serial, string.toString());
			heapArray[i] = new Candidate(serial, string.toString());
			mergeArray[i] = new Candidate(serial, string.toString());
			selArray[i] = new Candidate(serial, string.toString());
			string = new StringBuffer();
		}
	}
}