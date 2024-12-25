import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] gadget = new int[K];
		Queue<Integer>[] requireTime = new LinkedList[K + 1];
		int[] plug = new int[N];
		
		for (int i = 1; i <= K; i++) {
			requireTime[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			gadget[i] = Integer.parseInt(st.nextToken());
			requireTime[gadget[i]].add(i);
		}
		Arrays.fill(plug, -1);

		int gadgetIndex = 0;
		int plugCount = 0;
		while (plugCount < N && gadgetIndex < K) {
			boolean alreadyPlugged = false;
			
			for (int plugIndex = 0; plugIndex < N; plugIndex++) {
				if (plug[plugIndex] == gadget[gadgetIndex]) {
					alreadyPlugged = true;
					break;
				}
			}
			
			if (alreadyPlugged) {
				requireTime[gadget[gadgetIndex]].poll();
				gadgetIndex++;
				continue;
			}
			
			plug[plugCount] = gadget[gadgetIndex];
			requireTime[gadget[gadgetIndex]].poll();
			gadgetIndex++;
			plugCount++;
		}

		int answer = 0;
		while (gadgetIndex < K) {
			int gadgetNumber = gadget[gadgetIndex];
			
			boolean inPlug = false;
			for (int plugIndex = 0; plugIndex < N; plugIndex++) {
				if (plug[plugIndex] == gadgetNumber) {
					inPlug = true;
					break;
				}
			}
			
			if (inPlug) {
				requireTime[gadgetNumber].poll();
				gadgetIndex++;
				continue;
			}
			
			int changeIndex = -1;
			int maxTime = 0;
			for (int plugIndex = 0; plugIndex < N; plugIndex++) {
				if (requireTime[plug[plugIndex]].isEmpty()) {
					changeIndex = plugIndex;
					break;
				}
				
				int remainTime = requireTime[plug[plugIndex]].peek() - gadgetIndex;
				if (remainTime > maxTime) {
					maxTime = remainTime;
					changeIndex = plugIndex;
				}
			}
			
			requireTime[gadgetNumber].poll();
			plug[changeIndex] = gadget[gadgetIndex];
			gadgetIndex++;
			answer++;
		}
		
		System.out.println(answer);
	}
}
