import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Path implements Comparable<Path> {
		int nodeNumber, cost;

		public Path(int nodeNumber, int cost) {
			this.nodeNumber = nodeNumber;
			this.cost = cost;
		}

		@Override
		public int compareTo(Path o) {
			return this.cost - o.cost;
		}
	}

	static class Node {
		int number, cost;

		public Node(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, minCost[], srcNode, dstNode;
	static int MAX_COST = 100_000_000;
	static PriorityQueue<Path> pq;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		minCost[srcNode] = 0;
		pq.add(new Path(srcNode, 0));

		while (!pq.isEmpty()) {
			Path path = pq.poll();
			int curNode = path.nodeNumber;
			int curCost = path.cost;

			if (curCost != minCost[curNode]) {
				continue;
			}

			for (Node next : graph[curNode]) {
				int nextNode = next.number;
				int newCost = curCost + next.cost;
				if (minCost[nextNode] > newCost) {
					minCost[nextNode] = newCost;
					pq.add(new Path(nextNode, newCost));
				}
			}
		}

		System.out.println(minCost[dstNode]);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		minCost = new int[N + 1];
		graph = new ArrayList[N + 1];
		pq = new PriorityQueue<>();

		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		Arrays.fill(minCost, MAX_COST);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		srcNode = Integer.parseInt(st.nextToken());
		dstNode = Integer.parseInt(st.nextToken());
	}
}
