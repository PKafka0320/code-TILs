import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int answer = 0;
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < N) {
            sum += numbers[right];
            while (right - left + 1 > X) {
                sum -= numbers[left];
                left++;
            }

            if (answer < sum) {
                answer = sum;
                count = 1;
            } else if (answer == sum) {
                count++;
            }
            right++;
        }

        if (answer == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(answer);
            System.out.println(count);
        }
    }
}