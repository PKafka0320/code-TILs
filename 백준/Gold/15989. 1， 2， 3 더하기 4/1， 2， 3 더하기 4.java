import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T;
	static int[] caseInput;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		StringBuilder answer = new StringBuilder();
		Arrays.fill(dp, 1);

		for (int i = 2; i < 10_001; i++) {
			dp[i] += dp[i - 2];
		}
		for (int i = 3; i < 10_001; i++) {
			dp[i] += dp[i - 3];
		}

		for (int n : caseInput) {
			answer.append(dp[n]).append("\n");
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		T = Integer.parseInt(br.readLine());
		caseInput = new int[T];
		for (int i = 0; i < T; i++) {
			caseInput[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[10_001];
	}
}