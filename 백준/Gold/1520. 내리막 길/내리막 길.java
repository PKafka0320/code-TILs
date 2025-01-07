import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp, heights;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                dp[r][c] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        if (r == N-1 && c == M-1) return 1;

        if (dp[r][c] != -1) return dp[r][c];

        int ways = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (outOfGrid(nr, nc)) continue;

            if (heights[r][c] <= heights[nr][nc]) continue;

            ways += dfs(nr, nc);
        }

        return dp[r][c] = ways;
    }

    static boolean outOfGrid(int nr, int nc) {
        return (nr < 0 || nr >= N || nc < 0 || nc >= M);
    }
}
