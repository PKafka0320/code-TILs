import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node1, node2, length;

        public Edge(int node1, int node2, int length) {
            this.node1 = node1;
            this.node2 = node2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return this.length - o.length;
        }
    }

    static int N;
    static List<Edge> edges;
    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        roots = new int[N];
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }

        edges = new ArrayList<>();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < N; j++) {
                int length = toInt(line.charAt(j));
                answer += length;

                if (i == j || length == 0) continue;
                edges.add(new Edge(i, j, length));
            }
        }
        Collections.sort(edges);

        int count = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int node1 = edge.node1;
            int node2 = edge.node2;
            int length = edge.length;

            if (union(node1, node2)) {
                answer -= length;
                count++;
            }
            if (count == N-1) break;
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

    private static int toInt(char ch) {
        if (ch == '0') return 0;
        if ('a' <= ch && ch <= 'z') {
            return ch - 'a' + 1;
        } else {
            return ch - 'A' + 27;
        }
    }
}