package Silver2018;
/*
ID: alwang	
LANG: JAVA
TASK: mooyomooyo
 */

import java.io.*;
import java.util.*;


public class mooyomooyo {
	static int n;
	static int k; 
	static int[][] board; 
	static int count = 0; 
	static boolean switched = false; 
	static boolean wasFlood = true; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("mooyomooyo.in")); 
		StringTokenizer in = new StringTokenizer(f.readLine());
		n = Integer.parseInt(in.nextToken());
		k = Integer.parseInt(in.nextToken());
		board = new int[n][10]; 
		for(int i = 0; i < n; i++) {
			String row = f.readLine();
			for(int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(row.substring(j, j+1));
			}
		}
		//algorithm
		//flood fill the parts that match    if marked means it has a 1 in front of it 
		//then gravity 
		//repeat until nothing can be popped 
		while(wasFlood == true) {
			wasFlood = false; 
			//search algorithm, finding matches 
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < 10; j++) {
					if(board[i][j] != 0 && board[i][j] < 10) {
						flood(i, j, board[i][j]);
						if(count >= k) {  
							switchFlood(i, j, board[i][j]); 
						}
						if(count >= k) wasFlood = true; 
						count = 0; 
						switched = false; 
					}
				}
			}
			
			//gravity, shifts everything down, no zeros on the bottom 
			for(int i = n-1; i >= 0 ; i--) {
				for(int j = 0; j < 10; j++) {
					if(board[i][j] > 10) board[i][j] = board[i][j]%10;
					int counter = i;
					if(i != n-1 && board[i][j] != 0) {
						while(counter < n-1 && board[counter+1][j] == 0) {
							board[counter+1][j] = board[counter][j];
							board[counter][j] = 0;
							counter++;
						}
					}
				}
			}

		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 10; j++) {
				out.print("" + board[i][j]);
			}
			out.println();
		}
		out.close();
		
	}
	

	public static void flood(int i, int j, int value) {
		count++;
		board[i][j] += 10;
		if(i != 0 && board[i-1][j] == value) flood(i-1, j, value);
		if( i != n-1 && board[i+1][j] == value) flood(i+1, j , value);
		if (j != 0 && board[i][j-1] == value) flood(i, j-1, value);
		if(j != 9 && board[i][j+1] == value) flood(i, j+1, value);

	}
	
	public static void switchFlood(int i, int j, int value) {
		board[i][j] = 0; 
		if(i != 0 && board[i-1][j] == value) switchFlood(i-1, j, value);
		if( i != n-1 && board[i+1][j] == value) switchFlood(i+1, j , value);
		if (j != 0 && board[i][j-1] == value) switchFlood(i, j-1, value);
		if(j != 9 && board[i][j+1] == value) switchFlood(i, j+1, value);
		
	}
}
