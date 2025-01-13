import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] numbers = new long[K];
        Queue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            pq.add(numbers[i]);
        }

        long answer = 0;
        while (N-- > 0) {
            answer = pq.poll();

            for (int i = 0; i < K; i++) {
                long temp = answer * numbers[i];
                if (temp >= ((long) 1 << 31)) {
                    break;
                }

                pq.add(temp);

                if (answer % numbers[i] == 0) {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
