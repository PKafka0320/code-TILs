import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] rootOf;
    static int[][] costOf;
    static LinkedList<Integer>[] nodes;

    static class Node {
        int number, maxCost;

        public Node(int number, int maxCost) {
            this.number = number;
            this.maxCost = maxCost;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rootOf = new int[N + 1];
        costOf = new int[N + 1][N + 1];
        nodes = new LinkedList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            rootOf[i] = i;
            nodes[i] = new LinkedList<>();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            costOf[A][B] = costOf[B][A] = C;
            pq.add(new Edge(A, B, C));
        }

        int edgeCount = 0;
        int mstCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;

            if (union(from, to)) {
                edgeCount++;
                mstCost += cost;
                nodes[from].add(to);
                nodes[to].add(from);
            }

            if (edgeCount == N - 1) {
                break;
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            answer.append(mstCost - maxCost(X, Y)).append("\n");
        }
        System.out.println(answer);
    }

    public static int maxCost(int node1, int node2) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(node1, 0));

        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            int current = currentNode.number;
            int currentMaxCost = currentNode.maxCost;

            visited[current] = true;

            for (Integer next : nodes[current]) {
                if (visited[next]) {
                    continue;
                }

                if (next == node2) {
                    return Math.max(currentMaxCost, costOf[current][next]);
                }
                q.add(new Node(next, Math.max(currentMaxCost, costOf[current][next])));
            }
        }

        return -1;
    }

    public static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) {
            return false;
        }

        rootOf[root1] = root2;
        return true;
    }

    public static int find(int node) {
        if (node == rootOf[node]) {
            return node;
        }

        return rootOf[node] = find(rootOf[node]);
    }
}