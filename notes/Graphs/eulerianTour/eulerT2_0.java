package eulerianTour;
import java.util.*;

// !!!!!!!!! you must make sure that the graph is eulerian or else this algorithm breaks and this cycles it back to the start 
public class eulerT2_0 {
	static ArrayList<pair>[] graph = new ArrayList[6]; 
	static ArrayList<Integer> tour = new ArrayList<Integer>(); 
	public static void main(String args[]) {
		for(int i = 0; i < 6; i++) graph[i] = new ArrayList<pair>(); 
		graph[1].add(new pair(3)); 
		graph[3].add(new pair(1)); 
		graph[2].add(new pair(2)); 
		graph[2].add(new pair(4)); 
		graph[4].add(new pair(2)); 
		graph[3].add(new pair(4)); 
		graph[4].add(new pair(3)); 
		graph[3].add(new pair(5)); 
		graph[5].add(new pair(3)); 
		graph[3].add(new pair(4)); 
		graph[4].add(new pair(3)); 
		graph[4].add(new pair(5)); 
		graph[5].add(new pair(4)); 
		
		eulerTour(1, 0); 
		for(int item : tour) System.out.println(item);
		
	}
	
	public static void eulerTour(int cur, int idxinsert) {
		for(int j = 0; j < graph[cur].size(); j++) {
			pair v = graph[cur].get(j); 
			if(v.status == 1) {
				v.status = 0; 
				for(int k = 0; k < graph[v.destination].size(); k++) {
					pair uu = graph[v.destination].get(k); 
					if(uu.destination == cur && uu.status == 1) {
						uu.status = 0; 
						break; 
					}
				}
				tour.add(idxinsert, cur);
				eulerTour(v.destination, idxinsert+1); 
			}
		}
	}
	
	public static class pair {
		int status = 1; 
		int destination; 
		public pair(int a) {
			destination = a; 
		}
	}
}
