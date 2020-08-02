
//package open2019;
/*
ID: urd00m
LANG: JAVA
TASK: balance
 */
import java.io.*;
import java.util.*;

public class balance {
	static int n;
	static int[] a;
	static long invR = 0, invL = 0;
	static int num1l, num0l, num0r, num1r;
	static ArrayList<Integer> lpositions = new ArrayList<Integer>();
	static ArrayList<Integer> rpositions = new ArrayList<Integer>();

	public static void main(String args[]) throws IOException {
		// input
		BufferedReader f = new BufferedReader(new FileReader("balance.in"));
		StringTokenizer input;
		n = Integer.parseInt(f.readLine());
		input = new StringTokenizer(f.readLine());
		a = new int[2 * n];
		int zero = 0;
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(input.nextToken());
			if (a[i] == 0) {
				zero++;
				num0l++;
			}
			if (a[i] == 1 || i == n - 1) {

				invL += zero * num1l;
				zero = 0;
				if (a[i] == 1) {
					lpositions.add(i);
					num1l++;
				}
			}
		}
		zero = 0;
		for (int i = n; i < 2 * n; i++) {
			a[i] = Integer.parseInt(input.nextToken());
			if (a[i] == 0) {
				zero++;
				num0r++;
			}
			if (a[i] == 1 || i == 2 * n - 1) {
				invR += zero * num1r;
				zero = 0;
				if (a[i] == 1) {
					rpositions.add(i);
					num1r++;
				}
			}
		}
		Collections.reverse(lpositions);
		//System.out.println(invL + " " + invR);
		// algorithm: count inversions and record total number of zeros and ones on both
		// sides
		long best = Long.MAX_VALUE;
		best = Math.min(best, Math.abs(invR - invL));
		// moving things from the left side to the right side
		// move to the n-1 posiiton, move things away from the n position
		int l1 = num1l, r0 = num0r;
		long newinvl = invL;
		long newinvr = invR;
		int swaps = 0;
		int i = n;
		for (int t = 0; t < Math.min(lpositions.size(), 20000); t++) { // try moving 1's over
			// move to n-1
			int cur = lpositions.get(t);
			newinvl -= (n - 1 - cur);
			swaps += (n - 1 - cur);

			// clear n

			while (i < 2 * n && a[i] == 1) {
				i++;
			}
			if (i == 2 * n)
				break;
			a[i] = 1;
			a[n] = 1;
			newinvr -= (i - n);
			swaps += i - n;

			// swap
			l1--;
			r0--;
			newinvl += l1;
			newinvr += r0;
			swaps++;

			best = Math.min(best, (swaps + (Math.abs(newinvl - newinvr))));
		}
		// shifting 1's from the left
		l1 = num1l;
		r0 = num0r;
		newinvl = invL;
		newinvr = invR;
		swaps = 0;
		i = n - 1;
		for (int t = 0; t < Math.min(rpositions.size(), 20000); t++) { // try moving 1's over

			// move to n
			int cur = rpositions.get(t);
			newinvr += (cur - n);
			swaps += (cur - n);
			// clear n-1
			while (i >= 0 && a[i] == 1) {
				i--;
			}
			if (i == -1)
				break;
			a[i] = 1;
			a[n - 1] = 1;
			newinvl += (n - 1 - i);
			swaps += (n - 1 - i);

			// swap
			newinvl -= l1;
			newinvr -= r0;
			swaps++;
			l1++;
			r0++;
			
			best = Math.min(best, (swaps + (Math.abs(newinvl - newinvr))));
		}
		// output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
		out.println(best);
		out.close();

	}
}
