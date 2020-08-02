//package open2017;

/*
ID: alwang
LANG: JAVA
TASK: art2
 */
import java.io.*;
import java.util.*;

public class art2 {
	static int n;
	static pair[] interval;
	static int numInt = 0;

	public static void main(String args[]) throws IOException {
		// input
		BufferedReader f = new BufferedReader(new FileReader("art2.in"));
		n = Integer.parseInt(f.readLine());
		interval = new pair[n+1]; 
		for (int i = 0; i < n; i++) {
			int cur = Integer.parseInt(f.readLine());
			if (interval[cur] == null) { // first occurence
				interval[cur] = new pair(i, i, cur);
				numInt++;
			} else { // choose the maximum ending idx
				if(interval[cur].end != interval[cur].start) interval[cur].inside.add(interval[cur].end); 
				interval[cur].end = i;
			}
		}
		// set any nulls to a start of 100001
		for (int i = 0; i < n+1; i++) {
			if (interval[i] == null)
				interval[i] = new pair(100001, -1, -1);
		}
		f.close();
		// algorithm: store the intervals then sort the intervals based on increasing
		// order
		// then for each "hill" calculate the rounds need and take the maximum out of
		// all the mounds
		// if at any point two intervals overlap then return -1 and finish
		Arrays.sort(interval, new Comparator<pair>() { // increasing order
			@Override
			public int compare(pair a, pair b) {
				return a.start - b.start;
			}
		});
		Stack<pair> hill = new Stack<pair>();
		int roundsNeeded = 1;
		hill.add(interval[0]);
		int zero = interval[0].color == 0 ? -1 : 0; 
		for (int i = 1; i < numInt; i++) {
			pair cur = interval[i];
			pair top = hill.peek();
			int status = compare(top, cur);
			if(cur.color == 0) zero = -1; 
			while(true) { 
				if (status == 0) {
					hill.add(cur);
					roundsNeeded = Math.max(roundsNeeded, hill.size()+zero);
					break; 
				}
				else if (status == 1) {
					roundsNeeded = -1;
					break;
				} else { // status == 2
					roundsNeeded = Math.max(roundsNeeded, hill.size()+zero);
					if(hill.size() > 0) {
						pair temp = hill.pop();
						if(temp.color == 0) zero = 0;
						if(hill.size() > 0) top = hill.peek(); 
					} else {
						hill.add(cur); 
						break; 
					}
				}
				status = compare(top, cur);
			}  //if status == 2 we need to continue removing intervals until we are  
			if(roundsNeeded == -1) break; 		
		}

		// output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out"))); 
		out.println(roundsNeeded); 
		out.close();
	}

	// takes in 2 pairs and checks to returns interval status
	// 0 if b is inside a, 1 if b has part of its interval in a, 2 if a and b are
	// disjoint
	public static int compare(pair a, pair b) {
		assert (a.start < b.start);
		for(int item : a.inside) {
			if(item > b.start && item < b.end) return 1; //if it cuts off a color inside itself 
		}
		if (a.start < b.start && b.end < a.end)
			return 0; // b inside a
		else if (b.start > a.end)
			return 2; // due to the sorted function b should never be left of a
		else
			return 1; // if it isn't inside a or completely outside a then it must be partly inside a
		
	}

	public static class pair {
		int start;
		int end;
		int color;
		ArrayList<Integer> inside = new ArrayList<Integer>(); 
		public pair(int a, int b, int c) {
			start = a;
			end = b;
			color = c;
		}
	}
}
