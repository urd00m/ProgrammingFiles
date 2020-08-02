package topologicalSort;
import java.util.*;
public class topo {
	static ArrayList<Integer> ts = new ArrayList<Integer>();  
	static boolean[] dfs_num; 
	static ArrayList<Integer>[] graph; 
	public static void main(String args[]) {
		graph = new ArrayList[5]; 
		for(int i = 0; i < 5; i++) graph[i] = new ArrayList<Integer>();
		dfs_num = new boolean[5]; 
		graph[2].add(3); 
		graph[1].add(2); 
		graph[4].add(0); 
		graph[4].add(2); 
		
		
		
		for(int i = 4; i >= 0; i--) { //topological sort for DAG but doesn't work for graphs with cycles you need a separate thing for that 
			if(dfs_num[i] == false) {
				dfs2(i); 
			}
		}
		Collections.reverse(ts);
		for(int item : ts) System.out.println((item));
		
 	}
	public static void dfs2(int u) {
		dfs_num[u] = true; 
		for(int item : graph[u]) {
			 if(dfs_num[item] == false) dfs2(item); 
		}
		ts.add(u); 
	}
}
