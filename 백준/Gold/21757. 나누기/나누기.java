import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, sum;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		sum = new int[N + 1];
		dp = new long[N + 1][5]; // dp[i][j] = i 번째까지 j 번으로 나누는 경우의 수

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}

		if (sum[N] % 4 != 0) {
			System.out.println(0);
			return;
		}

		int value = sum[N] / 4;
		dp[0][0] = 1;
		for (int idx = 1; idx <= N; idx++) {
			dp[idx][0] = 1;

			for (int multi = 1; multi < 4; multi++) {
				dp[idx][multi] = dp[idx - 1][multi];

				if (value * multi == sum[idx]) {
					dp[idx][multi] += dp[idx - 1][multi - 1];
				}
			}
		}

		System.out.println(dp[N - 1][3]);
	}
}