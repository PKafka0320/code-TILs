import java.io.*;
import java.util.*;

public class Solution {
	static List<Integer>[] lock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			int K = Integer.parseInt(br.readLine());
			lock = new ArrayList[4];
			
			for (int i = 0; i < 4; i++) {
				lock[i] = new ArrayList<>();
				
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					int num = Integer.parseInt(st.nextToken());
					lock[i].add(num);
				}
				
			}
			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				
				rotate(idx, dir, -1);
			}
			
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				answer += lock[i].get(0) == 1 ? 1<<i : 0;
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void rotate(int idx, int dir, int beforeIdx) {
		if (idx > 0 && beforeIdx != idx-1 && lock[idx].get(6) != lock[idx-1].get(2)) {
			rotate(idx-1, dir * -1, idx);
		}
		if (idx < 3 && beforeIdx != idx+1 && lock[idx].get(2) != lock[idx+1].get(6)) {
			rotate(idx+1, dir * -1, idx);
		}
		
		if (dir == 1) {
			lock[idx].add(0, lock[idx].remove(lock[idx].size()-1));
		} else {
			lock[idx].add(lock[idx].remove(0));
		}
	}

}