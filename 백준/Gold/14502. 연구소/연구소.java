import java.io.*;
import java.util.*;

class Position {
    int row, col;
    
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static int n, m, answer;
    static int[][] grid;
    static int[] dr = new int[] {1,-1,0,0};
    static int[] dc = new int[] {0,0,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        grid = new int[n][m];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        answer = 0;
        select(0, 0, 0);
        System.out.println(answer);
    }
    
    public static void select(int number, int row, int col) {
//        if (number == 1) {
//            System.out.printf("%d, %d%n", row, col);        
//        } else if (number == 2) {
//            System.out.printf("----%d, %d%n", row, col);   
//        } else if (number == 3) {
//            System.out.printf("--------%d, %d%n", row, col);   
//        }
        
        if (number >= 3) {
            answer = Math.max(answer, count());
            return;
        }
        
        if (row >= n || col >= m) return;
        if (row == n - 1 && col == m - 1) return;
        
        for (int r = row; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r == row && c < col) continue;
                if (grid[r][c] != 0) continue;
                
                grid[r][c] = 1;
                select(number + 1, r, c);
                grid[r][c] = 0;
            }
        }
    }
    
    public static int count() {
        int[][] tmp = new int[n][m];
        Queue<Position> q = new LinkedList<>(); 
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                tmp[r][c] = grid[r][c];
                if (tmp[r][c] == 2) {
                    q.add(new Position(r, c));
                }
            }
        }
        
        while (!q.isEmpty()) {
            Position p = q.poll();
            int r = p.row;
            int c = p.col;
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (tmp[nr][nc] != 0) continue;
                tmp[nr][nc] = 2 ;
                q.add(new Position(nr, nc));
            }
        }
        
        int sum = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
//                System.out.printf("%d ", tmp[r][c]);
                if (tmp[r][c] == 0) {
                    sum++;
                }
            }
//            System.out.println();
        }
//        System.out.println(sum);
//        System.out.println();
        
        return sum;
    }

}
