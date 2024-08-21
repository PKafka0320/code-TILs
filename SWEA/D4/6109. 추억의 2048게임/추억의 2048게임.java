import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] grid;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append("\n");
            
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            String op = st.nextToken();
            
            grid = new int[n][n];
            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            int rotateCnt = 0;
            if (op.equals("up")) rotateCnt = 2;
            else if (op.equals("left")) rotateCnt = 1;
            else if (op.equals("right")) rotateCnt = 3;
            
            rotate(rotateCnt);
            
            moveDown();
            
            rotate(4 - rotateCnt);
            
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    answer.append(grid[r][c]).append(" ");
                }
                answer.append("\n");
            }
            
        }
        
        System.out.println(answer);
    }
    
    public static void rotate(int cnt) {
        for (int i = 0; i < cnt; i++) {
            int[][] tempGrid = new int[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    tempGrid[r][c] = grid[c][n - r - 1];
                }
            }
            grid = tempGrid;
        }
        
    }
    
    public static void moveDown() {
        for (int c = 0; c < n; c++) {
            int currentPosition = n - 1;
            
            for (int r = n - 2; r >= 0; r--) {
                if (grid[r][c] == 0) continue;
                
                if (grid[currentPosition][c] == grid[r][c]) {
                    grid[currentPosition][c] = 2 * grid[r][c];
                    grid[r][c] = 0;
                    currentPosition--;
                } else {
                    while (grid[currentPosition][c] != grid[r][c] && grid[currentPosition][c] != 0 && currentPosition > r) {
                        currentPosition--;
                    }
                    
                    if (currentPosition == r) continue;
                    
                    if (grid[currentPosition][c] == grid[r][c]) {
                        grid[currentPosition][c] = 2 * grid[r][c];
                        grid[r][c] = 0;
                        currentPosition--;
                    } else if (grid[currentPosition][c] == 0) {
                        grid[currentPosition][c] = grid[r][c];
                        grid[r][c] = 0;
                    }
                        
                }
            }
            
        }
    }
}