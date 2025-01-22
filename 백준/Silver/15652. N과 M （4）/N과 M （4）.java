import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result[];
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N =  Integer.parseInt(st.nextToken());
        M =  Integer.parseInt(st.nextToken());
        result = new int[M];

        dfs(0, 1);
        System.out.println(answer.toString());
    }

    static void dfs(int index, int start) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                answer.append(result[i]).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int number = start; number <= N; number++) {
            result[index] = number;
            dfs(index + 1, number);
        }
    }
}