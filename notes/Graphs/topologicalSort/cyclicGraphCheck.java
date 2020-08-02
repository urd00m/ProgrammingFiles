package topologicalSort;

import java.util.*;

public class cyclicGraphCheck {
	static ArrayList<Integer> ts = new ArrayList<Integer>(); 
	static int n; 
	static ArrayList<Integer>[] graph; 
	static int[] degreeIn; 
	static ArrayList<Integer>[] observations; 
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
