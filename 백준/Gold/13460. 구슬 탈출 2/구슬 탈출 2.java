import java.io.*;
import java.util.*;

class Position {
    int rr, rc, br, bc, dir, count;

    public Position(int rr, int rc, int br, int bc, int dir, int count) {
        this.rr = rr;
        this.rc = rc;
        this.br = br;
        this.bc = bc;
        this.dir = dir;
        this.count = count;
    }
}

public class Main {
    // 상좌하우
    static int[] dr = new int[] { -1, 0, 1, 0 };
    static int[] dc = new int[] { 0, -1, 0, 1 };
    static int[][] grid;
    static boolean[][][][] visited;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m]; // -1: wall
        visited = new boolean[n][m][n][m]; // [i][j][k][l]: 빨간 구슬이 i,j, 파란 구슬이 k,l 위치로 왔는지 확인

        int rr = -1;
        int rc = -1;
        int br = -1;
        int bc = -1;
        int gr = -1;
        int gc = -1;

        // 보드 생성
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();

            for (int j = 0; j < m; j++) {
                char tile = line.charAt(j);

                if (tile == '#') {
                    grid[i][j] = -1;
                } else if (tile == 'R') {
                    rr = i;
                    rc = j;
                } else if (tile == 'B') {
                    br = i;
                    bc = j;
                } else if (tile == 'O') {
                    gr = i;
                    gc = j;
                }
            }
        }

        int result = bfs(rr, rc, br, bc, gr, gc);
        System.out.println(result);
    }
    
    public static int[] nextPosition(int r, int c, int dir, int gr, int gc) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        while (grid[nr][nc] == 0) {
            r = nr;
            c = nc;
            
            if (r == gr && c == gc) {
                break;
            }
            
            nr = r + dr[dir];
            nc = c + dc[dir];
        }
        
        return new int[] {r, c};
    }

    public static int bfs(int rr, int rc, int br, int bc, int gr, int gc) {
        Queue<Position> pq = new LinkedList<>();

        for (int d = 0; d < 4; d++) {
            pq.add(new Position(rr, rc, br, bc, d, 1));
            visited[rr][rc][br][bc] = true;
        }

        while (!pq.isEmpty()) {
            Position currentPosition = pq.poll();
            int crr = currentPosition.rr;
            int crc = currentPosition.rc;
            int cbr = currentPosition.br;
            int cbc = currentPosition.bc;
            int cdir = currentPosition.dir;
            int ccount = currentPosition.count;

            if (ccount > 10)
                return -1;

            // 구슬 이동
            int[] nrp = nextPosition(crr, crc, cdir, gr, gc);
            int[] nbp = nextPosition(cbr, cbc, cdir, gr, gc);
            int nrr = nrp[0];
            int nrc = nrp[1];
            int nbr = nbp[0];
            int nbc = nbp[1];
            
            // 파란 공이 구멍에 도착한다면 실패
            if (nbr == gr && nbc == gc) continue;
            
            // 빨간 공이 구멍에 도착한다면 성공
            if (nrr == gr && nrc == gc) return ccount;

            // 겹치는 경우 위치 조정 (상좌하우)
            if (nrr == nbr && nrc == nbc) {
                if (cdir == 0) {
                    if (crr > cbr) {
                        nrr += 1;
                    } else {
                        nbr += 1;
                    }
                } else if (cdir == 1) {
                    if (crc > cbc) {
                        nrc += 1;
                    } else {
                        nbc += 1;
                    }
                } else if (cdir == 2) {
                    if (crr > cbr) {
                        nbr -= 1;
                    } else {
                        nrr -= 1;
                    }
                } else if (cdir == 3) {
                    if (crc > cbc) {
                        nbc -= 1;
                    } else {
                        nrc -= 1;
                    }
                }
            }
            
            // 동일한 결과가 나오지 않았다면 다른 방향으로 이동 계획 추가
            if (visited[nrr][nrc][nbr][nbc]) continue;
            for (int ndir = 0; ndir < 4; ndir++) {
                if (ndir == cdir) continue;
                pq.add(new Position(nrr, nrc, nbr, nbc, ndir, ccount + 1));
            }
        }

        return -1;
    }
}
