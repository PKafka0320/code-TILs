import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
        int M = Integer.parseInt(tokenizer.nextToken()); // 키를 비교한 회수

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer>[] link = new ArrayList[N + 1]; // 연결
        int[] degree = new int[N + 1]; // 진입 차수
        
        for (int idx = 1; idx <= N; idx++) {
            link[idx] = new ArrayList<>(); 
        }
        
        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int front = Integer.parseInt(tokenizer.nextToken());
            int back = Integer.parseInt(tokenizer.nextToken());

            link[front].add(back);
            degree[back]++;
        }

        // 진입 차수가 0인 숫자 확인
        for (int idx = 1; idx <= N; idx++) {
            if (degree[idx] > 0) continue;
            queue.add(idx);
        }

        // 위상정렬
        StringBuilder answer = new StringBuilder();
        while (!queue.isEmpty()) {
            int number = queue.poll();
            answer.append(number).append(" ");
            for (int next : link[number]) {
                degree[next]--;
                if (degree[next] > 0) continue;
                queue.add(next);
            }
        }
        System.out.print(answer);
    }
}
