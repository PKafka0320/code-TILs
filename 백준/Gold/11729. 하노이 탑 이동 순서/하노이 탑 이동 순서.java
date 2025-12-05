import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder log = new StringBuilder();
	static StringTokenizer st;
	static int K, count;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		recurse(K, 1, 3);
		System.out.println(count);
		System.out.println(log);
	}

	private static void recurse(int number, int from, int to) {
		if (number <= 0) {
			return;
		}
		int mid = 6 - from - to;
		recurse(number - 1, from, mid);
		log.append(from).append(" ").append(to).append("\n");
		count++;
		recurse(number - 1, mid, to);
	}

	private static void init() throws IOException {
		K = Integer.parseInt(br.readLine());
	}
}
