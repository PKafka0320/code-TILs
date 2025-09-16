import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Position implements Comparable<Position> {
		int index, time;

		public Position(int index, int time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Position o) {
			return this.time - o.time;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer, N, K, minTime[];
	static int INF = 100_000, MAX_POSITION = 100_000;
	static PriorityQueue<Position> pq;

	public static void main(String[] args) throws IOException {
		init();
		if (N >= K) {
			answer = N - K;
		} else {
			solve();
		}
		System.out.println(answer);
	}

	private static void solve() {
		minTime[N] = 0;
		pq.add(new Position(N, 0));

		while (!pq.isEmpty()) {
			Position currentPosition = pq.poll();
			int currentIndex = currentPosition.index;
			int currentTime = currentPosition.time;

			if (minTime[currentIndex] < currentTime) {
				continue;
			}

			int nextIndex = currentIndex * 2;
			if (isValid(nextIndex) && minTime[nextIndex] > currentTime) {
				minTime[nextIndex] = currentTime;
				pq.add(new Position(nextIndex, minTime[nextIndex]));
			}

			nextIndex = currentIndex - 1;
			if (isValid(nextIndex) && minTime[nextIndex] > currentTime + 1) {
				minTime[nextIndex] = currentTime + 1;
				pq.add(new Position(nextIndex, minTime[nextIndex]));
			}

			nextIndex = currentIndex + 1;
			if (isValid(nextIndex) && minTime[nextIndex] > currentTime + 1) {
				minTime[nextIndex] = currentTime + 1;
				pq.add(new Position(nextIndex, minTime[nextIndex]));
			}
		}
		answer = minTime[K];
	}

	private static boolean isValid(int index) {
		return 0 <= index && index <= MAX_POSITION;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		minTime = new int[MAX_POSITION + 1];
		pq = new PriorityQueue<>();

		Arrays.fill(minTime, INF);
	}
}
