import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        StringBuilder answer = new StringBuilder();
        
        int totalTest = Integer.parseInt(reader.readLine());
        for (int test_case = 1; test_case <= totalTest; test_case++) {
            int[][] grid = new int[4][4]; // 격자칸에 적혀있는 수
            HashSet<String>[][] set = new HashSet[4][4]; // 격자칸마다 만들수 있는 수의 집합
            
            // 초기화
            for (int row = 0; row < 4; row++) {
                token = new StringTokenizer(reader.readLine());
                for (int col = 0; col < 4; col++) {
                    grid[row][col] = Integer.parseInt(token.nextToken());
                    set[row][col] = new HashSet<String>();
                    set[row][col].add(String.valueOf(grid[row][col]));
                }
            }
            
            // 주변 격자칸이 만들 수 있는 수 앞에 격자칸에 적혀있는 수를 붙여서 집합을 만들기
            for (int count = 1; count <= 6; count++) {
                int[] dr = {1, -1, 0, 0};
                int[] dc = {0, 0, 1, -1};
                HashSet<String>[][] tmpset = new HashSet[4][4]; // 격자칸이 만들 수 있는 수를 저장하는 임시 집합
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        tmpset[row][col] = new HashSet<String>();
                        for (int d = 0; d < 4; d++) {
                            int nr = row + dr[d];
                            int nc = col + dc[d];
                            
                            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue; // 범위 확인
                            for (String str : set[nr][nc]) {
                                tmpset[row][col].add(String.valueOf(grid[row][col]) + str);
                            }
                        }
                    }
                }
                
                // 임시 집합을 복사
                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        set[row][col] = tmpset[row][col];
                    }
                }
            }
            
            HashSet<String> res = new HashSet<String>();
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    for (String str : set[row][col]) res.add(str);
                }
            }
            
            answer.append("#").append(test_case).append(" ").append(res.size()).append("\n");
        }

        System.out.println(answer);
    }
}
