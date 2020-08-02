//package open2018;
/*
ID: urd00m
LANG: JAVA
TASK: talent
 */
import java.io.*;
import java.util.*;

public class talent {
	static int n, w; 
	static int[][] cows; //stores w then t 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("talent.in")); StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken()); w = Integer.parseInt(input.nextToken()); 
		cows = new int[n][2]; 
		for(int i = 0; i < n; i++) {
			input = new StringTokenizer(f.readLine()); 
			cows[i][0] = Integer.parseInt(input.nextToken()); 
			cows[i][1] = Integer.parseInt(input.nextToken()); 
		}
		f.close();
		
		//binary search + dp 
		int l = 0; 
		int r = 250001;
		while(l+1 < r) {
			int mid = (l+r)/2; 
			if(pos(mid) == true) l = mid; 
			else r = mid; 
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out"))); 
		out.println(l);
		out.close();
	}
	
	public static boolean pos(int x) {
		long[] dp = new long[w+1]; 
		Arrays.fill(dp, -1000000000000000000L); //to avoid overflow 
		dp[0] = 0; 
		for(int i = 0; i < n; i++) {
			int curW = cows[i][0]; 
			int curT = cows[i][1]; 
			for(int j = w; j >= 0; j--) {
				if(dp[j] != -1000000000000000000L) {
					int next = Math.min(w, j+curW); 
					dp[next] = Math.max(dp[next], dp[j]+score(curW, curT, x)); 
				}
			}
		}
		return dp[w] >= 0 ? true : false; 
	}
	
	
	public static long score(int weight, int t, int x) {
		return (1000L*(long)t-(long)x*(long)weight); 
	}
}
