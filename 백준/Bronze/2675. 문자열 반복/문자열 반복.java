import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int count = Integer.parseInt(tokenizer.nextToken());
            String string = tokenizer.nextToken();
            
            for (char ch : string.toCharArray()) {
                for (int c = 0; c < count; c++) {
                    answer.append(ch);
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
