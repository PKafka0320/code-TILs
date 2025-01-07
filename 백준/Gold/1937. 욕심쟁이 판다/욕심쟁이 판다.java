import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] grid, dp;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    static int dfs(int i, int j) {
        if (dp[i][j] != -1) return dp[i][j];

        int maxCount = 1;
        for (int dir = 0; dir < 4; dir++) {
            int ni = i + dr[dir];
            int nj = j + dc[dir];

            if (outOfGrid(ni, nj)) continue;
            if (grid[ni][nj] <= grid[i][j]) continue;

            maxCount = Math.max(maxCount, 1 + dfs(ni, nj));
        }

        return dp[i][j] = maxCount;
    }

    static boolean outOfGrid(int i, int j) {
        return (i < 0 || i >= N || j < 0 || j >= N);
    }
}
