
//package open2020;
/*
ID: alwang
LANG: JAVA
TASK: exercise
 */

import java.util.*;
import java.io.*;

public class exercise {
	static int n, m;
	static ArrayList<Integer> primes = new ArrayList<Integer>(); 
	public static void main(String args[]) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("exercise.in"));
		StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken());
		m = Integer.parseInt(input.nextToken());
		
		long sum = 0;
		for (int i = 1; i <= n; i++)
			sum = (sum + i) % m;
		
		
		
		//generate primes 
		int[] dp = new int[n+1];
		Arrays.fill(dp, 1);
		dp[0] = 0; dp[1] = 0; 
		for(int i = 2; i <= n; i++) {
			if(dp[i] == 1) {
				for(int j = i*i; j <= n; j += i) {
					dp[j]= 0; 
				}
				primes.add(i); 
			}
		}
		
		//then find the different numbers that can be created when combining primes 
		//dp to find the sum
		long[][] ret = new long[primes.size()][n+1];
		for(int i = 0; i < primes.size(); i++) {
			for(int j = 0; j <= n; j++) {
				if(i != 0) ret[i][j] = ret[i-1][j];
				else ret[i][j] = 1; 
				
				long cur = primes.get(i);
				while(cur <= j) {
					if(i != 0) ret[i][j] = (ret[i][j] + (cur*ret[i-1][j-(int)(cur)])%m)%m;
					else ret[i][j] = (ret[i][j] + cur)%m;
					cur = (cur*primes.get(i))%m; //increasing power 
				}
			}
		}

		
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
		out.println(ret[primes.size()-1][n]);
		out.close();
	}
}
