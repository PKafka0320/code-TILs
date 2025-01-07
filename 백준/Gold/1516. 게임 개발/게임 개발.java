import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] times = new int[N + 1];
        int[] indegree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());
            while (pre != -1) {
                edges[pre].add(i);
                indegree[i]++;
                pre = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int[] dp = new int[N + 1];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            dp[cur] += times[cur];

            for (int next : edges[cur]) {
                dp[next] = Math.max(dp[next], dp[cur]);
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            answer.append(dp[i]).append("\n");
        }
        System.out.println(answer);
    }
}
