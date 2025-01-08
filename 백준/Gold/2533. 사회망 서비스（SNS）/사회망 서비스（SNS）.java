import java.io.*;
import java.util.*;

public class Main {
    static int MAX_VALUE = 1_000_000;
    static int N;
    static int[][] dp;
    static boolean[] visited;

    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(v);
            edges[v].add(u);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], MAX_VALUE);
        }

        System.out.println(Math.min(getMinValue(1, false), getMinValue(1, true)));
    }

    static int getMinValue(int node, boolean select) {
        if (dp[node][0] != MAX_VALUE && dp[node][1] != MAX_VALUE) {
            return select ? dp[node][1] : Math.min(dp[node][0], dp[node][1]);
        }
        visited[node] = true;

        dp[node][0] = 0;
        dp[node][1] = 1;
        for (int ad : edges[node]) {
            if (visited[ad]) continue;
            dp[node][0] += getMinValue(ad, true);
            dp[node][1] += getMinValue(ad, false);
        }

        return select ? dp[node][1] : Math.min(dp[node][0], dp[node][1]);
    }
}
