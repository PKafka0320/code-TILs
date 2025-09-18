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
	static int N, M, X;
	static int[] minCostGo, minCostBack;
	static final int MAX_COST = 1_000_000;
	static ArrayList<Edge>[] forward, backward;
	static PriorityQueue<Path> pq;

	static class Path {
		int node, totalCost;

		public Path(int node, int totalCost) {
			this.node = node;
			this.totalCost = totalCost;
		}
	}

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		dijkstra(minCostGo, forward);
		dijkstra(minCostBack, backward);

		int result = 0;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, minCostGo[i] + minCostBack[i]);
		}
		System.out.println(result);
	}

	private static void dijkstra(int[] minCost, ArrayList<Edge>[] graph) {
		minCost[X] = 0;
		pq.add(new Path(X, 0));

		while (!pq.isEmpty()) {
			Path path = pq.poll();
			int node = path.node;
			int totalCost = path.totalCost;

			for (Edge edge : graph[node]) {
				int next = edge.to;
				int cost = edge.cost;

				if (minCost[next] > totalCost + cost) {
					minCost[next] = totalCost + cost;
					pq.add(new Path(next, minCost[next]));
				}
			}
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		minCostGo = new int[N + 1];
		minCostBack = new int[N + 1];
		pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.totalCost));
		forward = new ArrayList[N + 1];
		backward = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
		}

		Arrays.fill(minCostGo, MAX_COST);
		Arrays.fill(minCostBack, MAX_COST);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			forward[from].add(new Edge(to, cost));
			backward[to].add(new Edge(from, cost));
		}
	}
}
