//package open2020;
/*
ID: alwang
LANG: JAVA
TASK: fcolor
 */
import java.util.*;
import java.io.*;

public class fcolor {
	static int n, m; 
	static ArrayList<Integer>[] graph; 
	static int[] degreeOut; 
	static Queue<Integer> init = new LinkedList<Integer>(); 
	static HashMap<Integer, Boolean>[] connected; 
	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("fcolor.in")); StringTokenizer input = new StringTokenizer(f.readLine()); 
		n = Integer.parseInt(input.nextToken()); m = Integer.parseInt(input.nextToken());
		graph = new ArrayList[n]; degreeOut = new int[n]; 
		connected = new HashMap[n]; 
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
			connected[i] = new HashMap<Integer, Boolean>();
		}
		for(int i = 0; i < m; i++) {
			input = new StringTokenizer(f.readLine()); 
			int a = Integer.parseInt(input.nextToken())-1; int b = Integer.parseInt(input.nextToken())-1;
			graph[a].add(b);
			connected[a].put(b, true); 
			degreeOut[a]++; 
			if(degreeOut[a] == 2) init.add(a); 
		}
		f.close();
		
		//algorithm: clumping with UFDS
		UnionFind clumps = new UnionFind(n); 
		Queue<Integer> next = new LinkedList<Integer>();
		boolean[] visited = new boolean[n]; //chain ID visited 
		while(init.isEmpty() == false) {
			//clumping together groups 
			int cur = init.remove(); 
			int chainID = graph[cur].get(0);
			int i = 1; 
			//while(i < graph[cur].size() && (clumps.isSameSet(chainID, cur) == true && chainID != cur)) { chainID = graph[cur].get(i); i++; }
			for(; i < graph[cur].size(); i++) {
				 clumps.unionSet(chainID, graph[cur].get(i)); //if(clumps.isSameSet(cur, graph[cur].get(i)) == false)
			}
			if(graph[clumps.findSet(chainID)].size() >= 2) next.add(clumps.findSet(chainID)); 
		}
		
		while(next.isEmpty() == false) {
			int curId = clumps.findSet(next.remove());
			if(visited[clumps.findSet(curId)] == true) continue; 
			else {
				visited[clumps.findSet(curId)] = true; //merging  
				int chainID = graph[clumps.findSet(curId)].get(0); 
				int i = 1; 
				//while(i < graph[curId].size() && (clumps.isSameSet(chainID, curId) == true && chainID != curId)) { chainID = graph[curId].get(i); i++; }
				for(; i < graph[clumps.findSet(curId)].size(); i++) {
					 clumps.unionSet(chainID, graph[clumps.findSet(curId)].get(i)); //if(clumps.isSameSet(curId, graph[clumps.findSet(curId)].get(i)) == false)
				}
				
				if(graph[clumps.findSet(chainID)].size() >= 2) next.add(clumps.findSet(chainID)); 
			}
		}
		
		//output 
		HashMap<Integer, Integer> convert = new HashMap<Integer, Integer>(); 
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")));
		int cur = 1; 
		for(int i = 0; i < n; i++) {
			int id = clumps.findSet(i); 
			if(convert.containsKey(id) == false) convert.put(id, cur++); 
			out.println(convert.get(id));
		}
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
				if(rank[x] >= rank[y]) {
					p[y] = x;
					size[x]+=size[y]; 
					for(int item : graph[y]) { //moving assets over 
						if(connected[x].containsKey(item) == false) {
							connected[x].put(item, true); 
							graph[x].add(item); 
						}
					}
				}
				else {
					p[x] = y;
					size[y] += size[x]; 
					if(rank[x] == rank[y]) rank[y]++; 
					for(int item : graph[x]) {
						if(connected[y].containsKey(item) == false) {
							connected[y].put(item, true); 
							graph[y].add(item); 
						}
					}
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
