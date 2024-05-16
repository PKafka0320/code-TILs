import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine()); // 지폐의 금액
        int k = Integer.parseInt(reader.readLine()); // 동전의 가지 수
        long[] cases = new long[T + 1]; // cases[i] : 금액 i를 만들 수 있는 경우의 수
        
        cases[0] = 1;
        for (int K = 0; K < k; K++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int coinPrice = Integer.parseInt(tokenizer.nextToken());
            int coinCount = Integer.parseInt(tokenizer.nextToken());
            
            // tmpPrice를 더해서 price를 만들 수 있는 경우의 수 계산
            for (int price = T; price >= 0; price--) {
                for (int multiplier = 1; multiplier <= coinCount; multiplier++) {
                    int tmpPrice = coinPrice * multiplier;
                    if (price < tmpPrice) break;
                    
                    cases[price] += cases[price - tmpPrice]; 
                }
            }
        }
        
        System.out.println(cases[T]);
    }
}
