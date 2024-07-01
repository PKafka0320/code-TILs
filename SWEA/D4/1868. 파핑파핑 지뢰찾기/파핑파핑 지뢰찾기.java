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
    static char[][] originBoard;
    static int[][] board;
    static boolean[][] visited;
 
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total_test_case = Integer.parseInt(reader.readLine());
 
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            n = Integer.parseInt(reader.readLine()); // 격자의 크기
             
            originBoard = new char[n][n]; // [i][j]: i행 j열의 문자
            board = new int[n][n]; // [i][j]: i행 j열의 주변 지뢰 수
            visited = new boolean[n][n]; // [i][j]: i행 j열의 탐색 여부
             
            // 문자열 입력
            for (int row = 0; row < n; row++) {
                char[] line = reader.readLine().toCharArray();
                for (int col = 0; col < n; col++) {
                    originBoard[row][col] = line[col];
                }
            }
             
            // 각 칸마다 주변 지뢰 개수 계산
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    board[row][col] = countMine(row, col);
                }
            }
             
            int count = 0;
             
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (visited[row][col] || board[row][col] == -1) continue;
                    count += search(row, col);
                }
            }
             
            answer.append("#").append(test_case).append(" ").append(count).append("\n");
        }
        System.out.println(answer);
    }
     
    public static int search(int startRow, int startCol) {
        int[] dr = new int[] {0, 0, 1, 1, 1, -1, -1, -1};
        int[] dc = new int[] {1, -1, 1, 0, -1, 1, 0, -1};
         
        // 시작 위치가 0이 아닐 때
        // 주변에 0이 있을 경우 탐색 종료, 없다면 클릭 1번 추가
        if (board[startRow][startCol] != 0) {
            for (int dir = 0; dir < 8; dir++) {
                int nr = startRow + dr[dir];
                int nc = startCol + dc[dir];
                 
                if (outOfRange(nr, nc)) continue;
                if (board[nr][nc] == 0) return 0;
            }
            visited[startRow][startCol] = true;
            return 1;
        }
         
        // 시작 위치가 0일때 탐색
        Queue<Position> queue = new LinkedList<>();
         
        queue.add(new Position(startRow, startCol));
        visited[startRow][startCol] = true;
         
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int currentRow = position.row;
            int currentCol = position.col;
             
            for (int dir = 0; dir < 8; dir++) {
                int nextRow = currentRow + dr[dir];
                int nextCol = currentCol + dc[dir];
                 
                if (outOfRange(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;
                 
                // 다음 위치의 나머지는 탐색 여부를 갱신하고
                // 해당 위치의 숫자가 0일 때만 큐에 추가
                if (board[nextRow][nextCol] == 0) {
                    queue.add(new Position(nextRow, nextCol));
                }
                visited[nextRow][nextCol] = true;
            }
        }
         
        return 1;
    }
     
    // 주변의 지뢰 개수 계산
    public static int countMine(int row, int col) {
        if (originBoard[row][col] == '*') return -1; // 폭탄위치일 경우 -1
         
        int count = 0;
         
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r == row && c == col) continue;
                if (outOfRange(r, c)) continue;
                 
                if (originBoard[r][c] == '*') count++;
            }
        }
         
        return count;
    }
     
    // 범위 확인용
    public static boolean outOfRange(int row, int col) {
        return (row < 0 || row >= n || col < 0 || col >= n);
    }
}
