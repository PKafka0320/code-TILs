import java.io.*;
import java.util.*;

public class Solution {
    static int N, K, max, result, map[][], tempMap[][], dr[], dc[];
    static boolean visited[][];
    static List<Position> starts;
    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dr = new int[] {-1,1,0,0};
        dc = new int[] {0,0,-1,1};
        starts = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            tempMap = new int[N][N];
            visited = new boolean[N][N];
            starts.clear();
            max = 0;
            
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = tempMap[r][c] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[r][c]);
                }
            }
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] != max) continue;
                    starts.add(new Position(r, c));
                }
            }
            
            result = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < K; k++) {
                        tempMap[r][c]--;
                        
                        for (Position position : starts) {
                            if (tempMap[position.row][position.col] != max) continue;
                            
                            resetVisited();
                            evaluate(position.row, position.col, 1);
                        }
                    }
                    
                    tempMap[r][c] = map[r][c];
                }
            }
            
            answer.append(result).append("\n");
        }
        System.out.println(answer);
    }
    
    public static void evaluate(int r, int c, int len) {
        boolean isEnd = true;
        visited[r][c] = true;
        
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
            if (tempMap[nr][nc] >= tempMap[r][c]) continue;
            isEnd = false;
            evaluate(nr, nc, len + 1);
        }
        
        if (isEnd) {
            result = Math.max(result, len);
        }
        visited[r][c] = false;
    }
    
    public static void resetVisited() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                visited[r][c] = false;
            }
        }
    }
    
}
