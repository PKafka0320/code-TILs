import java.io.*;
import java.util.*;

public class Main {
	static int N, LEN, SIZE;
	static long numbers[], tree[];

	public static void update(int idx, int currentStart, int currentEnd, int queryStart, int queryEnd, int value) {
		if (currentEnd < queryStart || currentStart > queryEnd)
			return;

		if (queryStart <= currentStart && currentEnd <= queryEnd) {
			tree[idx] += value;
			return;
		}

		int mid = (currentStart + currentEnd) / 2;

		update(idx * 2, currentStart, mid, queryStart, queryEnd, value);
		update(idx * 2 + 1, mid + 1, currentEnd, queryStart, queryEnd, value);
	}

	public static long get(int position) {
		int idx = 1;
		int left = 1;
		int right = LEN;

		while (idx * 2 < SIZE) {
			int mid = (left + right) / 2;
			long stack = tree[idx];

			tree[idx * 2 + 1] += stack;
			tree[idx * 2] += stack;
			tree[idx] = 0;

			if (left <= position && position <= mid) {
				right = mid;
				idx = idx * 2;
			} else if (mid + 1 <= position && position <= right) {
				left = mid + 1;
				idx = idx * 2 + 1;
			}
		}
		
		numbers[position - 1] += tree[idx];
		tree[idx] = 0;

		return numbers[position - 1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		double log = Math.log(N) / Math.log(2);
		int depth = log % 1 == 0 ? (int) log : (int) log + 1;
		LEN = 1 << depth;
		SIZE = 1 << depth + 1;

		numbers = new long[N];
		tree = new long[SIZE];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(st.nextToken());
		}

		StringBuilder answer = new StringBuilder();
		int queryCount = Integer.parseInt(br.readLine());
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());

			if (op == 1) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				update(1, 1, LEN, start, end, value);
			} else {
				int position = Integer.parseInt(st.nextToken());

				answer.append(get(position)).append("\n");
			}
		}

		System.out.println(answer.toString());
	}
}
