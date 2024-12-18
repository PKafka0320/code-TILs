import java.io.*;
import java.util.*;

public class Main {
	static int N, LEN, SIZE;
	static int tree[], numbers[];

	public static void makeTree() {
		for (int i = 0; i < N; i++) {
			tree[i + LEN] = numbers[i];
		}

		for (int i = SIZE - 1; i >= 2; i--) {
			tree[i / 2] = Math.min(tree[i / 2], tree[i]);
		}
	}

	public static int get(int idx, int s, int e, int ts, int te) {
		if (e < ts || s > te)
			return (int) 1e9;

		if (ts <= s && e <= te)
			return tree[idx];

		int mid = (s + e) / 2;

		return Math.min(get(idx * 2, s, mid, ts, te), get(idx * 2 + 1, mid + 1, e, ts, te));
	}

	public static void update(int idx, int value) {
		tree[idx] = value;

		while (idx >= 2) {
			if (idx % 2 == 0) {
				tree[idx / 2] = Math.min(tree[idx], tree[idx + 1]);
			} else {
				tree[idx / 2] = Math.min(tree[idx], tree[idx - 1]);
			}
			idx /= 2;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		double log = Math.log(N) / Math.log(2);
		int depth = log % 1 == 0 ? (int) log : (int) log + 1;
		LEN = 1 << depth;
		SIZE = 1 << depth + 1;

		numbers = new int[N];
		tree = new int[SIZE];

		Arrays.fill(tree, (int) 1e9);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		makeTree();

		StringBuilder answer = new StringBuilder();
		int QueryCount = Integer.parseInt(br.readLine());
		for (int q = 0; q < QueryCount; q++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());

			if (op == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				update(LEN + idx - 1, value);
			} else {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				answer.append(get(1, 1, LEN, from, to)).append("\n");
			}
		}

		System.out.println(answer.toString());
	}
}
