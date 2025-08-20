import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Integer> inputList;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		StringBuilder answer = new StringBuilder();

		for (int i = 1; i <= 30; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j < 30; j++) {
				if (j == 0) { // 완전한 알약 하나를 먹는 상황
					dp[i][j] = dp[i - 1][j + 1];
				} else { // 반절짜리 약을 하나를 먹는 상황 + 완전한 알약 하나를 먹는 상황
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j + 1];
				}
			}
		}

		for (Integer n : inputList) {
			answer.append(dp[n][0]).append("\n");
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		inputList = new ArrayList<>();
		int input = Integer.parseInt(br.readLine());
		while (input != 0) {
			inputList.add(input);
			input = Integer.parseInt(br.readLine());
		}

		dp = new long[31][31];
	}
}