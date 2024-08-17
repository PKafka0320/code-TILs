import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 10;
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            answer.append("#").append(n).append(" ");
            
            int[] numbers = new int[8];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            
            boolean isNotEnd = true;
            while (isNotEnd) {
                isNotEnd = cycle(numbers);
            }
            
            for (int i = 0; i < 8; i++) {
                answer.append(numbers[i]).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
    
    public static boolean cycle(int[] numbers) {
        boolean isEnd = false;
        for (int sub = 1; sub <= 5; sub++) {
            if (numbers[0] <= sub) {
                numbers[0] = 0;
                isEnd = true;
            } else {
                numbers[0] -= sub;
            }
            move(numbers);
            
            if (isEnd) break;
        }
        
        if (isEnd) {
            return false;
        }
        return true;
    }
    
    public static void move(int[] numbers) {
        int tmp = numbers[0];
        for (int i = 0; i < 7; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[7] = tmp;
    }
}
