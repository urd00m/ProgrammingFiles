package Silver2019;
/*
ID: alwang
LANG: JAVA
TASK: perimeter
 */
import java.io.*;
import java.util.*;

public class perimeter {
	static String[][] ice; 
	static int maxArea = 0; 
	static int maxPer = 0;  
	static int n; 
	static int tempArea; 
	static int tempperimeter; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		n = Integer.parseInt(f.readLine());
		ice = new String[n][n]; 
		for(int i = 0; i < n; i++) {
			String input = f.readLine();
			for(int j = 0; j < n; j++) {
				ice[i][j] = input.substring(j, j+1);
			}
		}
		f.close();
		
		//algorithm
		//flood fill
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(ice[i][j].equals("#"))
					fill(i ,j, true); 
			}
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		out.println(maxArea + " " + maxPer);
		out.close();
	}
	
	public static void fill(int x, int y, boolean start) {
		tempArea++; 
		ice[x][y] = "A"; 
		if(x == 0 || ice[x-1][y].equals("."))
			tempperimeter++; 
		if( x == n-1 || ice[x+1][y].equals("."))
			tempperimeter++; 
		if(y == 0 || ice[x][y-1].equals("."))
			tempperimeter++; 
		if( y == n-1 || ice[x][y+1].equals("."))
			tempperimeter++; 
		
		if(x != 0 && ice[x-1][y].equals("#"))
			fill(x-1, y, false);
		if( x != n-1 && ice[x+1][y].equals("#"))
			fill(x+1, y, false); 
		if(y != 0 && ice[x][y-1].equals("#"))
			fill( x, y-1, false);
		if( y != n-1 && ice[x][y+1].equals("#"))
			fill( x, y+1, false);
		
		if(start == true) {
			if(tempArea > maxArea) {
				maxArea = tempArea;
				maxPer = tempperimeter; 
			}
			if(tempArea == maxArea && tempperimeter < maxPer) {
				maxPer = tempperimeter;
			}
			//reset
			tempArea = 0;
			tempperimeter = 0; 
		}
	}
}
