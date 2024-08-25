import java.io.*;
import java.util.*;

public class Solution {
    static char[][] map;
    static int cr, cc, cdir, H, W;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            // 현재 탱크의 좌표와 방향
            cr = -1;
            cc = -1;
            cdir = -1;
            
            map = new char[H][W];
            for (int r = 0; r < H; r++) {
                String line = br.readLine();
                for (int c = 0; c < W; c++) {
                    map[r][c] = line.charAt(c);
                    
                    if (map[r][c] == '^') {
                        cr = r;
                        cc = c;
                        cdir = 0;
                    } else if (map[r][c] == 'v') {
                        cr = r;
                        cc = c;
                        cdir = 1;
                    } else if (map[r][c] == '<') {
                        cr = r;
                        cc = c;
                        cdir = 2;
                    } else if (map[r][c] == '>') {
                        cr = r;
                        cc = c;
                        cdir = 3;
                    }
                }
            }
            
            // 명령어 입력 및 처리
            int N = Integer.parseInt(br.readLine());
            String ops = br.readLine();
            for (int i = 0; i < N; i++) {
                char op = ops.charAt(i);
                
                if (op == 'S') {
                    shoot();
                } else if (op == 'U') {
                    move(0);
                } else if (op == 'D') {
                    move(1);
                } else if (op == 'L') {
                    move(2);
                } else if (op == 'R') {
                    move(3);
                }
            }
            
            // 결과 출력
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    answer.append(map[r][c]);
                }
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }
    
    public static void move(int dir) {
        // 방향 전환
        if (dir == 0) {
            map[cr][cc] = '^';
        } else if (dir == 1) {
            map[cr][cc] = 'v';
        } else if (dir == 2) {
            map[cr][cc] = '<';
        } else if (dir == 3) {
            map[cr][cc] = '>';
        }
        cdir = dir;
        
        // 이동
        int nr = cr + dr[cdir];
        int nc = cc + dc[cdir];
        if (inBoard(nr, nc) && canMove(nr, nc)) {
            map[nr][nc] = map[cr][cc];
            map[cr][cc] = '.';
            cr = nr;
            cc = nc;
        }
    }
    
    public static void shoot() {
        // 포탄의 위치
        int nr = cr + dr[cdir];
        int nc = cc + dc[cdir];
        
        // 벽을 만나거나 맵 밖으로 나갈때까지 이동
        while (inBoard(nr, nc)) {
            //  벽일때 이벤트 처리
            if (map[nr][nc] == '*') {
                map[nr][nc] = '.';
                break;
            } else if (map[nr][nc] == '#') {
                break;
            }
            
            nr += dr[cdir];
            nc += dc[cdir];
        }
        
    }
    
    public static boolean canMove(int r, int c) {
        return (map[r][c] == '.');
    }
    
    // 유효한 좌표인지 확인
    public static boolean inBoard(int r, int c) {
        return (r >= 0 && r < H && c >= 0 && c < W);
    }
    
}