import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 10000001;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 행렬의 크기 N
        int M = Integer.parseInt(tokenizer.nextToken()); // 행렬의 크기 M
        
        int[][] grid = new int[N][M]; // 행렬
        int[][][] dp = new int[N][M][3]; // [i][j][k] : k방향으로 i,j 위치까지 갔을 때 최소 연료값
        
        // 행렬 입력
        for (int r = 0; r < N; r++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int c = 0; c < M; c++) {
                grid[r][c]= Integer.parseInt(tokenizer.nextToken());
            }
        }
        
        // dp 초기화
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int k = 0; k < 3; k++) {
                    if (r == 0) {
                        dp[r][c][k] = grid[0][c]; 
                    }
                    else {
                        dp[r][c][k] = MAX;
                    }
                }
            }
        }
        
        int[] dc = {-1, 0, 1};
        for (int r = 0; r < N - 1; r++) {
            for (int c = 0; c < M; c++) {
                for (int dir = 0; dir < 3; dir++) {
                    for (int nextDir = 0; nextDir < 3; nextDir++) {
                        if (dir == nextDir) continue;
                        
                        int nr = r + 1;
                        int nc = c + dc[nextDir];
                        if (nc < 0 || nc >= M) continue;
                        
                        dp[nr][nc][nextDir] = Math.min(dp[nr][nc][nextDir], dp[r][c][dir] + grid[nr][nc]);
                    }
                }
            }
        }
        
        int answer = MAX;
        for (int c = 0; c < M; c++) {
            answer = Math.min(answer, Arrays.stream(dp[N - 1][c]).min().getAsInt());
        }
        System.out.println(answer);
    }
}
