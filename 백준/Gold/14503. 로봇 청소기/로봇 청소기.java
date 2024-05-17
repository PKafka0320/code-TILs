import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 열의 개수
        M = Integer.parseInt(tokenizer.nextToken()); // 행의 개수
        
        grid = new int[N][M]; // 방 정보
        visited = new boolean[N][M]; // 탐색 여부
        
        tokenizer = new StringTokenizer(reader.readLine());
        int r = Integer.parseInt(tokenizer.nextToken()); // 처음 위치(r)
        int c = Integer.parseInt(tokenizer.nextToken()); // 처음 위치(c)
        int d = Integer.parseInt(tokenizer.nextToken()); // 처음 방향
        
        for (int row = 0; row < N; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < M; col++) {
                grid[row][col] = Integer.parseInt(tokenizer.nextToken()); 
            }
        }
        
        answer = 0; // 청소하는 칸의 개수
        while (true) {
            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (!visited[r][c]) {
                answer++;
                visited[r][c] = true; 
            }
            
            boolean canMove = false;
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] || grid[nr][nc] == 1) continue;
                canMove = true;
                break;
            }
            
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
            // 반시계 방향으로 90도 회전한다.
            // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
            if (canMove) {
                d = (d + 3) % 4;
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (inGrid(nr, nc) && grid[nr][nc] == 0 && !visited[nr][nc]) {
                    r = nr;
                    c = nc;
                }
            }
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
            // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진한다.
            // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
            else {
                int nr = r - dr[d];
                int nc = c - dc[d];
                
                if (inGrid(nr, nc) && grid[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                }
                else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }
    
    public static boolean inGrid(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }
}
