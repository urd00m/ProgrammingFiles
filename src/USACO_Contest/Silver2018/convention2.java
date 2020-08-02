package Silver2018;
/*
ID: alwang 
LANG: JAVA
TASK: convention2
 */
import java.io.*; 
import java.util.*;

public class convention2 {
	static int n; 
	static Queue<cow> cows;
	static Queue<cow> waiting; 
	static int maxWait = 0; 
	static int currentTime = 1; 
	public static void main(String args[]) throws IOException {
		//input 
		BufferedReader f = new BufferedReader(new FileReader("convention2.in"));
		n = Integer.parseInt(f.readLine());
		StringTokenizer in; 
		//sort based on arrival time 
		cows = new PriorityQueue<cow>(20, new Comparator<cow>() {  //queue bases on arrival
			@Override
			public int compare(cow a, cow b) {
				return a.timeArrive - b.timeArrive;
			}
		});  
		for(int i = 0; i < n; i++) {
			in = new StringTokenizer(f.readLine());
			cows.add(new cow(i, Integer.parseInt(in.nextToken()), Integer.parseInt(in.nextToken())));
		}
		f.close();
		
		//start algorithm 
		
		//creates priority queue for waiting 
		waiting = new PriorityQueue<cow>(20, new Comparator<cow>() {
			@Override
			public int compare(cow a, cow b) {
				return a.seniority - b.seniority; 
			}
		});
		//algorithm
		run();
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out"))); 
		out.println(maxWait);
		out.close();
		
	}
	
	public static void run() {
		//first cow
		cow a = cows.remove(); 
		currentTime = a.timeArrive + a.timeEat; 
		
		while(cows.size() != 0 || waiting.size() != 0) { //until all the cows are done eating
			if(cows.size() != 0) find(currentTime); 
			if(waiting.size() != 0) {
				a = waiting.remove(); 
				int wait = currentTime - a.timeArrive;
				if(wait > maxWait) maxWait = wait; 
				currentTime += a.timeEat; 
			}
			else {
				a = cows.remove(); 
				currentTime = a.timeArrive + a.timeEat; 
			}
		}
	}
	
	public static void find(int time ) {
		cow a = cows.peek(); 
		while(cows.size() != 0 && a.timeArrive <= time) {
			cows.remove();
			waiting.add(a); 
			a = cows.peek(); 
		}
	}
	
	static class cow {
		public int seniority; 
		public int timeArrive;
		public int timeEat;
		public cow(int a, int b, int c) {
			seniority = a;
			timeArrive = b;
			timeEat = c; 
		}
	}
}
