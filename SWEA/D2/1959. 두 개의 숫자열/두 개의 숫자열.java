import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total_test_case = Integer.parseInt(reader.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken()); // 숫자열 A의 길이
            int m = Integer.parseInt(tokenizer.nextToken()); // 숫자열 B의 길이
            
            int[] a = new int[n]; // [i]: 숫자열 A의 i번째 숫자
            int[] b = new int[m]; // [i]: 숫자열 B의 i번째 숫자
            
            tokenizer = new StringTokenizer(reader.readLine());
            for (int idx = 0; idx < n; idx++) {
                a[idx] = Integer.parseInt(tokenizer.nextToken());
            }
            
            tokenizer = new StringTokenizer(reader.readLine());
            for (int idx = 0; idx < m; idx++) {
                b[idx] = Integer.parseInt(tokenizer.nextToken());
            }
            
            int sum = 0;
            if (n > m) {
                for (int startIdx = 0; startIdx < n - m + 1; startIdx++) {
                    int tmpSum = 0;
                    for (int offset = 0; offset < m; offset++) {
                        tmpSum += a[startIdx + offset] * b[offset];
                    }
                    sum = Math.max(sum, tmpSum);
                }
            }
            else {
                for (int startIdx = 0; startIdx < m - n + 1; startIdx++) {
                    int tmpSum = 0;
                    for (int offset = 0; offset < n; offset++) {
                        tmpSum += a[offset] * b[startIdx + offset];
                    }
                    sum = Math.max(sum, tmpSum);
                }
            }
            
            answer.append("#").append(test_case).append(" ").append(sum).append("\n");
        }
        System.out.println(answer);
    }
}
