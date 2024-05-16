import java.io.*;
import java.util.*;

class Point {
    int row, col;
    
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString() {
        return "[" + this.row + "," + this.col + "]";
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 미로의 세로 길이
        int M = Integer.parseInt(tokenizer.nextToken()); // 미로의 가로 길이
        
        int[][] maze = new int[N][M]; // maze[i][j] : (i,j) 위치의 미로 정보
        int[][] route = new int[N][M]; // route[i][j] : (i,j) 위치일때 진행된 단계 번호
        boolean[][] visited = new boolean[N][M]; // visited[i][j] : (i,j) 탐색 여부
        Queue<Point> points = new LinkedList<Point>(); // 현재 단계에서 지나가야 할 위치
        Queue<Point> nextPoints = new LinkedList<Point>(); // 다음 단계에서 지나가야 할 위치
        int count = 1; // 단계
        
        // 미로 정보 입력
        for (int row = 0; row < N; row++) {
            String line = reader.readLine();
            for (int col = 0; col < M; col++) {
                if (line.charAt(col) == '+') continue;
                maze[row][col] = 1;
                if ((row == 0 || col == 0 || row == N - 1 || col == M - 1) && points.isEmpty()) { // 시작점 설정
                    route[row][col] = count++;
                    points.add(new Point(row, col));
                }
            }
        }
        
        // 시작점부터 단계별로 지나가야 하는 위치를 탐색하며 탐색
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        int endRow = -1;
        int endCol = -1;
        while(!points.isEmpty()) {
            Point point = points.poll();
            int row = point.row;
            int col = point.col;
            visited[row][col] = true;
            
            // 끝점인지 확인
            if (row == 0 || row == N - 1 || col == 0 || col == M - 1) {
                endRow = row;
                endCol = col;
            }
            
            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nrow = row + dr[dir];
                int ncol = col + dc[dir];
                
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= M) continue;
                if (maze[nrow][ncol] != 1 || visited[nrow][ncol]) continue;
                
                // 다음 단계에 지나가야할 위치와 해당 위치를 지나갈 때의 단계 저장
                route[nrow][ncol] = count;
                visited[nrow][ncol] = true;
                nextPoints.add(new Point(nrow, ncol));
            }
            
            // 현재 단계에서 지나가야할 위치를 모두 탐색했다면 다음 단계에서 지나가야 할 위치를 불러오기
            if (points.isEmpty()) {
                count++;
                points = new LinkedList<Point>(nextPoints);
                nextPoints.clear();
            }
        }
        
        // 끝점에서부터 단계를 역순으로 탐색
        int minCount = route[endRow][endCol];
        while (minCount-- > 0) {
            maze[endRow][endCol] = 2; // 최단 거리로 사용된 길로 갱신
            
            for (int dir = 0; dir < 4; dir++) {
                int nrow = endRow + dr[dir];
                int ncol = endCol + dc[dir];
                
                if (nrow < 0 || nrow >= N || ncol < 0 || ncol >= M) continue;
                if (route[nrow][ncol] != minCount) continue;
                
                // 위치 갱신
                endRow = nrow;
                endCol = ncol;
                break;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (maze[row][col] == 0) {
                    answer.append("+");
                }
                else if (maze[row][col] == 1) {
                    answer.append("@");
                }
                else if (maze[row][col] == 2) {
                    answer.append(".");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
