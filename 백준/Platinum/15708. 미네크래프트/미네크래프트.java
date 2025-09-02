import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, T, P;
	static int[] rocks;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int answer = 0;

		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (P * i > T) {
				break;
			}

			pq.add(rocks[i]);
			sum += rocks[i];

			if (!pq.isEmpty() && sum + (P * i) > T) {
				sum -= pq.poll();
			}
			answer = Math.max(answer, pq.size());
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		rocks = new int[N];
		pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rocks[i] = Integer.parseInt(st.nextToken());
		}
	}
}
