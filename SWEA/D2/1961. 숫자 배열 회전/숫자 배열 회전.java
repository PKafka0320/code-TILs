import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total_test_case = Integer.parseInt(reader.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            n = Integer.parseInt(reader.readLine()); // 격자의 크기
            
            grid = new int[n][n]; // [i][j]: i행 j열의 숫자
            
            for (int row = 0; row < n; row++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < n; col++) {
                    grid[row][col] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            
            answer.append("#").append(test_case).append("\n");
            for (int row = 0; row < n; row++) {
                rotate();
                for (int col = 0; col < n; col++) {
                    answer.append(grid[row][col]);
                }
                answer.append(" ");
                rotate();
                for (int col = 0; col < n; col++) {
                    answer.append(grid[row][col]);
                }
                answer.append(" ");
                rotate();
                for (int col = 0; col < n; col++) {
                    answer.append(grid[row][col]);
                }
                answer.append("\n");
                rotate();
            }
        }
        System.out.println(answer);
    }
    
    public static void rotate() {
        int[][] tmpGrid = new int[n][n];
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                tmpGrid[row][col] = grid[n - col - 1][row];
            }
        }
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = tmpGrid[row][col];
            }
        }
    }
}
