import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int T, K;
	static PriorityQueue<Long> pq;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		answer = new StringBuilder();
		while (T-- > 0) {
			init();
			solve();
		}
		System.out.println(answer);
	}

	private static void solve() {
		long result = 0;
		while (!pq.isEmpty()) {
			if (pq.size() == 1) {
				break;
			}

			long size1 = pq.poll();
			long size2 = pq.poll();
			long newSize = size1 + size2;
			result += newSize;
			pq.add(newSize);
		}
		answer.append(result).append("\n");
	}

	private static void init() throws IOException {
		K = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		while (K-- > 0) {
			long size = Long.parseLong(st.nextToken());
			pq.add(size);
		}
	}
}
