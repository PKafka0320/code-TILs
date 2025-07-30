import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	static int c, d, n;
	static int[] mod;
	static int[] modCount;

	public static void main(String[] args) throws IOException {
		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			init();
			solve();
		}
		System.out.println(answer);
	}

	private static void solve() {
		int count = 0;

		int modSum = 0;
		for (int i = 1; i <= n; i++) {
			modSum = (modSum + mod[i]) % d;
			count += modCount[modSum];
			modCount[modSum]++;
		}
		count += modCount[0];

		answer.append(count).append("\n");
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		mod = new int[n + 1];
		modCount = new int[d];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			mod[i] = Integer.parseInt(st.nextToken()) % d;
		}
	}
}