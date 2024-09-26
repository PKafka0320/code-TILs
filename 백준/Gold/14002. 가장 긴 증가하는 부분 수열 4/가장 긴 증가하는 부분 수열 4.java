import java.io.*;
import java.util.*;

public class Main {
	static int N, numbers[], lis[], len[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N+1];
		lis = new int[N+1];
		len = new int[N+1];
		
		Arrays.fill(lis, Integer.MAX_VALUE);
		int maxLen = 1;
		
		st = new StringTokenizer(br.readLine());
		numbers[1] = Integer.parseInt(st.nextToken());
		lis[1] = numbers[1];
		len[1] = 1;
		for (int i = 2; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
			
			if (lis[maxLen] < num) {
				maxLen++;
				lis[maxLen] = num;
				len[i] = maxLen;
			} else {
				int insertIdx = getIndex(1, maxLen, num);
				lis[insertIdx] = num;
				len[i] = insertIdx;
			}
		}
		
		
		System.out.println(maxLen);
		int[] resultNumbers = new int[maxLen+1];
		for (int i = N; i >= 1; i--) {
			if (len[i] == maxLen) {
				resultNumbers[maxLen] = numbers[i];
				maxLen--;
			}
		}
		for (int i = 1; i < resultNumbers.length; i++) {
			System.out.printf("%d ", resultNumbers[i]);
		}
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
