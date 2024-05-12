import java.io.*;
import java.util.*;

class Point {
    int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n;
    static int[][] grid; // 격자
    static int[][] count; // 격자 위치로 가는데 최소로 필요한 변경 수
    static Queue<Point> queue;
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(reader.readLine());
        grid = new int[n][n];
        count = new int[n][n];
        
        // 격자
        for (int r = 0; r < n; r++) { // row
            String line = reader.readLine();
            for (int c = 0; c < n; c++) { // column
                grid[r][c] = Character.getNumericValue(line.charAt(c));
                count[r][c] = Integer.MAX_VALUE;
            }
        }
        
        queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        count[0][0] = 0;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            
            // 4 방향 확인 및 큐에 추가
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue; // 좌표 유효성 확인
                // 더 적은 개수로 갈 수 있는 경우 확인
                if (grid[nx][ny] == 1 && count[x][y] < count[nx][ny]) {
                    count[nx][ny] = count[x][y];
                    queue.add(new Point(nx, ny));
                }
                else if (grid[nx][ny] == 0 && count[x][y] + 1 < count[nx][ny]) {
                    count[nx][ny] =  count[x][y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        
        System.out.println(count[n - 1][n - 1]);
    }
}
