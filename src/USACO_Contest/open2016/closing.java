//package open2016;
/*
ID: alwang
LANG: JAVA
TASK: closing
 */
import java.util.*;
import java.io.*;

public class closing {
	static int n, m; 
	static ArrayList<Integer>[] graph;
	static int[] barns; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("closing.in")); StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken()); m = Integer.parseInt(input.nextToken());
		graph = new ArrayList[n];
		barns = new int[n]; 
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>(); 
		for(int i = 0; i < m; i++) {
			input = new StringTokenizer(f.readLine()); 
			int a = Integer.parseInt(input.nextToken())-1; int b = Integer.parseInt(input.nextToken())-1; 
			graph[a].add(b); 
			graph[b].add(a); 
		}
		for(int i = 0; i < n; i++) {
			barns[i] = Integer.parseInt(f.readLine())-1; 
		}
		f.close();
		
		//algorithm
		//reverse UFDS
		String[] output = new String[n]; 
		UnionFind ds = new UnionFind(n); 
		boolean[] inSet = new boolean[n]; 
		int numDS = n; 
		for(int i = n-1; i >= 0; i--) {
			int cur = barns[i];
			inSet[cur] = true; 
			for(int item : graph[cur]) {
				if(inSet[item] == true) {
					ds.unionSet(cur, item);
				}
			}
			if(ds.numSets == numDS) output[i] = "YES";
			else output[i] = "NO"; 
			numDS--; 
		}
		
		//output 
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out"))); 
		for(String item : output) out.println(item);
		out.close(); 
	}
	public static class UnionFind { 
		public int[] p, rank, size; 
		public int numSets; 
		public UnionFind(int n) {
			p = new int[n]; rank = new int[n]; numSets = n; size = new int[n]; 
			for(int i = 0; i < n; i++) {
				p[i] = i;
				size[i] = 1; 
			}
		}
		public int findSet(int i) { //finds parent
			return (p[i] == i) ? i : (p[i]= findSet(p[i])); 
		}
		public boolean isSameSet(int i, int j) {		//checks if they are the same set
			return (findSet(i) == findSet(j)); 
		}
		public void unionSet(int i, int j) {		//merges 2 sets
			if(!isSameSet(i, j)) {
				int x = findSet(i); int y = findSet(j); 
				if(rank[x] > rank[y]) {
					p[y] = x;
					size[x]+=size[y]; 
					
				}
				else {
					p[x] = y;
					size[y] += size[x]; 
					if(rank[x] == rank[y]) rank[y]++; 
				}
				numSets--; 
			}
		}
		public int sizeOfSet(int i) { 		//returns the size of the set
			return size[findSet(i)]; 
		}
		public int numDisjointSets() {		//returns the total number of sets 
			return numSets; 
		}
	}
}
