import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Position implements Comparable<Position> {
        int row, col, time;

        public Position(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Position o) {
            return this.time - o.time;
        }
    }
    static int INF = (int) 1e9;
    static int N, M, T, D;
    static int[][] map;
    static int[][][][] dist;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M][N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < M; l++) {
                        dist[i][j][k][l] = INF;
                    }
                }
            }
        }
        for (int r = 0; r < N; r++) {
            String line = br.readLine();

            for (int c = 0; c < M; c++) {
                map[r][c] = toInt(line.charAt(c));
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                dijkstra(r, c);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[i][j][0][0] + dist[0][0][i][j] <= D) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
        }
        System.out.println(answer);
    }

    public static void dijkstra(int row, int col) {
        Queue<Position> q = new PriorityQueue<>();
        dist[row][col][row][col] = 0;
        q.add(new Position(row, col, 0));

        while (!q.isEmpty()) {
            Position current = q.poll();
            int cr = current.row;
            int cc = current.col;
            int ct = current.time;

            if (dist[cr][cc][row][col] < ct) continue;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cr + dr[dir];
                int nc = cc + dc[dir];
                int nt;

                if (outOfMap(nr, nc)) continue;
                if (Math.abs(map[cr][cc] - map[nr][nc]) > T) continue;

                if (map[nr][nc] <= map[cr][cc]) {
                    nt = ct + 1;
                } else {
                    nt = (int) Math.pow(map[nr][nc] - map[cr][cc], 2) + ct;
                }

                if (dist[nr][nc][row][col] > nt && nt <= D) {
                    dist[nr][nc][row][col] = nt;
                    q.add(new Position(nr, nc, nt));
                }
            }
        }
    }

    public static boolean outOfMap(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= M);
    }

    public static int toInt(char c) {
        if ('a' <= c && c <= 'z') {
            return c - 'a' + 26;
        }
        return c - 'A';
    }
}