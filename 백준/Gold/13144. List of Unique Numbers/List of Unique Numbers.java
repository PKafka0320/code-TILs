import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int[] counts = new int[100_001];
		long answer = 0;
		int left = 0;
		int right = 0;

		while (left < N) {
			while (right < N && counts[numbers[right]] + 1 <= 1) {
				counts[numbers[right]]++;
				right++;
			}
			answer += right - left;
			counts[numbers[left]]--;
			left++;
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
	}
}
