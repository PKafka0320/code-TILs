import java.io.*;
import java.util.*;

// 좌표 객체
class Position {
    int x, y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int totalTest = 10;
        for (int test_case = 1; test_case <= totalTest; test_case++) {
            int testNumber = Integer.parseInt(reader.readLine());
            int[][] grid = new int[16][16]; // 미로 그리드
            boolean[][] visited = new boolean[16][16];
            Position start = null; // 시작 지점
            Position end = null; // 도착 지점
            
            // 미로 입력
            for (int row = 0; row < 16; row++) {
                String numbers = reader.readLine();
                for (int col = 0; col < 16; col++) {
                    grid[row][col] = Integer.parseInt(numbers.substring(col, col + 1));
                    if (grid[row][col] == 2) start = new Position(row, col);
                    if (grid[row][col] == 3) end = new Position(row, col);
                }
            }
            
            // bfs 초기화
            Queue<Position> queue = new LinkedList<Position>();
            queue.add(start);
            visited[start.x][start.y] = true;
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            
            // bfs 탐색
            while (!queue.isEmpty()) {
                Position curPosition = queue.poll();
                int x = curPosition.x;
                int y = curPosition.y;
                
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16) continue;
                    if (grid[nx][ny] == 1 || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    queue.add(new Position(nx, ny));
                }
            }
            
            if (visited[end.x][end.y]);
            answer.append("#").append(testNumber).append(" ").append(visited[end.x][end.y] ? 1 : 0).append("\n");
        }

        System.out.println(answer);
    }
}
