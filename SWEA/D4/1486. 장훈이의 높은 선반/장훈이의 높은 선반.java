import java.io.*;
import java.util.*;

public class Solution {
    static int n, h, min;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total_test_case = Integer.parseInt(reader.readLine());

        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= total_test_case; test_case++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken()); // 사람의 수
            h = Integer.parseInt(tokenizer.nextToken()); // 필요한 높이
            
            arr = new int[n]; // [i]: i번째 사람의 키
            
            tokenizer = new StringTokenizer(reader.readLine());
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = Integer.parseInt(tokenizer.nextToken());
            }
            
            min = Integer.MAX_VALUE;
            
            find(0, 0);
            answer.append("#").append(test_case).append(" ").append(min - h).append("\n");
        }
        
        System.out.println(answer);
    }
    
    public static void find(int index, int sum) {
        if(sum >= min) return;
        
        if(sum >= h && sum < min){
            min = sum;
        }
        
        if(index == n) return;
        find(index + 1, sum + arr[index]);
        find(index + 1, sum);
    }
}
