package patternFinding;

public class rabin_karp {
	// d is the number of characters in the input alphabet --- used primes instead to avoid collisions 
	public final static long d = 1000081;

	static void search(String pat, String txt, long q) {
		int M = pat.length();
		int N = txt.length();
		int i, j;
		long p = 0; // hash value for pattern
		long t = 0; // hash value for txt
		long h = 1;

		// The value of h would be "pow(d, M-1)%q"
		for (i = 0; i < M - 1; i++)
			h = (h * d) % q;

		// Calculate the hash value of pattern and first
		// window of text
		for (i = 0; i < M; i++) {
			p = (d * p + (long)pat.charAt(i)) % q;
			t = (d * t + (long)txt.charAt(i)) % q;
		}

		for (i = 0; i <= N - M; i++) {

			if (p == t) {
				for (j = 0; j < M; j++) {
					if (txt.charAt(i + j) != pat.charAt(j)) {
						System.out.println("collision");
						break;
					}
				}
				if (j == M)
					System.out.println("Pattern found at index " + i);
			}

			// Calculate hash value for next window of text: Remove
			// leading digit, add trailing digit
			if (i < N - M) {
				t = (d * (t - (long)txt.charAt(i) * h) + (long)txt.charAt(i + M)) % q;
				if (t < 0)
					t = (t + q);
			}
		}
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		String txt = "EVWFAAPFGBPXFRONQONGDMIVRWYZGCSQLRDJGYXAYXSUKLCHVHBCGQCJFUCKBNOMRQCIVJSJGKHZTVRHYAOOECHXBEKFXRVZXUXK";
		String pat = "AXHD";
		long q = 1000016929; // A prime number
		search(pat, txt, q);
	}
}