package Bronze2018;
/*
ID: alwang
LANG: JAVA
TASK: backforth
 */
import java.util.*;
import java.io.*;
public class backforth {
	static ArrayList<Integer> house1main = new ArrayList<Integer>();
	static ArrayList<Integer> house2main = new ArrayList<Integer>();
	static ArrayList<Integer> house1c = new ArrayList<Integer>();
	static ArrayList<Integer> house2c = new ArrayList<Integer>();
	static ArrayList<Integer> endValues = new ArrayList<Integer>();
	static ArrayList<Integer> house3c = new ArrayList<Integer>();
	static ArrayList<Integer> house4c = new ArrayList<Integer>();
	static ArrayList<Integer> house5c = new ArrayList<Integer>();
	static ArrayList<Integer> house6c = new ArrayList<Integer>();
	static ArrayList<Integer> house7c = new ArrayList<Integer>();
	static ArrayList<Integer> house8c = new ArrayList<Integer>();
	static ArrayList<Integer> house9c = new ArrayList<Integer>();
	static ArrayList<Integer> house10c = new ArrayList<Integer>();

	public static void main(String args[]) throws IOException {
		//input
		BufferedReader f = new BufferedReader(new FileReader("backforth.in"));
		StringTokenizer in = new StringTokenizer(f.readLine());
		StringTokenizer in2 = new StringTokenizer(f.readLine());
		for(int i = 0; i < 10; i++) {
			house1main.add(Integer.parseInt(in.nextToken()));
			house2main.add(Integer.parseInt(in2.nextToken()));
		}
		
		//algorithm
		//recurisve backtracking with copies 
		copy(house1main, house1c);
		copy(house2main, house2c); //resets 
		find(2, 1000); //starts on day 2 (tuesday) and house 1 has 1000 initially
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
		out.println(endValues.size() + "");
		out.close();
	}
	
	public static void find(int day, int house1) {
		if(day == 6) { //base case, after friday
			if(inArray(house1) == true) { //means not in list
				endValues.add(house1);
			}
		}
		else {
			if(day %2 == 0) { //move bucket from first house
				for(int i = 0; i < house1c.size(); i++) {
					int store = house1c.get(i); 
					house1c.remove(i); //move the bucket 
					house2c.add(store);
					if(day == 2) {
						copy(house1c, house3c);
						copy(house2c, house4c);
					}
					if(day == 4) {
						copy(house1c, house9c);
						copy(house2c, house10c);
					}
					find(day+1, house1-store); //2nd day and takes away the milk from house1 
					if(day == 2) {
						copy(house1main, house1c);
						copy(house2main, house2c);
					}
					if(day == 4) {
						copy( house5c, house1c);
						copy(house6c, house2c);
					}
				}
			}
			else { //move from second house
				for(int i = 0; i < house2c.size(); i++) {
					int store = house2c.get(i);
					house2c.remove(i); //move the bucket 
					house1c.add(store);
					if(day == 3) {
						copy(house1c, house5c);
						copy(house2c, house6c);
					}
					find(day+1, house1+store); //2nd day and takes away the milk from house2 into house1
					if(day == 3) {
						copy (house3c, house1c);
						copy(house4c, house2c);
					}
					if(day == 5) {
						copy(house9c, house1c);
						copy(house10c, house2c);
					}
				}
			}
		}
			
	}
	
	public static boolean inArray(int a) { //returns true if not in list 
		for(int i = 0; i < endValues.size(); i++) {
			if(endValues.get(i) == a) return false; 
		}
		return true; //flipped for ease  
	}
	public static void copy(ArrayList<Integer> a, ArrayList<Integer> b) { //copies over all elements of a to b 
		b.clear();
		for(int i = 0; i < a.size(); i++) {
			b.add(a.get(i));
		}
	}
}
