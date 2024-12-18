import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static long number[], tree[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		number = new long[N+1];
		tree = new long[4*(N+1)];
		for (int i = 1; i <= N; i++) {
			number[i] = Long.parseLong(br.readLine());
		}
		
		init(1, N, 1);
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				long dif = c - number[b];
				update(1, N, 1, b, dif);
				number[b] = c;
			} else {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if (b <= c) {
					answer.append(sum(1, N, 1, b, c)).append("\n");
				} else {
					answer.append(sum(1, N, 1, c, b)).append("\n");
				}
			}
		}
		System.out.println(answer.toString());
	}
	
	public static void update(int start, int end, int node, int index, long dif) {
		if (index < start || index > end) return;
		tree[node] += dif;
		if (start == end) return;
		
		int mid = (start + end) / 2;
		update(start, mid, node*2, index, dif);
		update(mid+1, end, node*2 + 1, index, dif);
	}
	
	public static long sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) return 0;
		if (left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2 + 1, left, right);
	}
	
	public static long init(int start, int end, int node) {
		if (start == end) return tree[node] = number[start];
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2 + 1);
	}
}
