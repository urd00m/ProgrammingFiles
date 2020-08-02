
//package open2019;
/*
ID: alwang
LANG: JAVA
TASK: walk
 */

import java.io.*;
import java.util.*;

public class walk {
	static int a = 2019201913, b = 2019201949, mod = 2019201997;
	static int[] taken;
	static int n, k;
	static Long[] mst_cost;
	static int next;

	public static void main(String args[]) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("walk.in"));
		StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken());
		k = Integer.parseInt(input.nextToken());
		mst_cost = new Long[n + 1];
		Arrays.fill(mst_cost, 10000000000L);
		prim(n);
		Arrays.sort(mst_cost, new Comparator<Long>() {
			@Override
			public int compare(Long a, Long b) { // decreasing order
				return (int) (b - a);
			}
		});
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		if (k == 1)
			out.println("0");
		else
			out.println(mst_cost[k - 2]);
		out.close();
	}

	static int count = 0;

	public static void prim(int n) {
		taken = new int[n + 1];
		taken[0] = 1;
		mst_cost[0] = (long) 0;
		mst_cost[1] = (long) 0;
		process(1);
		while (count != n - 1) {
			process(next);
		}
	}

	public static void process(int vtx) {
		count++;
		taken[vtx] = 1;
		next = -1;
		for (int j = 1; j < n + 1; j++) {
			if (taken[j] == 0) {
				mst_cost[j] = Math.min(mst_cost[j], (long) (((long) a * Math.min(vtx, j)) % mod + ((long) b * Math.max(vtx, j)) % mod) % mod);
				if (next == -1 || mst_cost[j] < mst_cost[next])
					next = j;
			}
		}
	}

}
