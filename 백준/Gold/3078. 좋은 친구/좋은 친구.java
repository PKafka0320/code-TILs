import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static String[] names;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		long result = 0;
		HashMap<Integer, Integer> lengthCount = new HashMap<>();

		for (int i = 0; i <= K; i++) {
			int length = names[i].length();
			lengthCount.put(length, lengthCount.getOrDefault(length, 0) + 1);
		}

		for (int i = 0; i < N; i++) {
			int length = names[i].length();
			result += lengthCount.getOrDefault(length, 0) - 1;

			lengthCount.put(length, lengthCount.get(length) - 1);
			int nextIndex = i + K + 1;
			if (nextIndex < N) {
				int nextLength = names[nextIndex].length();
				lengthCount.put(nextLength, lengthCount.getOrDefault(nextLength, 0) + 1);
			}
		}

		System.out.println(result);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		names = new String[N];

		for (int i = 0; i < N; i++) {
			names[i] = br.readLine();
		}
	}
}
