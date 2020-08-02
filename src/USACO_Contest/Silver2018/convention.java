package Silver2018;
/*
ID: alwang
LANG: JAVA
TASK: convention
*/
import java.util.*;
import java.io.*;

public class convention {
	static int n; 
	static int m;
	static int c; 
	static int[] cows; 
	static int currentIndex = 0; 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("convention.in"));
		StringTokenizer in = new StringTokenizer(f.readLine());
		n = Integer.parseInt(in.nextToken());
		m = Integer.parseInt(in.nextToken());
		c = Integer.parseInt(in.nextToken());
		cows = new int[n];
		in = new StringTokenizer(f.readLine());
		for(int i = 0; i < n; i ++) {
			cows[i] = Integer.parseInt(in.nextToken());
		}
		Arrays.sort(cows);
		
		//algorithm greedy 
		int time = binarySearch(0, 1000000000);
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		out.println(time);
		out.close();
		f.close();
	}
	//sees if time works
	public static boolean run(int time) {
		currentIndex = 0; 
		int firstCow;
		int firstIndex;
		for(int i = 0; i < m; i++) {
			firstCow = cows[currentIndex]; 
			firstIndex = currentIndex; 
			currentIndex++;
			while( (currentIndex != firstIndex +c) && !(cows[currentIndex] > firstCow + time) ) {
				currentIndex++;
				if(currentIndex == n) return true;
			}
		}
		return false;
	}
	
	
	public static int binarySearch(int lower, int upper) {
		if(lower == upper) return lower;
		else if(lower+1 == upper) {
			if( run(lower) == true) return lower;
			else return upper; 
		}
		
		int mid = (lower+upper)/2; 
		if(run(mid) == true) {
			return binarySearch(lower, mid);
		}
		else {
			return binarySearch(mid, upper);
		}
	}
}
