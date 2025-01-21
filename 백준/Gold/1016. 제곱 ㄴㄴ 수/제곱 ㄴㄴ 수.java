import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long MIN = Long.parseLong(st.nextToken());
        long MAX = Long.parseLong(st.nextToken());
        long size = MAX - MIN + 1;
        boolean[] notPrime = new boolean[(int) size];
        long answer = size;

        for (long i = 2; i * i <= MAX; i++) {
            long power = i * i;
            long temp = MIN / power;

            if (MIN % power != 0) {
                temp += 1;
            }

            for (long j = temp; j * power <= MAX; j++) {
                int offset = (int) (j * power - MIN);
                if (!notPrime[offset]) {
                    notPrime[offset] = true;
                    answer--;
                }
            }
        }

        System.out.println(answer);
    }
}
