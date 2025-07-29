import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int[][][] map;
	static int[][][] prefixSum;

	public static void main(String[] args) throws IOException {
		init();
		getPrefixSum();
		solve();
	}

	private static void solve() throws IOException {
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 3; j++) {
				answer.append(
						prefixSum[c][d][j] - prefixSum[c][b - 1][j] - prefixSum[a - 1][d][j] + prefixSum[a - 1][b - 1][j])
					.append(" ");
			}
			answer.append("\n");
		}

		System.out.println(answer);
	}

	private static void getPrefixSum() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 0; k < 3; k++) {
					prefixSum[i][j][k] =
						map[i][j][k] - prefixSum[i - 1][j - 1][k] + prefixSum[i - 1][j][k] + prefixSum[i][j - 1][k];
				}
			}
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		map = new int[N + 1][M + 1][3];
		prefixSum = new int[N + 1][M + 1][3];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == 'J') {
					map[i + 1][j + 1][0] = 1;
				} else if (line.charAt(j) == 'O') {
					map[i + 1][j + 1][1] = 1;
				} else {
					map[i + 1][j + 1][2] = 1;
				}
			}
		}
	}
}