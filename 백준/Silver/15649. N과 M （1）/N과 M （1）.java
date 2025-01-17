import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result[];
    static StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N =  Integer.parseInt(st.nextToken());
        M =  Integer.parseInt(st.nextToken());
        result = new int[M];
        
        dfs(0, new boolean[N+1]);
        System.out.println(answer.toString());
    }
    
    static void dfs(int index, boolean[] flag) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                answer.append(result[i]).append(" ");
            }
            answer.append("\n");
            return;
        }
        
        for (int number = 1; number <= N; number++) {
            if (flag[number]) continue;
            
            flag[number] = true;
            result[index] = number;
            
            dfs(index + 1, flag);
            
            flag[number] = false;
        }
    }
}