//package open2018;
/*
ID: urd00m
LANG: JAVA
TASK: milkorder
 */

import java.io.*;
import java.util.*;

public class milkorder {
	static int n, m; 
	static ArrayList<Integer>[] observations;
	static ArrayList<Integer>[] graph; 
	static int[] degreeIn; 
	static ArrayList<Integer> ts = new ArrayList<Integer>(); 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("milkorder.in")); StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken()); m = Integer.parseInt(input.nextToken()); 
		observations = new ArrayList[m]; 
		for(int i = 0; i < m; i++) observations[i] = new ArrayList<Integer>(); 
		for(int i = 0; i < m; i++) {
			input = new StringTokenizer(f.readLine());
			int num = Integer.parseInt(input.nextToken()); 
			for(int j = 0; j < num; j++) observations[i].add(Integer.parseInt(input.nextToken())-1); 
		}
		graph = new ArrayList[n]; 
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>(); 
		degreeIn = new int[n]; 
		f.close();
		
		//algorithm: binary search plus lexographic topological sort
		int l = 0; 
		int r = m+1; 
		while(l+1 < r) {
			int mid = (l+r)/2; 
			if(topo(mid)) {
				l = mid; 
			}
			else r = mid;  
		}
		topo(l); 
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out"))); 
		for(int i = 0; i < ts.size(); i++) {
			out.print((ts.get(i)+1));
			if(i != ts.size()-1) out.print(" ");
		}
		out.println();
		out.close();
	}
	
	public static boolean topo(int k) { //observations 0-k inclusive 
		ts.clear();
		for(int i = 0; i < n; i++) {
			graph[i].clear(); 
			degreeIn[i] = 0; 
		}
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < observations[i].size()-1; j++) {
				graph[observations[i].get(j)].add(observations[i].get(j+1));
				degreeIn[observations[i].get(j+1)]++; 
			}
		}
		//check for possible starting points degree 0 
		//pq to always go the smallest one 
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); 
		for(int i = 0; i < n; i++) if(degreeIn[i] == 0) pq.add(i);
		
		//running through the values storing the topo sort and checking to make sure no cycles appear.  we should be able to visit each node once once all vertices are reached 
		//if that isn't possible then 
		for(int i = 0; i < n; i++) {
			if(pq.isEmpty() == true) return false; 
			int cur = pq.remove();
			ts.add(cur); 
			for(int item : graph[cur]) {
				degreeIn[item]--; 
				if(degreeIn[item] == 0) pq.add(item); //only if all conditions are met then it can be visited next 
			}
			
		}
		return true; //no cycle 
	}

}
