package open2018;

import java.io.*;
import java.util.*;

public class test {
	static int[] a;
	static int n;

	public static void main(String args[]) throws IOException {
		PriorityQueue<Integer> test = new PriorityQueue<Integer>(); 
		test.add(1); 
		test.add(0); 
		System.out.println(test.poll());
	}

	public static void swap(int b, int c) {
		int temp = a[c];
		a[c] = a[b];
		a[b] = temp;
	}
}
