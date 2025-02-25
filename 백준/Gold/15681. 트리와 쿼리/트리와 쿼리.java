import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static int[] parents, dp;
    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            edges[V].add(U);
            edges[U].add(V);
        }

        parents = new int[N + 1];
        parents[R] = R;
        makeTree(R);

        dp = new int[N + 1];
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int startNode = Integer.parseInt(br.readLine());
            answer.append(count(startNode)).append("\n");
        }
        System.out.println(answer.toString());
    }

    private static int count(int node) {
        if (dp[node] != 0) return dp[node];
        int result = 0;

        for (int next : edges[node]) {
            if (parents[node] == next) continue;
            result += count(next);
        }
        result++;

        return dp[node] = result;
    }

    private static void makeTree(int node) {
        for (int next : edges[node]) {
            if (parents[node] == next) continue;
            parents[next] = node;
            makeTree(next);
        }
    }
}