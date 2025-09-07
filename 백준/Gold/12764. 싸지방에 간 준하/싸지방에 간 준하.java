import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class User implements Comparable<User> {
		int start, end, number;

		public User(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(User o) {
			return this.start - o.start;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, maxPcNumber;
	static HashMap<Integer, Integer> userCount;
	static PriorityQueue<User> users, currentUsers;
	static PriorityQueue<Integer> emptyPcs;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		while (!users.isEmpty()) {
			User user = users.poll();
			int time = user.start;
			// System.out.printf("current time : %d\n", time);

			while (!currentUsers.isEmpty() && currentUsers.peek().end <= time) {
				User poll = currentUsers.poll();
				emptyPcs.add(poll.number);
				// System.out.printf("pc %d is empty\n", poll.number);
			}

			if (emptyPcs.isEmpty()) {
				emptyPcs.add(++maxPcNumber);
				userCount.put(maxPcNumber, 0);
				// System.out.printf("no empty pc, create new : %d\n", maxPcNumber);
			}

			Integer pcNumber = emptyPcs.poll();
			userCount.put(pcNumber, userCount.get(pcNumber) + 1);
			user.number = pcNumber;
			currentUsers.add(user);
			// System.out.printf("pc %d is now using\n", pcNumber);
			// System.out.println();
		}

		StringBuilder answer = new StringBuilder();
		answer.append(maxPcNumber).append("\n");
		for (int number = 1; number <= maxPcNumber; number++) {
			answer.append(userCount.get(number)).append(" ");
		}
		System.out.println(answer);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		users = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			users.add(new User(p, q));
		}

		maxPcNumber = 0;
		userCount = new HashMap<>();
		currentUsers = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
		emptyPcs = new PriorityQueue<>();
	}
}