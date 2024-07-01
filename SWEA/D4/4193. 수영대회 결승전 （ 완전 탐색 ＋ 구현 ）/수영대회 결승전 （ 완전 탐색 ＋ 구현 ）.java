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
    static int n;
    static int[][] grid, time;
    static boolean[][] visited;
 
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        int total_test_case = Integer.parseInt(reader.readLine());
 
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            n = Integer.parseInt(reader.readLine()); // 수영장의 크기
             
            grid = new int[n][n]; // [i][j]: i행 j열의 수영장 상태 (0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이)
            time = new int[n][n]; // [i][j]: i행 j열의 위치로 갈 수 있는 최단 시간
            visited = new boolean[n][n]; // [i][j]: i행 j열의 탐색 여부
             
            for (int row = 0; row < n; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < n; col++) {
                    grid[row][col] = Integer.parseInt(tokenizer.nextToken());
                    time[row][col] = Integer.MAX_VALUE;
                }
            }
             
            tokenizer = new StringTokenizer(reader.readLine());
            Position startPosition = new Position(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            time[startPosition.row][startPosition.col] = 0;
             
            tokenizer = new StringTokenizer(reader.readLine());
            Position endPosition = new Position(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
             
            search(startPosition);
             
            answer.append("#").append(test_case).append(" ");
            if (visited[endPosition.row][endPosition.col]) {
                answer.append(time[endPosition.row][endPosition.col]).append("\n");
            }
            else {
                answer.append("-1\n");
            }
        }
        System.out.println(answer);
    }
     
    public static void search(Position startPosition) {
        Queue<Position> queue = new LinkedList<>(); // 현재 탐색중인 위치
        int[] dr = new int[] {0, 0, 1, -1};
        int[] dc = new int[] {1, -1, 0, 0};
         
        queue.add(startPosition);
        visited[startPosition.row][startPosition.col] = true;
         
        while (!queue.isEmpty()) {
            Position currentPosition = queue.poll();
            int currentRow = currentPosition.row;
            int currentCol = currentPosition.col;
            int currentTime = time[currentRow][currentCol];
             
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = currentRow + dr[dir];
                int nextCol = currentCol + dc[dir];
                 
                // 다음 위치의 범위 확인 및 장애물일 경우 무시
                if (outOfRange(nextRow, nextCol) || grid[nextRow][nextCol] == 1) continue;
                 
                int nextBlock = grid[nextRow][nextCol];
                int nextTime = time[nextRow][nextCol];
                 
                // 다음 위치의 시간보다 빠르게 도착할 수 있다면 갱신
                if (nextBlock == 0 && (nextTime == Integer.MAX_VALUE || currentTime + 1 < nextTime)) {
                    time[nextRow][nextCol] = currentTime + 1;
                    queue.add(new Position(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
                // 소용돌이의 경우 3의 배수 시간에 맞춰서 계산
                else if (nextBlock == 2 && (nextTime == Integer.MAX_VALUE || currentTime + (3 - currentTime % 3) < nextTime)) {
                    time[nextRow][nextCol] = currentTime + (3 - currentTime % 3);
                    queue.add(new Position(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }
     
    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= n || col < 0 || col >= n);
    }
}