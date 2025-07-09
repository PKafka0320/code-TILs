import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, START, END;
    static int[] inDegree, cost;
    static boolean[] check;
    static List<Edge>[] graph, reGraph;
    static Queue<Integer> queue;

    static class Edge {
        int to, time;

        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        cost = new int[N + 1];
        graph = new ArrayList[N + 1];
        reGraph = new ArrayList[N + 1];
        queue = new LinkedList<>();
        check = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            reGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, time));
            reGraph[to].add(new Edge(from, time));
            inDegree[to]++;
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        queue.add(START);
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge nextEdge : graph[current]) {
                int next = nextEdge.to;
                int time = nextEdge.time;

                if (cost[next] <= cost[current] + time) {
                    cost[next] = cost[current] + time;
                }

                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int count = 0;
        queue.add(END);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (check[current]) continue;
            check[current] = true;

            for (Edge nextEdge : reGraph[current]) {
                int next = nextEdge.to;
                int time = nextEdge.time;

                if (cost[current] == cost[next] + time) {
                    count++;
                    queue.add(next);
                }
            }
        }

        System.out.println(cost[END]);
        System.out.println(count);
    }
}