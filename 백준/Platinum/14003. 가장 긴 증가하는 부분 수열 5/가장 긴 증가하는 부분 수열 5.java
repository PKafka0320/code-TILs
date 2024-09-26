import java.io.*;
import java.util.*;

public class Main {
	static int N, numbers[], lis[], beforeIdx[], currentNumberIdx[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		lis = new int[N+1];
		beforeIdx = new int[N+1];
		currentNumberIdx = new int[N+1];
		int maxLen = 1;
		int maxIdx = 1;
		
		st = new StringTokenizer(br.readLine());
		numbers[1] = Integer.parseInt(st.nextToken());
		lis[1] = numbers[1];
		currentNumberIdx[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
			
			if (lis[maxLen] < num) {
				maxIdx = i;
				maxLen++;
				lis[maxLen] = num;
				currentNumberIdx[maxLen] = i;
				beforeIdx[i] = currentNumberIdx[maxLen-1];
			} else {
				int insertIdx = getIndex(1, maxLen, num);
				lis[insertIdx] = num;
				currentNumberIdx[insertIdx] = i;
				beforeIdx[i] = currentNumberIdx[insertIdx-1];
			}
		}
		
		System.out.println(maxLen);
		int[] resultNumbers = new int[maxLen+1];
		for (int i = maxLen; i >= 1; i--) {
			resultNumbers[i] = numbers[maxIdx];
			maxIdx = beforeIdx[maxIdx];
		}
		
        StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= maxLen; i++) {
            answer.append(resultNumbers[i]).append(" ");
		}
        System.out.println(answer);
		
	}
	
	public static int getIndex(int min, int max, int num) {
		while (min < max) {
			int mid = (min + max) / 2;
			
			if (lis[mid] == num) {
				return mid;
			} else if (lis[mid] < num) {
				min = mid + 1;
			} else {
				max = mid;
			}
		}

		return min;
	}

}
