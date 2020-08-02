package shortest_path_from_all_nodes_to_all_nodes;
import java.io.*;
import java.util.*;

public class transitiveClosure {
	public static void main(String args[]) throws IOException {
		//data structure used
		int v = 5; 
		int[][] graph = new int[v][v]; 
		graph[1][2] = 1; 
		graph[3][4] = 1; 
		graph[3][1] = 1; 
		
		
		//transitive closure algorithm 
		for(int k = 0; k < v; k++) {
			for(int i = 0; i < v; i++) {
				for(int j = 0; j < v; j++) {
					graph[i][j] |= (graph[i][k] & graph[k][j]);
				}
			}
		}
		
		
	}
}
