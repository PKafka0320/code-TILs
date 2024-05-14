import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] numbers;
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 숫자 개수
        int K = Integer.parseInt(tokenizer.nextToken()); // 그룹 개수
        
        numbers = new int[N]; // 숫자
        int sum = 0; // 모든 숫자의 합
        
        // 숫자 배열 생성 및 총합 계산
        tokenizer = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < N; idx++) {
            numbers[idx]= Integer.parseInt(tokenizer.nextToken()); 
            sum += numbers[idx];
        }
        
        // 그룹의 합의 최솟값을 이분탐색
        int low = 0;
        int high = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            int groupSum = 0; // 그룹의 합
            int groupCnt = 0; // 그룹의 개수
            
            // 합을 계산하면서 설정한 최솟값을 넘길 경우 그룹으로 분리
            for (int idx = 0; idx < N; idx++) {
                groupSum += numbers[idx];
                
                if(groupSum >= mid) {
                    groupCnt++;
                    groupSum = 0;
                }
            }
            
            if (groupCnt >= K) {
                low = mid + 1;
            }
            else high = mid - 1;
        }
        
        System.out.println(high);
    }
}
