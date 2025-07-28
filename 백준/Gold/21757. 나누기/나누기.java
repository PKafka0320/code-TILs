import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, sum;
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		sum = new int[N + 1];
		dp = new long[5];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}

		// 누적합이 4의 배수가 아니라면 불가능
		if (sum[N] % 4 != 0) {
			System.out.println(0);
			return;
		}

		long answer = 0;

		if (sum[N] == 0) { // 누적합이 0이라면 수열의 끝을 제외한 누적합이 0인 모든 지점 중 3개를 조합
			long zeroCount = 0;
			for (int i = 1; i <= N; i++) {
				if (sum[i] == 0)
					zeroCount++;
			}

			answer = (zeroCount - 1) * (zeroCount - 2) * (zeroCount - 3) / 6;
		} else { // 누적합이 4의 배수라면 나와야 할 값은 누적합을 4로 나눈 수
			dp[0] = 1;
			int value = sum[N] / 4;

			for (int i = 1; i <= N; i++) {
				int type = sum[i] / value;

				// value의 배수(1 ~ 4)가 아니라면 불가능한 구간
				if (sum[i] % value != 0 || type < 1 || type > 4) {
					continue;
				}

				dp[type] += dp[type - 1];
			}

			answer = dp[4];
		}

		System.out.println(answer);
	}
}