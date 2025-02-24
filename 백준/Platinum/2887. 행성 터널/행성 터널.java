import java.io.*;
import java.util.*;

public class Main {
    static class Position implements Comparable<Position> {
        long value;
        int node;

        public Position(int value, int node) {
            this.value = value;
            this.node = node;
        }

        @Override
        public int compareTo(Position o) {
            return (int) (this.value - o.value);
        }
    }
    static class Edge implements Comparable<Edge> {
        int node1, node2;
        long cost;

        public Edge(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) return 1;
            if (this.cost == o.cost) return 0;
            return -1;
        }
    }

    static int N;
    static int[] roots;
    static Queue<Edge> edges;
    static List<Position> costX, costY, costZ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        roots = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            roots[i] = i;
        }

        edges = new PriorityQueue<>();
        costX = new ArrayList<>();
        costY = new ArrayList<>();
        costZ = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            costX.add(new Position(x, i+1));
            costY.add(new Position(y, i+1));
            costZ.add(new Position(z, i+1));
        }

        Collections.sort(costX);
        Collections.sort(costY);
        Collections.sort(costZ);

        for (int i = 0; i < N-1; i++) {
            edges.add(new Edge(costX.get(i).node, costX.get(i+1).node, costX.get(i+1).value - costX.get(i).value));
            edges.add(new Edge(costY.get(i).node, costY.get(i+1).node, costY.get(i+1).value - costY.get(i).value));
            edges.add(new Edge(costZ.get(i).node, costZ.get(i+1).node, costZ.get(i+1).value - costZ.get(i).value));
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