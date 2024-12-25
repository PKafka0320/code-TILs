import java.io.*;
import java.util.*;

public class Main {
	static class Box implements Comparable<Box> {
		int start, end, amount;

		public Box(int start, int end, int amount) {
			this.start = start;
			this.end = end;
			this.amount = amount;
		}

		@Override
		public int compareTo(Box o) {
			if (this.end == o.end)
				return this.start - o.start;
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		Box[] boxs = new Box[M];
		int[] remainLoadable = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			
			boxs[i] = new Box(start, end, amount);
		}
		Arrays.fill(remainLoadable, C);
		
		Arrays.sort(boxs);
		
		int answer = 0;
		for (int i = 0; i < M; i++) {
			Box currentBox = boxs[i];
			
			int carriableAmount = currentBox.amount;
			for (int position = currentBox.start; position < currentBox.end; position++) {
				carriableAmount = Math.min(carriableAmount, remainLoadable[position]);
			}
			
			if (carriableAmount == 0) continue;
			
			for (int position = currentBox.start; position < currentBox.end; position++) {
				remainLoadable[position] -= carriableAmount;
			}
			
			answer += carriableAmount;
		}
		
		System.out.println(answer);
	}
}
