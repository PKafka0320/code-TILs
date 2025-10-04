import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int T, N, M;
	static int[] inDegree, rank;
	static ArrayList<Integer> result;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue;

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
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			if (queue.size() > 1) {
				answer.append("?");
				return;
			}

			int node = queue.poll();
			// System.out.printf("current node: %d\n", node);
			result.add(node);
			for (Integer next : graph[node]) {
				inDegree[next]--;
				// System.out.printf("indegree[%d]: %d\n", next, inDegree[next]);
				if (inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		if (result.size() != N) {
			answer.append("IMPOSSIBLE");
		} else {
			for (Integer node : result) {
				answer.append(node).append(" ");
			}
		}
		answer.append("\n");
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		rank = new int[N + 1];
		inDegree = new int[N + 1];
		graph = new ArrayList[N + 1];
		queue = new LinkedList<>();
		result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			rank[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				graph[rank[i]].add(rank[j]);
				inDegree[rank[j]]++;
			}
		}

		M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (graph[a].contains(b)) {
				graph[a].remove((Integer)b);
				graph[b].add(a);
				inDegree[a]++;
				inDegree[b]--;
			} else if (graph[b].contains(a)) {
				graph[b].remove((Integer)a);
				graph[a].add(b);
				inDegree[b]++;
				inDegree[a]--;
			}
		}
	}
}
