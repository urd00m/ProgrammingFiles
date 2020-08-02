package Silver2019;
/*
ID: alwang
LANG: JAVA
TASK: planting
 */

import java.util.*;
import java.io.*;

public class planting {
	static int[] grassType; // marked with grass type 1-3
	static ArrayList<Integer>[] pathway; // pahtway is index-index+1 true if their is a pathway
	static int n;
	static int min;
	static PriorityQueue<Integer> cantBe = new PriorityQueue<Integer>();

	public static void main(String args[]) throws IOException {
		// input
		BufferedReader f = new BufferedReader(new FileReader("planting.in"));
		n = Integer.parseInt(f.readLine());
		grassType = new int[n + 1]; // 0 is dead spot
		pathway = new ArrayList[n + 1]; // 0 is a dead spot
		StringTokenizer in;
		for(int i = 1; i < n+1; i ++) {
			pathway[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < n; i++) {
			in = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(in.nextToken());
			int b = Integer.parseInt(in.nextToken());
			pathway[a].add(b);
			pathway[b].add(a);
		}
		f.close();

		// algorithm
		// greedy
		grassType[1] = 1;
		min = 1;
		for (int i = 2; i < n + 1; i++) {
			cantBe.clear();
			web(i, 0);
			//System.out.println(cantBe.peek());
			int a = 0;
			if (cantBe.size() > 0) {
				a = cantBe.remove();
				if(a > 1) {
					grassType[i] = 1; 
					cantBe.clear();
				}
				while (cantBe.size() > 0) {
					int b = cantBe.remove();
					if (b - a > 1) {
						grassType[i] = a + 1;
						if(a+1 > min) min = a+1; 
						break; 
					}
					a = b; 
				}
				if(grassType[i] == 0) {
					grassType[i] = a+1; 
					if(a+1 > min) min = a+1; 
				}
			}
			else 
				grassType[i] = 1; 
		}

		// output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		out.println(min);
		out.close();

	}

	// depthcount starts at zero, once it reaches 3 it should stop
	public static void web(int startValue, int depthCount) {
		if (depthCount != 3) {
			if (grassType[startValue] != 0 && cantBe.contains(grassType[startValue]) == false) {
				cantBe.add(grassType[startValue]);
			}
			for (int i = 0; i < pathway[startValue].size(); i++) {
				web(pathway[startValue].get(i), depthCount + 1);
			}
		}
	}
}
