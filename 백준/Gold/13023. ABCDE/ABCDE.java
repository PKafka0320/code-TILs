import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int N, M, result;
	static ArrayList<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		boolean[] visited = new boolean[N + 1];
		result = 0;

		for (int i = 0; i < N; i++) {
			check(i, visited, 1);
		}

		System.out.println(result);
	}

	private static void check(int node, boolean[] visited, int count) {
		if (result == 1) {
			return;
		}
		if (count == 5) {
			result = 1;
			return;
		}

		visited[node] = true;

		for (int next : nodes[node]) {
			if (visited[next]) continue;

			check(next, visited, count + 1);
		}

		visited[node] = false;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			nodes[node1].add(node2);
			nodes[node2].add(node1);
		}
	}
}
