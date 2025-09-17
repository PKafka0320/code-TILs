import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int T, n, m, t, s, g, h;
	static int[] candidates, minCosts;
	static boolean[] results;
	static final int MAX_COST = 100_000_000;
	static ArrayList<Edge>[] graph;
	static PriorityQueue<Path> pq;

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static class Path {
		int node, cost;
		boolean check;

		public Path(int node, int cost, boolean check) {
			this.node = node;
			this.cost = cost;
			this.check = check;
		}
	}

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
		minCosts[s] = 0;
		pq.add(new Path(s, 0, false));

		while (!pq.isEmpty()) {
			Path path = pq.poll();
			int currentNode = path.node;
			int currentCost = path.cost;
			boolean isChecked = path.check;

			if (minCosts[currentNode] != currentCost) {
				continue;
			}
			// System.out.printf("from %d, cost %d, checked %b\n", currentNode, currentCost, isChecked);

			for (Edge edge : graph[currentNode]) {
				int next = edge.to;
				int cost = edge.cost;
				boolean check = isChecked || isPassingGH(currentNode, next);

				if (currentCost + cost < minCosts[next]) {
					minCosts[next] = currentCost + cost;
					results[next] = check;
					pq.add(new Path(next, minCosts[next], check));
					// System.out.printf("update minCost %d(%d, %b)\n", next, minCosts[next], check);
				} else if (currentCost + cost == minCosts[next] && !results[next] && check) {
					// System.out.printf("update check %d\n", next);
					results[next] = true;
					pq.add(new Path(next, minCosts[next], true));
				}
			}
		}

		for (int candidate : candidates) {
			if (results[candidate]) {
				answer.append(candidate).append(" ");
			}
		}
		answer.append("\n");
	}

	private static boolean isPassingGH(int from, int to) {
		return (from == g && to == h) || (from == h && to == g);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n + 1];
		candidates = new int[t];
		minCosts = new int[n + 1];
		results = new boolean[n + 1];
		pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		Arrays.fill(minCosts, MAX_COST);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, d));
			graph[b].add(new Edge(a, d));
		}

		for (int i = 0; i < t; i++) {
			candidates[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(candidates);
	}
}
