import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            boolean isOn = true;
            for (int i = 0; i < n; i++) {
                if ((m & 1 << i) == 0) {
                    isOn = false;
                    break;
                }
            }
            
            answer.append(isOn ? "ON" : "OFF").append("\n");
        }

        System.out.println(answer);
    }
}
