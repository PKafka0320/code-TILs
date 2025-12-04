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
		int left = 0;
		int right = N - 1;

		long result = 2_000_000_000;
		int minLeft = -1;
		int minRight = -1;
		long sum;
		while (left < right) {
			sum = numbers[left] + numbers[right];

			if (Math.abs(sum) < Math.abs(result)) {
				result = sum;
				minLeft = left;
				minRight = right;
			}

			if (sum > 0) {
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				break;
			}
		}
		System.out.printf("%d %d", numbers[minLeft], numbers[minRight]);
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
