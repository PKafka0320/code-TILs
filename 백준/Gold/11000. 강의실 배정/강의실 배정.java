import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Reserve implements Comparable<Reserve> {
		int start, end;

		public Reserve(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Reserve o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static PriorityQueue<Reserve> reserves, currentClasses;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int classCount = 0;
		int emptyClassCount = 0;

		while (!reserves.isEmpty()) {
			Reserve reserve = reserves.poll();
			int start = reserve.start;
			int end = reserve.end;

			while (!currentClasses.isEmpty() && currentClasses.peek().end <= start) {
				currentClasses.poll();
				emptyClassCount++;
			}

			if (emptyClassCount == 0) {
				emptyClassCount++;
				classCount++;
			}

			currentClasses.add(reserve);
			emptyClassCount--;
		}

		System.out.println(classCount);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		reserves = new PriorityQueue<>();
		currentClasses = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			reserves.add(new Reserve(start, end));
		}
	}
}
