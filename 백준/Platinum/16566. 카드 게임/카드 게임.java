import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int[] cards, expects;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < K; i++) {
			int expect = expects[i];

			int start = 0;
			int end = M - 1;

			while (start < end) {
				int mid = (start + end) / 2;

				if (cards[mid] <= expect) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}

			while (used[end]) {
				end++;
			}

			used[end] = true;
			answer.append(cards[end]).append("\n");
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		cards = new int[M];
		expects = new int[K];
		used = new boolean[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);

		expects = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}