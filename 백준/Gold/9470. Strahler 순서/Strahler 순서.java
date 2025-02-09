import java.io.*;
import java.util.*;

public class Main {
    static int T, K, M, P;
    static int[] inDegree, number;
    static boolean[] isDouble;
    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            inDegree = new int[M+1];

            number = new int[M+1];
            Arrays.fill(number, -1);

            isDouble = new boolean[M+1];

            edges = new ArrayList[M+1];
            for (int i = 1; i <= M; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                edges[from].add(to);
                inDegree[to]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= M; i++) {
                if (inDegree[i] == 0) {
                    number[i] = 1;
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (isDouble[current]) {
                    number[current]++;
                }
                
                for (int next : edges[current]) {
                    if (number[next] == number[current]) {
                        isDouble[next] = true;
                    } else if (number[next] < number[current]) {
                        isDouble[next] = false;
                        number[next] = number[current];
                    }

                    if (--inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            answer.append(K).append(" ").append(number[M]).append("\n");
        }
        System.out.println(answer.toString());
    }
}