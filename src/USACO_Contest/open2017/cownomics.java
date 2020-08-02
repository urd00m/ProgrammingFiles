
//package open2017;
/*
ID: urd00m
LANG: JAVA
TASK: cownomics 
 */
import java.util.*;
import java.io.*;

public class cownomics {
	public final static long d = 1000081;
	public static int n, m;
	public static String[] spotty, plain;

	public static void main(String args[]) throws IOException {
		// input
		BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer input = new StringTokenizer(f.readLine());
		n = Integer.parseInt(input.nextToken());
		m = Integer.parseInt(input.nextToken());
		spotty = new String[n];
		plain = new String[n];
		for (int i = 0; i < n; i++) {
			spotty[i] = f.readLine();
		}
		for (int i = 0; i < n; i++) {
			plain[i] = f.readLine();
		}
		f.close();

		// algorithm: binary search + rabin-karp
		int l = 0;
		int r = m + 1;
		while (l + 1 < r) {
			int mid = (l + r) / 2; // this is size
			if (explainsSpot(mid) == true)
				r = mid;
			else
				l = mid;
		}

		// output
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		out.println(r);
		out.close();
	}

	public static boolean explainsSpot(int size) {
		long q = 1000016929;
		int M = size;
		int N = size;
		int i, j;
		long t = 0; // hash value for txt
		long h = 1;
		ArrayList<Long> plainH = new ArrayList<Long>(); // hash values for the cows
		ArrayList<Long> spottyH = new ArrayList<Long>();

		// The value of h would be "pow(d, M-1)%q"
		for (i = 0; i < size - 1; i++)
			h = (h * d) % q;

		// Calculate the hash value of pattern and first
		// window of text
		for (i = 0; i < n; i++) {
			long p = 0, s = 0;
			for (j = 0; j < size; j++) {
				p = (d * p + (long) plain[i].charAt(j)) % q;
				s = (d * s + (long) spotty[i].charAt(j)) % q;
			}
			plainH.add(p);
			spottyH.add(s);
		}

		for (i = 0; i <= m - size; i++) {

			for (j = 0; j < n; j++) {
				long cur = plainH.get(j);
				if (spottyH.contains(cur))
					break;
			}
			if (j == n)
				return true;

			// Calculate hash value for next window of text: Remove
			// leading digit, add trailing digit
			if (i < m - size) {
				for (j = 0; j < n; j++) {
					t = (d * (spottyH.get(j) - (long) spotty[j].charAt(i) * h) + (long) spotty[j].charAt(i + M)) % q;
					if (t < 0)
						t = (t + q);
					spottyH.set(j, t);
					t = (d * (plainH.get(j) - (long) plain[j].charAt(i) * h) + (long) plain[j].charAt(i + M)) % q;
					if (t < 0)
						t = (t + q);
					plainH.set(j, t);
				}
			}
		}
		return false;
	}
}
