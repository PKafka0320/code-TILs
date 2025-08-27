import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pair implements Comparable<Pair> {
		long value;
		int index;

		public Pair(long value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(Pair o) {
			return Long.compare(o.value, this.value);
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, D;
	static long[] values;
	static PriorityQueue<Pair> pq;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		long currentValue = values[0];
		long answer = currentValue;
		Pair temp;
		pq.add(new Pair(currentValue, 0));

		for (int i = 1; i < N; i++) {
			currentValue = values[i];

			// 거리 조건 확인
			while (!pq.isEmpty() && pq.peek().index < i - D) {
				pq.poll();
			}

			// 새롭게 징검다리를 밟는것과 이전 징검다리를 밟는것을 비교
			pq.add(new Pair(Math.max(pq.peek().value + currentValue, currentValue), i));

			// 최댓값 갱신
			if (!pq.isEmpty() && answer < pq.peek().value) {
				answer = pq.peek().value;
			}
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		values = new long[N];
		pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			values[i] = Long.parseLong(st.nextToken());
		}
	}
}