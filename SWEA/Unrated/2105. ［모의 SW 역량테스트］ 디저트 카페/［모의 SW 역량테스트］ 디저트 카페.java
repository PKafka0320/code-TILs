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
            
            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {
                    selected.clear();
                    startR = r;
                    startC = c;
                    
                    find(r, c, 0, 0);
                }
            }
            
            answer.append(result == 0 ? -1 : result).append("\n");
        }
        System.out.println(answer);
    }
    
    public static void find(int r, int c, int dir, int len) {
        selected.add(grid[r][c]);
        visited[r][c] = true;
        
        if (dir >= 4) {
            selected.remove(selected.size() - 1);
            visited[r][c] = false;
            return;
        }
        
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        if (nr == startR && nc == startC) {
            result = Math.max(result, selected.size());
            selected.remove(selected.size() - 1);
            visited[r][c] = false;
            return;
        }
        
        if (!outOfGrid(nr, nc) && !visited[nr][nc] && !selected.contains(grid[nr][nc])) {
            find(nr, nc, dir + 1, 0);
            find(nr, nc, dir, len + 1);
        }
        
        selected.remove(selected.size() - 1);
        visited[r][c] = false;
    }
    
    public static boolean outOfGrid(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }
}
