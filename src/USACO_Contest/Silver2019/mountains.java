package Silver2019;
/*
ID: alwang
LANG: JAVA
TASK: mountains
 */
import java.util.*;
import java.io.*;

public class mountains {
	static int n; 
	static peak[] peaks; 
	static int unhiddenPeaks; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
		n = Integer.parseInt(f.readLine());
		unhiddenPeaks = n; 
		peaks = new peak[n]; 
		StringTokenizer in; 
		for(int i = 0; i < n; i++) {
			in = new StringTokenizer(f.readLine());
			peaks[i] = new peak(Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken()));
		}
		f.close();
		
		//sort
		Arrays.sort(peaks, new Comparator<peak>() {
			@Override
			public int compare(peak a, peak b) {
				return a.xBLeft - b.xBLeft; 
			}
		});
		
		//algorithm 
		// brute force modified
		//go through and mark all of the ones that are hidden 
		for(int i = 0; i < n; i++) {
			int	j = i-1;
			while(j >= 0 && isContained(i, j) == true ) {
				if(peaks[j].hidden == false) {
					peaks[j].hidden = true; 
					unhiddenPeaks--; 
				}
				j--;
			}
			j = i+1;
			while(j < n && isContained(i, j) == true ) {
				if(peaks[j].hidden == false) {
					peaks[j].hidden = true; 
					unhiddenPeaks--; 
				}
				j++;
			}
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		out.println(unhiddenPeaks);
		out.close();
	}
	public static boolean isContained(int index, int check) {
		if(peaks[index].x < peaks[check].x && (peaks[index].x+peaks[index].y-peaks[check].x) > peaks[check].y) {
			return true; 
		}
		else if(peaks[index].x > peaks[check].x && (peaks[index].y-peaks[index].x + peaks[check].x) > peaks[check].y) {
			return true; 
		}
		else return false;
	}
	public static class peak{ 
		public int x; 
		public int y; 
		public int xBLeft; 
		public int xBRight; 
		boolean hidden = false; 
		peak(int a, int b) {
			x = a;
			y = b; 
			xBLeft = x-y; 
			xBRight = x+y; 
		}
	}
}
