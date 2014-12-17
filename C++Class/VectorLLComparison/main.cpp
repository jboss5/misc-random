/*
	Jordan Bossman
	CSC 402
	Project #4
	Performs and times different sorts and copies to determine
	which actions are more efficient.
*/

#include <stdio.h>
#include <iostream>
#include <vector>
#include <time.h>
#include <list>
#include <ctime>
#include <algorithm>

using namespace std;

int main()
{
	//Main function that calculates the time for certain sorts to be done.
	clock_t start, end;
	int size, i;
	srand(time(NULL)); //Seed the random number generator function.

	for(i = 1, size = 2000; i < 11; i++, size *= 2)
	{
		//Loops 10 times and increments the size by 2 (2000 -> 1000000). 
		vector<int> vi0(size);
		vector<int>::iterator viIter;
		//Initialize the beginning vector.
		for(viIter = vi0.begin(); viIter != vi0.end(); viIter++)
			*viIter = rand();

		//Define the other vector and the list.
		vector<int> vi(vi0);
		list<int> li(vi0.begin(), vi0.end());

		//Sorting just the vector.
		start = clock();
		sort(vi.begin(),vi.end());
		end = clock();
		double sort_vector_time = (double)(end - start)/CLOCKS_PER_SEC;
	
		//Sorting just the list.
		start = clock();
		li.sort();
		end = clock();
		double sort_list_time = (double)(end - start)/CLOCKS_PER_SEC;
	
		//Clears the list and vector, plus resizes the vector. Then add the values from
		//the original vector to the list.
		li.clear();
		vi.clear();
		vi.resize(size);
		list<int>::iterator it = li.begin();
		li.insert(it, vi0.begin(), vi0.end());
	
		//Copies the values from the list to the vector, sorts the vector,
		//then copies them back to the original list.
		start = clock();
		copy(li.begin(), li.end(), vi.begin());
		sort(vi.begin(), vi.end());
		copy(vi.begin(), vi.end(), li.begin());
		end = clock();
		double sort_list_via_copying_to_vector = (double)(end - start)/CLOCKS_PER_SEC;
			
		//Prints out the values for each run.
		cout << "Run: " << i << " size: " << size << endl;
		cout << "sort_vector_time: " << sort_vector_time << endl;
		cout << "sort_list_time: " << sort_list_time << endl;
		cout << "sort_list_via_copying_to_vector: " << sort_list_via_copying_to_vector << endl << endl;;
	}

	cin >> i;
	return 0;
}
