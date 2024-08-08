import java.io.*;
import java.util.*;

class Info {
	int time ,value;
	
	public Info(int time, int value) {
		this.time = time;
		this.value = value;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int currentTime = 0;
		
		PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				if (o1.time == o2.time) return o2.value - o1.value;
				return o2.time - o1.time;
			
			}
		});
		PriorityQueue<Info> tmppq = new PriorityQueue<>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				return o2.value - o1.value;
			}
		});
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			pq.add(new Info(time, value));
			currentTime = Math.max(currentTime, time);
		}
		
		int sum = 0;
		while (currentTime > 0 ) {
//			System.out.println("currentTime: " + currentTime);
			while (!pq.isEmpty() && pq.peek().time >= currentTime) {
				Info info = pq.poll();
				int time = info.time;
				int value = info.value;
//				System.out.printf("addList | (%d,%d)\n", time, value);
				tmppq.add(info);
			}
			
			currentTime--;
			if (tmppq.isEmpty()) continue;
			Info peek = tmppq.poll();
			int time = peek.time;
			int value = peek.value;
//			System.out.printf("peek | (%d,%d)\n", time, value);
			sum += value;
			
//			System.out.println();
		}
		
		System.out.println(sum);
	}

}
