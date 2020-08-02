//package open2019;
/*
ID: alwang
LANG: JAVA
TASK: snakes
 */
import java.io.*;
import java.util.*;

public class snakes {
	static int n, k; 
	static int[][] dp;
	static int[] snakes;  
	static int ret; 
	static int INF = 1000000000; 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("snakes.in")); StringTokenizer input = new StringTokenizer(f.readLine()); 
		n = Integer.parseInt(input.nextToken()); k = Integer.parseInt(input.nextToken());
		snakes = new int[n];
		input = new StringTokenizer(f.readLine()); 
		for(int i = 0; i < n; i++) {
			snakes[i] = Integer.parseInt(input.nextToken()); 
		}
		
		//dp: bottom up  makes the most sense
		dp = new int[n][k+1]; 
		int mx = 0; int sum = 0;  
		for(int i = 0; i < n; i++) {
			mx = Math.max(snakes[i], mx); sum += snakes[i]; 
			dp[i][0] = ((i+1)*mx) - sum; 
		}



		for(int i = 1; i < k+1; i++) { //the switches 
			for(int j = 0; j < n; j++) { //the current group 
				dp[j][i] = INF; 
				mx = snakes[j]; sum = snakes[j];  
				for(int a = j-1; a >= 0; a--) {
					dp[j][i] = Math.min(dp[j][i], dp[a][i-1]+(mx*(j-a)-sum)); 
					mx = Math.max(snakes[a], mx); sum += snakes[a];  
				}
			}
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out"))); 
		out.println(dp[n-1][k]);
		out.close();
		
	}
	

}
