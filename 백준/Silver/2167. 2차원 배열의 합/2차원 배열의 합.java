import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sum = new int[N+1][M+1];
        sum[1][1] = grid[1][1];
        for (int i = 2; i <= M; i++) {
            sum[1][i] = sum[1][i-1] + grid[1][i];
        }
        for (int i = 2; i <= N; i++) {
            sum[i][1] = sum[i-1][1] + grid[i][1];
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= M; j++) {
                sum[i][j] = grid[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int result = sum[x][y] - sum[x][j-1] - sum[i-1][y] + sum[i-1][j-1];
            answer.append(result).append("\n");
        }
        System.out.println(answer);
    }
}
