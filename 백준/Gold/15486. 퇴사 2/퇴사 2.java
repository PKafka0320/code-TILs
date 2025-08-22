import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] T, P, dp;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		for (int i = 1; i < N + 1; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1]);

			int endDay = i + T[i] - 1;
			dp[endDay] = Math.max(dp[endDay], dp[i - 1] + P[i]);
		}

		System.out.println(dp[N]);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		T = new int[N + 1];
		P = new int[N + 1];
		dp = new int[N + 51];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
	}
}