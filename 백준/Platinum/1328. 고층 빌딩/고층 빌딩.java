import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, R;
	static long[][][] dp;
	static int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		dp[1][1][1] = 1;
		dp[2][2][1] = dp[2][1][2] = 1;

		for (int n = 3; n <= N; n++) {
			for (int l = 1; l <= L; l++) {
				for (int r = 1; r <= R; r++) {
					dp[n][l][r] += dp[n - 1][l - 1][r] % MOD;
					dp[n][l][r] += dp[n - 1][l][r - 1] % MOD;
					dp[n][l][r] += (dp[n - 1][l][r] * (n - 2)) % MOD;
					dp[n][l][r] %= MOD;
				}
			}
		}

		System.out.println(dp[N][L][R]);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		dp = new long[101][101][101];
	}
}