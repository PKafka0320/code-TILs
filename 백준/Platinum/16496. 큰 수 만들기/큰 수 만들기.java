import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static String[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		Arrays.sort(numbers, (o1, o2) -> {
			if (o1.length() == o2.length()) {
				for (int i = 0; i < o1.length(); i++) {
					if (o1.charAt(i) > o2.charAt(i))
						return -1;
					else if (o1.charAt(i) < o2.charAt(i))
						return 1;
				}
				return 0;
			} else {
				for (int i = 0; i < o1.length() + o2.length(); i++) {
					if (o1.charAt(i % o1.length()) > o2.charAt(i % o2.length())) return -1;
					else if (o1.charAt(i % o1.length()) < o2.charAt(i % o2.length())) return 1;
				}
				return 0;
			}
		});

		StringBuilder answer = new StringBuilder();
		boolean isStart = true;
		for (String number : numbers) {
			if (isStart && number.equals("0")) continue;
			isStart = false;
			answer.append(number);
		}
		if (answer.length() == 0) {
			answer.append("0");
		}
		System.out.println(answer);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = new String[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = st.nextToken();
		}
	}
}