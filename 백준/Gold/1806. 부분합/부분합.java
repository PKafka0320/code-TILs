import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int left = 0;
		int right = 0;
		int answer = N + 1;

		long sum = 0;
		while (left < N) {
			while (right < N && sum < S) {
				sum += numbers[right++];
			}

			if (sum >= S) {
				answer = Math.min(answer, right - left);
			}

			sum -= numbers[left++];
		}

		if (answer == N + 1) {
			answer = 0;
		}
		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
	}
}
