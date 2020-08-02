//package a120;
/*
ID: urd00m
LANG: JAVA
TASK: uva12047
 */
import java.io.*;
import java.util.*;

public class Main {
	static int T; 
	static int n, m, s, t, p; 
	static pair[] edgeList; 
	static ArrayList<pair>[] graph, graph2; 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new java.io.InputStreamReader(System.in)); StringTokenizer input; 
		T = Integer.parseInt(f.readLine()); 
		for(int caseNumber = 1; caseNumber < T+1; caseNumber++) {
			input = new StringTokenizer(f.readLine()); 
			n = Integer.parseInt(input.nextToken()); 
			m = Integer.parseInt(input.nextToken()); 
			s = Integer.parseInt(input.nextToken())-1; 
			t = Integer.parseInt(input.nextToken())-1; 
			p = Integer.parseInt(input.nextToken()); 
			edgeList = new pair[m]; 
			graph = new ArrayList[n]; graph2 = new ArrayList[n]; 
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<pair>(); 
				graph2[i] = new ArrayList<pair>(); 
			}
			for(int i = 0; i < m; i++) {
				input = new StringTokenizer(f.readLine()); 
				int a = Integer.parseInt(input.nextToken())-1; int b = Integer.parseInt(input.nextToken())-1; int c = Integer.parseInt(input.nextToken()); 
				edgeList[i] = new pair(a, b, c); 
				graph[a].add(new pair(b, c)); 
				graph2[b].add(new pair(a, c)); 
			}
			
			//run dijistrikas 
			int[] dist = dpq(graph, s, n);
			int[] dist2 = dpq(graph2, t, n);
			int max = -1; 
			for(pair item : edgeList) {
				if(dist[item.from] != -1 && dist2[item.destination] != -1  && dist[item.from] + item.weight + dist2[item.destination] <= p) {
					max = Math.max(item.weight, max); 
				}
			}
			
			 
			//ouptut
			System.out.println(max); 
		}
	}
	public static int[] dpq(ArrayList<pair>[] a, int src, int n) {
		int[] dist = new int[n];  
		Arrays.fill(dist, -1);
		boolean[] visited = new boolean[n]; 
		PriorityQueue<pair> next = new PriorityQueue<pair>(n, new pair()); 
		next.add(new pair(src, 0)); 
		dist[src] = 0; 
		while(next.isEmpty() == false) {
			pair curNode = next.remove(); 
			visited[curNode.destination] = true;
			for(pair store : a[curNode.destination]) {
				if(visited[store.destination] == false && (dist[store.destination] == -1 || dist[store.destination] > dist[curNode.destination]+store.weight)) {
					dist[store.destination] = dist[curNode.destination]+store.weight; 
					next.add(new pair(store.destination, dist[store.destination]));  
				}
			}
		}
		return dist; 
	}
	public static class pair implements Comparator<pair> {
		int from; 
		int destination; 
		int weight; 
		public pair() {
		}
		public pair(int a, int b) {
			destination = a; 
			weight = b; 
		}
		public pair(int a, int b, int c) {
			from = a; 
			destination = b; 
			weight = c; 
		}
		@Override
		public int compare(pair o1, pair o2) {
			return o1.weight - o2.weight;
		}
	}
}
