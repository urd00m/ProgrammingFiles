package Bronze2018;
/*
ID: alwang
LANG: JAVA
TASK: blist
 */
import java.util.*;
import java.io.*;
public class blist {
	static int n;
	static cow[] cows; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("blist.in"));
		n = Integer.parseInt(f.readLine());
		cows = new cow[n];
		StringTokenizer in;
		int c,d,e; 
		for( int i = 0; i < n; i++) {
			in = new StringTokenizer(f.readLine());
			c = Integer.parseInt(in.nextToken());
			d = Integer.parseInt(in.nextToken());
			e = Integer.parseInt(in.nextToken());
			cows[i] = new cow(c,d,e);
		}
		
		//sort
		Arrays.sort(cows, new Comparator<cow>() {
			@Override 
			public int compare(cow a1, cow a2) {
				return a1.getStart() - a2.getStart(); 
			}
		});
		
		//algorithm 
		//brute force, run through up to 1000, end if all values "get passed"
		int maxB = 0;
		int buckets = 0; 
		for(int t = 1; t < 1001; t++) {
			for(int i = 0; i < n; i++) { //checks to see if any cows are in range 
				if(cows[i].getStart() == t) {
					buckets += cows[i].getBucket();
				}
				if(cows[i].getEnd() == t) {
					buckets -= cows[i].getBucket();
					cows[i].passed();
				}
			}
			if(buckets > maxB) maxB = buckets; 
			if(cows[n-1].getPassed() == true) break; 
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		out.println(maxB + "");
		out.close(); 
	}
	
	static class cow {
		int start;
		int end;
		int buckets;
		boolean passed = false; 
		public cow(int s, int t, int b) {
			start = s;
			end = t;
			buckets = b; 
		}
		public int getStart() {
			return start;
		}
		public int getEnd() {
			return end; 
		}
		public int getBucket() {
			return buckets; 
		}
		public boolean getPassed() {
			return passed; 
		}
		public void passed() {
			passed = true; 
		}
	}

}
