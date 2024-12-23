import java.io.*;
import java.util.*;

public class Main {
	static class Flower {
		int start, end;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Flower[] flowers = new Flower[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());

			flowers[i] = new Flower(convert(startMonth, startDay), convert(endMonth, endDay));
		}

		Arrays.sort(flowers, new Comparator<Flower>() {
			@Override
			public int compare(Flower o1, Flower o2) {
				if (o1.start == o2.start) return o2.end - o1.end;
				return o1.start - o2.start;
			}
		});

		Queue<Flower> pq = new PriorityQueue<>(new Comparator<Flower>() {
			@Override
			public int compare(Flower o1, Flower o2) {
				return o2.end - o1.end;
			}
		});
		
		int current = 301;
		int target = 1130;
		int index = 0;
		int answer = 0;
		while (current <= target) {
			while (index < N && flowers[index].start <= current) {
				pq.add(flowers[index++]);
			}

			if (pq.isEmpty()) {
				break;
			}

			Flower selectFlower = pq.poll();
			answer++;
			current = selectFlower.end;
		}
		
		System.out.println(current > 1130 ? answer : 0);
	}

	public static int convert(int month, int day) {
		return month * 100 + day;
	}
}
