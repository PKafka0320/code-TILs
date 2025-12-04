import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static long[] A, B, C, D, AB, CD;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		long result = 0;

		int left = 0;
		int right = N * N - 1;
		while (left < N * N && right >= 0) {
			if (AB[left] + CD[right] < 0) {
				left++;
			} else if (AB[left] + CD[right] > 0) {
				right--;
			} else {
				long leftCount = 1; long rightCount = 1;
				while (left + 1 < N * N && AB[left] == AB[left + 1]) {
					leftCount++;
					left++;
				}
				while (right - 1 >= 0 && CD[right] == CD[right - 1]) {
					rightCount++;
					right--;
				}

				result += leftCount * rightCount;
				left++;
			}
		}
		System.out.println(result);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];
		AB = new long[N * N];
		CD = new long[N * N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[i * N + j] = A[i] + B[j];
				CD[i * N + j] = C[i] + D[j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
	}
}
