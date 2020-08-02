//package open2020;
/*
ID: alwang
LANG: JAVA
TASK: haircut
 */

import java.io.*;
import java.util.*;

public class haircut {
	public static int n; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("haircut.in")); StringTokenizer input; 
		n = Integer.parseInt(f.readLine()); input = new StringTokenizer(f.readLine()); 
		
		
		//algorithm: BIT 
		FenwickTree greaterThan = new FenwickTree(n+1);
		long[] output = new long[n]; 
		for(int i = 0; i < n; i++) {
			int cur = Integer.parseInt(input.nextToken());
			if(cur < n-1) output[cur+1] += greaterThan.rsq(cur+1, n);
			if(cur != 0) greaterThan.adjust(cur, 1);
		}
		f.close();
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out"))); 
		long sum = 0; 
		for(long item : output) {
			sum += item; 
			out.println(sum);
		}
		out.close();
	}
	

	public static class FenwickTree {
		int[] ft;
		public FenwickTree(int n) {
			ft = new int[n+1]; 
		}
		public int LSOne(int x) {
			return (x & (-x)); 
		}
		public int rsq(int b) {
			int sum = 0; for(; b != 0; b-= LSOne(b)) sum+= ft[b]; 
			return sum; 
		}
		public int rsq(int a, int b) {
			return rsq(b) - (a == 1 ? 0 : rsq(a-1));  
		}
		public void adjust(int k, int v) {
			for(; k < ft.length; k += LSOne(k)) ft[k] += v; 
		}
	}
}
