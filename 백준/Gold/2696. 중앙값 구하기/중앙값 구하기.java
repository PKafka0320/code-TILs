import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int T, M;
	static ArrayList<Long> numbers;
	static PriorityQueue<Long> left, right;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		answer = new StringBuilder();
		while (T-- > 0) {
			init();
			solve();
		}
		System.out.println(answer);
	}

	private static void solve() {
		ArrayList<Long> result = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			long number = numbers.get(i);

			if (left.isEmpty() || left.peek() < number) {
				right.add(number);
			} else {
				left.add(number);
			}

			if (left.size() > right.size() + 1) {
				right.add(left.poll());
			} else if (left.size() < right.size()) {
				left.add(right.poll());
			}

			if (i % 2 == 0) {
				result.add(left.peek());
			}
		}

		answer.append(result.size()).append("\n");
		for (int i = 0; i < result.size(); i++) {
			answer.append(result.get(i)).append(" ");
			if (i % 10 == 9) {
				answer.append("\n");
			}
		}
		answer.append("\n");
	}

	private static void init() throws IOException {
		M = Integer.parseInt(br.readLine());

		numbers = new ArrayList<>();
		left = new PriorityQueue<>((Comparator.reverseOrder()));
		right = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			long number = Long.parseLong(st.nextToken());
			numbers.add(number);
			if (i % 10 == 9) {
				st = new StringTokenizer(br.readLine());
			}
		}
	}
}
