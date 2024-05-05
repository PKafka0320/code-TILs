import java.io.*;
import java.util.*;

class Point {
    int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = Integer.parseInt(br.readLine());
            
            int[][] grid = new int[size][size];
            int[][] sum = new int[size][size];
            
            for (int r = 0; r < size; r++) {
                String str = br.readLine();
                for (int c = 0; c < size; c++) {
                    grid[r][c] = Character.getNumericValue(str.charAt(c));
                    sum[r][c] = Integer.MAX_VALUE;
                }
            }
            sum[0][0] = grid[0][0];

            Queue<Point> q = new LinkedList<Point>();
            q.add(new Point(0, 0));

            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};
            while (!q.isEmpty()) {
                Point p = q.poll();
                
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x + dx[dir];
                    int ny = p.y + dy[dir];
                    
                    if (nx >= 0 && ny >= 00 && nx < size && ny < size) {
                        if (sum[nx][ny] > sum[p.x][p.y] + grid[nx][ny]) {
                            sum[nx][ny] = Math.min(sum[nx][ny], sum[p.x][p.y] + grid[nx][ny]);
                            q.add(new Point(nx, ny));
                        }
                    }
                }
            }
            
            answer.append("#").append(test_case).append(" ").append(sum[size - 1][size - 1]).append("\n");
        }
        
        System.out.println(answer);
    }
}