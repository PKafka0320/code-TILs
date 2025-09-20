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
	static int N, E, V1, V2;
	static final int INF_COST = 200_000_001;
	static ArrayList<Edge>[] graph;
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
		int[] minCostV1 = dijkstra(V1);
		int[] minCostV2 = dijkstra(V2);

		if ((minCostV1[1] == INF_COST || minCostV2[1] == INF_COST) ||
			(minCostV1[N] == INF_COST || minCostV2[N] == INF_COST)) {
			System.out.println(-1);
			return;
		}

		int result1 = minCostV1[1] + minCostV1[V2] + minCostV2[N];
		int result2 = minCostV2[1] + minCostV2[V1] + minCostV1[N];

		System.out.println(Math.min(result1, result2));
	}

	private static int[] dijkstra(int start) {
		int[] minCost = new int[N + 1];
		Arrays.fill(minCost, INF_COST);

		minCost[start] = 0;
		pq.add(new Path(start, 0));

		while (!pq.isEmpty()) {
			Path path = pq.poll();
			int node = path.node;
			int totalCost = path.totalCost;

			if (minCost[node] != totalCost) {
				continue;
			}

			for (Edge edge : graph[node]) {
				int next = edge.to;
				int cost = edge.cost;

				if (minCost[next] > totalCost + cost) {
					minCost[next] = totalCost + cost;
					pq.add(new Path(next, minCost[next]));
				}
			}
		}

		return minCost;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.totalCost));

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}

		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
	}
}
