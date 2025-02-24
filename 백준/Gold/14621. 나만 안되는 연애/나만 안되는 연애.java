import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node1, node2, distance;

        public Edge(int node1, int node2, int distance) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }

    static int N, M;
    static int[] roots;
    static char[] types;
    static Queue<Edge> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roots = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            roots[i] = i;
        }

        types = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            types[i] = st.nextToken().charAt(0);
        }

        edges = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            edges.add(new Edge(node1, node2, distance));
        }

        long answer = 0;
        int count = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int node1 = edge.node1;
            int node2 = edge.node2;
            int distance = edge.distance;

            if (types[node1] == types[node2]) continue;

            if (union(node1, node2)) {
                answer += distance;
                count++;
            }
        }

        System.out.println(count == N-1 ? answer : -1);
    }

    private static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) return false;

        roots[root1] = root2;
        return true;
    }

    private static int find(int node) {
        if (roots[node] == node) return node;
        return roots[node] = find(roots[node]);
    }
}