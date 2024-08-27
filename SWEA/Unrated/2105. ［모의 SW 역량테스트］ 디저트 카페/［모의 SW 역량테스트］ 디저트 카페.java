import java.io.*;
import java.util.*;

public class Solution {
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static List<Integer> selected;
    static int[][] grid;
    static boolean[][] visited;
    static int N, result, startR, startC;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            selected = new ArrayList<>();
            visited = new boolean[N][N];
            result = 0;
            
            // 윗 꼭짓점이 될 수 있는 부분부터 탐색
            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {
                    selected.clear();
                    startR = r;
                    startC = c;
                    
                    find(r, c, 0);
                }
            }
            
            answer.append(result == 0 ? -1 : result).append("\n");
        }
        System.out.println(answer);
    }
    
    public static void find(int r, int c, int dir) {
        // 해당 위치의 값 추가
        selected.add(grid[r][c]);
        visited[r][c] = true;
        
        // 한바퀴 돌았다면 종료
        if (dir >= 4) {
            // 추가했던 값 제거
            selected.remove(selected.size() - 1);
            visited[r][c] = false;
            return;
        }
        
        // 다음 위치
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        // 다음 위치가 시작 위치라면 결과 갱신 및 종료
        if (nr == startR && nc == startC) {
            // 갱신
            result = Math.max(result, selected.size());
            // 추가했던 값 제거
            selected.remove(selected.size() - 1);
            visited[r][c] = false;
            return;
        }
        
        // 다음 위치의 값을 추가할 수 있다면 이동
        if (!outOfGrid(nr, nc) && !visited[nr][nc] && !selected.contains(grid[nr][nc])) {
            find(nr, nc, dir + 1);
            
            find(nr, nc, dir);
        }
        
        // 모든 탐색을 종료했다면 추가했던 값 제거
        selected.remove(selected.size() - 1);
        visited[r][c] = false;
    }
    
    // 범위 확인용
    public static boolean outOfGrid(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }
    
}
