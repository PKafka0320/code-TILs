import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int node, distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Integer>[] routes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            routes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            routes[from].add(to);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        dist[X] = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(X, 0));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.distance != dist[current.node]) continue;

            for (Integer ad : routes[current.node]) {
                if (dist[ad] == -1 || dist[ad] > current.distance + 1) {
                    dist[ad] = current.distance + 1;
                    queue.add(new Pair(ad, dist[ad]));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            StringBuilder answer = new StringBuilder();
            for (int num : result) {
                answer.append(num).append("\n");
            }
            System.out.println(answer.toString());
        }
    }
}