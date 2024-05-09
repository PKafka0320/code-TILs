import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    static int R, C, dir;
    static boolean tmpAns;
    static String ans;
    static char[][] charArr;
    static boolean[][][][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 1;
 
        while (t <= T) {
            String[] arr = br.readLine().split(" ");
            R = Integer.parseInt(arr[0]);
            C = Integer.parseInt(arr[1]);
            charArr = new char[R][C];
            visited = new boolean[R][C][4][16];
 
            for (int r = 0; r < R; r++) {
                String str = br.readLine();
                charArr[r] = str.toCharArray();
            }
 
            ans = "NO";
            tmpAns = false;
 
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (charArr[r][c] == '@') {
                        tmpAns = true;
                        break;
                    }
                }
            }
 
            if (!tmpAns) {
                System.out.println("#" + t + " " + ans);
                t++;
                continue;
            }
             
            dir = 3;
            dfs(0, 0, 0);
 
            System.out.println("#" + t + " " + ans);
            t++;
        }
    }
 
    public static void dfs(int r_idx, int c_idx, int memory) {
        if (ans == "YES") {
            return;
        }
 
        if (r_idx == R) {
            r_idx = 0;
        } else if (r_idx == -1) {
            r_idx = R - 1;
        }
        if (c_idx == C) {
            c_idx = 0;
        } else if (c_idx == -1) {
            c_idx = C - 1;
        }
 
        if (visited[r_idx][c_idx][dir][memory])
            return;
 
        visited[r_idx][c_idx][dir][memory] = true;
 
        switch (charArr[r_idx][c_idx]) {
        case '<':
            dir = 2;
            break;
        case '>':
            dir = 3;
            break;
        case '^':
            dir = 0;
            break;
        case 'v':
            dir = 1;
            break;
        case '_':
            if (memory == 0)
                dir = 3;
            else
                dir = 2;
            break;
        case '|':
            if (memory == 0)
                dir = 1;
            else
                dir = 0;
            break;
        case '?':
            for (int i = 0; i < 4; i++) {
                dir = i;
                dfs(r_idx + dr[i], c_idx + dc[i], memory);
            }
            return;
        case '.':
            break;
        case '@':
            ans = "YES";
            return;
        case '+':
            if (memory == 15)
                memory = 0;
            else
                memory += 1;
            break;
        case '-':
            if (memory == 0)
                memory = 15;
            else
                memory -= 1;
            break;
        default:
            memory = charArr[r_idx][c_idx] - '0';
        }
 
        dfs(r_idx + dr[dir], c_idx + dc[dir], memory);
    }
}