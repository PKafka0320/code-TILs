import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 숫자 개수
        int[] numbers = new int[N]; // 숫자
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = Integer.parseInt(tokenizer.nextToken()); 
        }
        
        int max = Arrays.stream(numbers).max().getAsInt();
        int min = Arrays.stream(numbers).min().getAsInt();
        
        System.out.println(min + " " + max);
    }
}
