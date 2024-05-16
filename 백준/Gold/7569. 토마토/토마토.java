import java.io.*;
import java.util.*;

class Apple {
    int h, n, m;
    
    public Apple(int h, int n, int m) {
        this.h = h;
        this.n = n;
        this.m = m;
    }
    
    @Override
    public String toString() {
        return "[" + this.h + "," + this.n + "," + this.m  + "]";
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int M = Integer.parseInt(tokenizer.nextToken()); // 상자의 가로 칸의 수
        int N = Integer.parseInt(tokenizer.nextToken()); // 상자의 세로 칸의 수
        int H = Integer.parseInt(tokenizer.nextToken()); // 쌓아올려지는 상자의 수
        
        int[][][] apples = new int[H][N][M]; // apples[i][j][k] : i번째 상자의 (j,k) 위치의 사과
        boolean[][][] visited = new boolean[H][N][M]; // visited[i][j][k] : i번째 상자의 (j,k) 위치의 사과 확인 여부
        int unripeAppleCount = 0; // 안익은 사과의 수
        Queue<Apple> currentRipedApple = new LinkedList<>();
        Queue<Apple> nextRipedApple = new LinkedList<>();
        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int m = 0; m < M; m++) {
                    apples[h][n][m] = Integer.parseInt(tokenizer.nextToken());
                    if (apples[h][n][m] == 0) unripeAppleCount++;
                    else if (apples[h][n][m] == 1) currentRipedApple.add(new Apple(h, n, m));
                }
            }
        }
        
        int answer = 0;
        
        // 처음부터 모두 익어있는 경우
        if (unripeAppleCount == 0) {
            System.out.println(answer);
            System.exit(0);
        }
        
        // BFS
        int[] dh = {0, 0, 0, 0, 1, -1};
        int[] dn = {0, 0, 1, -1, 0, 0};
        int[] dm = {1, -1, 0, 0, 0, 0};
        while (!currentRipedApple.isEmpty()) {
            Apple apple = currentRipedApple.poll();
            int h = apple.h;
            int n = apple.n;
            int m = apple.m;
            visited[h][n][m]= true;

            // 다음에 익을 사과 확인
            for (int dir = 0; dir < 6; dir++) {
                int nh = h + dh[dir];
                int nn = n + dn[dir];
                int nm = m + dm[dir];
                
                if (nn < 0 || nn >= N || nm < 0 || nm >= M || nh < 0 || nh >= H) continue;
                if (apples[nh][nn][nm] != 0 || visited[nh][nn][nm]) continue;

                visited[nh][nn][nm] = true;
                unripeAppleCount--;
                nextRipedApple.add(new Apple(nh, nn, nm));
            }
            
            // 다음에 익을 사과가 있을 경우 일수 증가 및 사과 목록 갱신
            if (currentRipedApple.isEmpty() && !nextRipedApple.isEmpty()) {
                answer++;
                currentRipedApple = new LinkedList<Apple>(nextRipedApple);
                nextRipedApple.clear();
            }
        }
        
        if (unripeAppleCount > 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }
    }
}
