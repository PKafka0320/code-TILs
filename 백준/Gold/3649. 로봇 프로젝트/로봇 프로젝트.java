import java.io.*;
import java.util.*;

public class Main {
    static int x, n;
    static HashSet<Integer> blocks;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder answer = new StringBuilder();
        while ((input = reader.readLine()) != null) {
            x = Integer.parseInt(input) * 10_000_000; // 구멍의 너비
            n = Integer.parseInt(reader.readLine()); // 레고 조각의 수
            
            blocks = new HashSet<>(); // 레고 조각의 길이와 개수
            int maxDiff = -1; // 두 레고의 최대 길이 차이
            int block1 = -1; // 짧은 레고의 길이
            int block2 = -1; // 긴 레고의 길이
            for (int idx = 0; idx < n; idx++) {
                int block = Integer.parseInt(reader.readLine());
                
                int diff = x - block;
                if (maxDiff >= Math.max(diff, block) - Math.min(diff, block)) continue; // 길이 차이 비교

                if (blocks.contains(diff)) {
                    block1 = Math.min(diff, block);
                    block2 = Math.max(diff, block);
                    maxDiff = block2 - block1;
                }
                
                blocks.add(block); // 레고 추가
            }
            
            if (block1 != -1 && block2 != -1) {
                answer.append("yes ").append(block1).append(" ").append(block2).append("\n");
            }
            else {
                answer.append("danger\n");
            }
        }
        System.out.println(answer);
    }
}
