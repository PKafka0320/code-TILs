import java.io.*;
import java.util.*;

class Position {
    int row, col;
    
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    static int n, answerCount, answerLength;
    static int[] dr = new int[] {-1,1,0,0};
    static int[] dc = new int[] {0,0,-1,1};
    static int[][] grid;
    static List<Position> cores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            
            grid = new int[n][n];
            cores = new ArrayList<>();
            answerCount = 0;
            answerLength = (int) 1e9;
            
            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    
                    if (grid[r][c] == 1 && (r > 0 && r < n - 1 && c > 0 && c < n - 1)) {
                        cores.add(new Position(r, c));
                    }
                }
            }
//            System.out.println(cores.size());
            
            dfs(0, 0, 0);
            
            sb.append(answerLength).append("\n");
        }
        
        System.out.println(sb);
    }
    
    public static void dfs(int idx, int count, int totalLength) {
//        System.out.println(idx);
        if (idx >= cores.size()) {
//            System.out.printf("count|Length: %d,%d / %d,%d\n", count, totalLength, answerCount, answerLength);
//            for (int r = 0; r < n; r++) {
//                for (int c = 0; c < n; c++) {
//                    System.out.printf("%d ", grid[r][c]);
//                }
//                System.out.println();
//            }
//            System.out.println();
            
            if (count > answerCount) {
                answerLength = totalLength;
                answerCount = count;
            } else if (count == answerCount) {
                answerLength = Math.min(answerLength, totalLength);
            }
            return;
        }
        Position currentCore = cores.get(idx);
        
        for (int d = 0; d < 4; d++) {
            if (canConnect(currentCore, d)) {
                int length = connect(currentCore, d);
                dfs(idx + 1, count + 1, totalLength + length);
                disconnect(currentCore, d);
            }
        }
        dfs(idx + 1, count, totalLength);
    }
    
    public static boolean canConnect(Position p, int dir) {
        int nr = p.row + dr[dir];
        int nc = p.col + dc[dir];
        
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (grid[nr][nc] != 0) return false;
            nr += dr[dir];
            nc += dc[dir];
        }
        
        return true;
    }
    
    public static int connect(Position p, int dir) {
        int len = 0;
        int nr = p.row + dr[dir];
        int nc = p.col + dc[dir];
        
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            len++;
            grid[nr][nc] = 2;
            nr += dr[dir];
            nc += dc[dir];
        }
        
//        System.out.println("after connect");
//        for (int r = 0; r < n; r++) {
//            for (int c = 0; c < n; c++) {
//                System.out.printf("%d ", grid[r][c]);
//            }
//            System.out.println();
//        }
//        System.out.println();
        
        return len;
    }
    
    public static void disconnect(Position p, int dir) {
        int nr = p.row + dr[dir];
        int nc = p.col + dc[dir];
        
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            grid[nr][nc] = 0;
            nr += dr[dir];
            nc += dc[dir];
        }
        
//        System.out.println("after disconnect");
//        for (int r = 0; r < n; r++) {
//            for (int c = 0; c < n; c++) {
//                System.out.printf("%d ", grid[r][c]);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

}
