package Bronze2018;
/*
ID: alwang
LANG: JAVA
TASK: mixmilk
 */
import java.io.*;
import java.util.*;
public class mixmilk {
	static int[] buckets = new int[3];
	static int[] carry = new int[3];
	public static void main(String args[]) throws IOException{
		//input
		BufferedReader f = new BufferedReader(new FileReader("mixmilk.in"));
		StringTokenizer in; 
		for(int i = 0; i < 3; i++) {
			in = new StringTokenizer(f.readLine());
			carry[i] = Integer.parseInt(in.nextToken());
			buckets[i] = Integer.parseInt(in.nextToken()); 
		}
		
		//algorithm
		//brute force 
		int count = 0; //which bucket to which       1 means bucket 1 to 2 
		for(int i = 0; i < 100; i++) { //100 cycles 
			if( (buckets[count%3] + buckets[(count+1)%3]) >= carry[(count+1)%3]) { //if can fill the bucket 
				buckets[count%3] = (buckets[count%3] + buckets[(count+1)%3]) - carry[(count+1)%3];
				buckets[(count+1)%3] = carry[(count+1)%3];
			}
			else { //can't fill the bucket 
				buckets[(count+1)%3] = buckets[count%3] + buckets[(count+1)%3];
				buckets[count%3] = 0;
			}
			count++;
		}
		
		//output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		for(int i = 0; i < 3; i++) {
			out.println(buckets[i]);
		}
		out.close();
	}
}
