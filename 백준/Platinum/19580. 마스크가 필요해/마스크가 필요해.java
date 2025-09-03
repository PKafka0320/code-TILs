import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Civil implements Comparable<Civil> {
		long min, max;

		public Civil(long min, long max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Civil o) {
			if (this.max == o.max) {
				return Long.compare(o.min, this.min);
			}
			return Long.compare(o.max, this.max);
		}
	}

	static class Store implements Comparable<Store> {
		long price;
		int count;

		public Store(long price, int count) {
			this.price = price;
			this.count = count;
		}

		@Override
		public int compareTo(Store o) {
			return Long.compare(o.price, this.price);
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static PriorityQueue<Civil> civils;
	static PriorityQueue<Civil> currentCivils;
	static PriorityQueue<Store> stores;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int answer = 0;

		while (!stores.isEmpty()) {
			Store store = stores.poll();
			long price = store.price;
			int count = store.count;
			// System.out.printf("현재 상점 | 가격: %d, 수량: %d\n", price, count);

			while (!civils.isEmpty() && civils.peek().max >= price) {
				Civil civil = civils.poll();
				currentCivils.add(civil);
				// System.out.printf("시민(%d,%d)이 구매할 수 있음\n", civil.min, civil.max);
			}

			while (!currentCivils.isEmpty() && currentCivils.peek().min > price) {
				Civil civil = currentCivils.poll();
				// System.out.printf("시민(%d,%d)이 구매할 수 없음\n", civil.min, civil.max);
			}

			while (!currentCivils.isEmpty() && count > 0) {
				Civil civil = currentCivils.poll();
				count--;
				answer++;
				// System.out.printf("시민(%d,%d)이 구매함\n", civil.min, civil.max);
			}
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		civils = new PriorityQueue<>();
		currentCivils = new PriorityQueue<>((a, b) -> Long.compare(b.min, a.min));
		stores = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long min = Long.parseLong(st.nextToken());
			long max = Long.parseLong(st.nextToken());

			civils.add(new Civil(min, max));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long price = Long.parseLong(st.nextToken());
			int count = Integer.parseInt(st.nextToken());

			stores.add(new Store(price, count));
		}
	}
}
