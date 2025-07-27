import java.io.*;
import java.util.*;

public class Main {
    static int x, n;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder answer = new StringBuilder();
        while ((input = reader.readLine()) != null) {
            x = Integer.parseInt(input) * 10_000_000; // 구멍의 너비
            n = Integer.parseInt(reader.readLine()); // 레고 조각의 수

            int[] blocks = new int[n];
            for (int i = 0; i < n; i++) {
                blocks[i] = Integer.parseInt(reader.readLine());
            }

            Arrays.sort(blocks); // 정렬

            int left = 0;
            int right = n - 1;
            int block1 = -1;
            int block2 = -1;

            while (left < right) {
                int sum = blocks[left] + blocks[right];

                if (sum == x) {
                    block1 = blocks[left];
                    block2 = blocks[right];
                    break;
                } else if (sum < x) {
                    left++;
                } else {
                    right--;
                }
            }

            if (block1 != -1 && block2 != -1) {
                answer.append("yes ").append(block1).append(" ").append(block2).append("\n");
            } else {
                answer.append("danger\n");
            }
        }

        System.out.print(answer);
    }
}
