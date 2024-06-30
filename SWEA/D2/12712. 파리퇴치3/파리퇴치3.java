import java.io.*;
import java.util.*;

public class Solution {
    static int n, m;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total_test_case = Integer.parseInt(reader.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            
            grid = new int[n][n]; // [i][j]: i행 j열의 파리 수
            
            for (int row = 0; row < n; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < n; col++) {
                    grid[row][col] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            
            int max = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    max = Math.max(max, plus(row, col));
                    max = Math.max(max, cross(row, col));
                }
            }
            
            answer.append("#").append(test_case).append(" ").append(max).append("\n");
        }
        System.out.println(answer);
    }
    
    public static int plus(int row, int col) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        int sum = grid[row][col];
        
        for (int dir = 0; dir < 4; dir++) {
            for (int offset = 1; offset < m; offset++) {
                int nr = row + dr[dir] * offset;
                int nc = col + dc[dir] * offset;
                
                if (outOfRange(nr, nc)) continue;
                
                sum += grid[nr][nc];
            }
        }
        
        return sum;
    }

    public static int cross(int row, int col) {
        int[] dr = {1, 1, -1, -1};
        int[] dc = {1, -1, 1, -1};
        int sum = grid[row][col];
        
        for (int dir = 0; dir < 4; dir++) {
            for (int offset = 1; offset < m; offset++) {
                int nr = row + dr[dir] * offset;
                int nc = col + dc[dir] * offset;
                
                if (outOfRange(nr, nc)) continue;
                
                sum += grid[nr][nc];
            }
        }
        
        return sum;
    }
    
    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= n || col < 0 || col >= n);
    }
}
