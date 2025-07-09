import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] indegree, counter;
    static List<Integer>[] nextJobs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        counter = new int[N + 1];
        nextJobs = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            nextJobs[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nextJobs[a].add(b);
            indegree[b]++;
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N * K; i++) {
            int job = Integer.parseInt(st.nextToken());
            answer.append(++counter[job]).append(" ");
        }
        System.out.println(answer);
    }
}