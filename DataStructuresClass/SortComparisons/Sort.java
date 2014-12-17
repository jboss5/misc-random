package SortComparisons;

/**
 * Sort.java
 * Jordan Bossman
 * File that contains an assortment of sorting routines.
 */
 
public class Sort 
{
	private Sort()
	{
	}

	public static void selectionSort(Comparable[] array)
	{
		//Sorts a given array into correct order using Selection Sort.
		int lowVal; //The lowest value.
		Comparable temp; //Temp value for swapping.
		
		for(int i = 0; i < array.length - 1; i++)
		{
			lowVal = i;
			for(int k = i + 1; k < array.length; k++)
			{
				if(((Comparable)array[k]).compareTo(array[lowVal]) < 0)
					lowVal = k;
			}
			
			temp = array[i];
			array[i] = array[lowVal];
			array[lowVal] = temp;			
		}
	}  
	
	public static void mergeSort(Comparable[] array)
	{
		//Calls the merge sort method and starts the start & end at the beginning and end of the array.
		mergeSort(array, 0, array.length - 1);
	}
	
	private static void mergeSort(Comparable[] array, int start, int end)
	{
		//Sorts the given array into correct order using Merge Sort.
		int mid = (start + end) / 2; //Mid point of the given start and end.
		Comparable[] lowArray = new Comparable[array.length / 2]; //The low half of the original array.
		Comparable[] highArray = new Comparable[array.length - lowArray.length]; //The high half of the original array.

		for(int i = 0; i < array.length; i++)
		{
			if(i > lowArray.length - 1)
				highArray[i - lowArray.length] = array[i];
			else
				lowArray[i] = array[i];
		}

		//If both subarrays only have one element each.
		if(lowArray.length < 2 && highArray.length < 2)
		{
			mergeArrays(lowArray, highArray, array);
			return;
		}
			
		mergeSort(lowArray, start, mid);
		mergeSort(highArray, mid, start);
		mergeArrays(lowArray, highArray, array);
	}  
	
	private static void mergeArrays(Comparable[] low, Comparable[] high, Comparable[] orig)
	{
		//Merges the two arrays together and puts the values in their final order into the original array.
		int lowCount = 0; //The low array's counter.
		int highCount = 0; //The high array's counter.
		int origCount = 0; //The original array's counter.
		while(origCount < orig.length)
		{
			if(lowCount > low.length - 1)
			{
				for(int k = highCount; k < high.length; k++)
				{
					orig[origCount] = high[k];
					origCount++;
				}
					
				return;
			}
			else if(highCount > high.length - 1)
			{
				for(int k = lowCount; k < low.length; k++)
				{
					orig[origCount] = low[k];
					origCount++;
				}
					
				return;
			}
			
			if(((Comparable)low[lowCount]).compareTo(high[highCount]) < 0)
			{
				orig[origCount] = low[lowCount];
				lowCount++;
				origCount++;
			}
			else
			{
				orig[origCount] = high[highCount];
				highCount++;
				origCount++;
			}
		}
	}
	
	public static void quickSort(Comparable[] array)
	{
		//Calls the quick sort method starting at the beginning and ending at the end of the array.
		quickSort(array, 0, array.length - 1);
	}
	
	private static void quickSort(Comparable[] array, int start, int end)
	{
		//Method that sorts the given array into correct order by using Quick Sort.
	    Comparable pivotVal = array[start]; //Value of the pivot.
	    
	  	if((end - start) < 2)
	    {
	    	if((end - start) < 0)
	    		return;
	    	else if(array[end].compareTo(pivotVal) < 0)
	    	{
	    		Comparable temp = pivotVal;
				array[start] = array[end];
				array[end] = pivotVal;
	    		return;
	    	}
	    	else
	    		return;
	    }
	    
	    int pivot = partition(array, start, end); //The element where the pivot is after partitioning.
	    int high = pivot + 1; //The start of the high side of the array.
	    int low = pivot - 1; //The end of the low side of the array.
	    
	  	if(high > array.length - 1)
	    	quickSort(array, pivot, end);
	    else
	   		quickSort(array, high, end);
	    
	    if(low < 0)
	    	quickSort(array, start, pivot);
	    else
	    	quickSort(array, start, low);	
	}
	
	private static int partition(Comparable[] array, int start, int end)
	{
		//Partitions the given array segment by moving all the values less than the pivot to the left
		//side of the array segment and all the values higher than the pivot to the right side of the array.
		//Returns the element of the final resting position of the pivot in the array.
		int lowCounter = start + 1; //Starts the low counter at the element after the pivot.
		int highCounter = end; //Starts the high counter at the end of the segment.
	    Comparable pivotVal = array[start]; //The pivot value.
		
		for(int i = start + 1; i <= end; i++)
	    {
	    	while(array[lowCounter].compareTo(pivotVal) < 0 && lowCounter < end)
	    		lowCounter++;
	    		
	    	while(array[highCounter].compareTo(pivotVal) > 0 && highCounter > start)
	    		highCounter--;

			if(pivotVal.compareTo(array[lowCounter]) == 0 && lowCounter < end)
	    		lowCounter++;
	    	
	   		else if(pivotVal.compareTo(array[highCounter]) == 0 && highCounter > start)
	    		highCounter--;
			
			if(lowCounter >  highCounter)
	    		break;		    	
			else
    		{
	    		Comparable temp = array[highCounter];
    			array[highCounter] = array[lowCounter];
    			array[lowCounter] = temp;
	   		}
	    }
	    
	    Comparable temp = array[highCounter];
		array[highCounter] = pivotVal;
		array[start] = temp;	
	    return highCounter;
	}
	
	public static void heapSort(Comparable[] array)
	{
		//Method sorts a given array by using Heap Sort.
		Heap heap = new Heap(array);
		for(int i = array.length - 1; i > -1; i--)
			array[i] = heap.remove();
	}
}