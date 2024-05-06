import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            
            HashSet<Integer> hs = new HashSet<Integer>();
            hs.add(0);
            
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int num = Integer.parseInt(st.nextToken());
                
                for (int cur : new HashSet<Integer>(hs)) hs.add(cur + num);
            }
            
            answer.append("#").append(test_case).append(" ").append(hs.size()).append("\n");
        }

        System.out.println(answer);
    }
}
