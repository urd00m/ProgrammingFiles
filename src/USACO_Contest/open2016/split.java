//package open2016;
/*
ID: alwang
LANG: JAVA
TASK: split
 */
import java.io.*;
import java.util.*;

public class split {
	static int n; 
	static pair[] cows; 
	static long area;  
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("split.in")); StringTokenizer input; 
		n = Integer.parseInt(f.readLine());
		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, maxx = -1, maxy = -1; 
		cows = new pair[n]; 
		for(int i = 0; i < n; i++) {
			input = new StringTokenizer(f.readLine()); 
			int a = Integer.parseInt(input.nextToken()); 
			minx = Math.min(minx, a); 
			maxx = Math.max(maxx, a); 
			int b = Integer.parseInt(input.nextToken()); 
			miny = Math.min(miny, b); 
			maxy = Math.max(maxy, b);
			cows[i] = new pair(a, b); 
		}
		area = (long)((long)(maxx)-(long)(minx))*((long)(maxy)-(long)(miny)); //initial area covered 
		f.close();
		
		//algorithm: using SPT we first calculate the area saved 
		long areaSaved = -1; 
		//going horozontally left to right  
		Arrays.sort(cows, new Comparator<pair>() { @Override public int compare(pair a, pair b) {return a.x-b.x;}});
		//we need to be able to find the min and max of the y quickly 
		sparseTableDT yMin = new sparseTableDT(n, cows, 1, 1); 
		sparseTableDT yMax = new sparseTableDT(n, cows, -1, 1); 
		for(int i = 0; i < n-1; i++) {
			long areaL = ((long)cows[i].x-(long)cows[0].x)*((long)yMax.query(0, i)-(long)yMin.query(0, i));
			long areaR = ((long)cows[n-1].x-(long)cows[i+1].x)*((long)yMax.query(i+1, n-1)-(long)yMin.query(i+1, n-1));
			areaSaved = Math.max(areaSaved, (area-areaL-areaR));  
		}
		Arrays.sort(cows, new Comparator<pair>() { @Override public int compare(pair a, pair b) {return a.y-b.y;}});
		sparseTableDT xMin = new sparseTableDT(n, cows, 1, 0); 
		sparseTableDT xMax = new sparseTableDT(n, cows, -1, 0); 
		for(int i = 0; i < n-1; i++) {
			long areaL = ((long)cows[i].y-(long)cows[0].y)*((long)xMax.query(0, i)-(long)xMin.query(0, i));
			long areaR = ((long)cows[n-1].y-(long)cows[i+1].y)*((long)xMax.query(i+1, n-1)-(long)xMin.query(i+1, n-1));
			areaSaved = Math.max(areaSaved, (area-areaL-areaR)); 
		}
		//output 
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out"))); 
		out.println(areaSaved);
		out.close();
	}

	//static RMQ 
	public static class sparseTableDT {
		public int MAX_N = 1000000;
		public int LOG_TWO_N = 20;
		public int[] _A; int[][] SpT; int s; 
		public sparseTableDT(int n, pair[] A, int sgn, int type) { //type == 0 mins x type == 1 means y  //sgn == -1 means range maximum query sgn == 1 means range min query 
			s = sgn; 
			_A = new int[n]; 
			SpT = new int[n][(int)(Math.log10(n*1.0)/Math.log10(2.0) + 1)]; 
			for(int i = 0; i < n; i++) {
				if(type == 0) _A[i] = sgn*A[i].x; 
				else _A[i] = sgn*A[i].y; 
				SpT[i][0] = i; //RMQ of sub array starting at index i+length 2^0=1
			}
			for(int j = 1; (1<<j) <= n; j++) {
				for(int i = 0; i + (1<<j)-1 < n; i++) {
					if(_A[SpT[i][j-1]] < _A[SpT[i+(1<<(j-1))][j-1]]) {
						SpT[i][j] = SpT[i][j-1]; 
					}
					else {
						SpT[i][j] = SpT[i+(1<<(j-1))][j-1]; 
					}
				} 
			}
		}
		public int query(int i, int j) {
			int k = (int)Math.floor(Math.log10((double)j-i+1)/Math.log10(2.0)); 
			if(_A[SpT[i][k]] <= _A[SpT[j-(1<<k)+1][k]]) return s*_A[SpT[i][k]]; 
			else return s*_A[SpT[j-(1<<k)+1][k]]; //I want the actual value 
		}
	}
	
	public static class pair {
		int x, y; 
		public pair(int a, int b) {
			x = a; 
			y = b; 
		}
	}

}
