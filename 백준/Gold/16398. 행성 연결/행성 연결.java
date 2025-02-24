import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node1, node2, cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    static int N;
    static int[] roots;
    static int[][] cost;
    static Queue<Edge> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N+1][N+1];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                cost[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        edges = new PriorityQueue<>();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= r-1; c++) {
                edges.add(new Edge(r, c, cost[r][c]));
            }
        }

        roots = new int[N+1];
        for (int i = 0; i <= N; i++) {
            roots[i] = i;
        }

        long answer = 0;
        while (!edges.isEmpty()) {
            Edge currentEdge = edges.poll();

            int node1 = currentEdge.node1, node2 = currentEdge.node2;

            if (unionRoot(node1, node2)) {
                answer += currentEdge.cost;
            }
        }
        System.out.println(answer);
    }

    private static boolean unionRoot(int node1, int node2) {
        int root1 = findRoot(node1);
        int root2 = findRoot(node2);

        if (root1 == root2) return false;

        roots[root1] = root2;
        return true;
    }

    private static int findRoot(int node) {
        if (roots[node] == node) return node;
        return roots[node] = findRoot(roots[node]);
    }
}