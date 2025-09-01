import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student> {
		int classNumber, skill;

		public Student(int classNumber, int skill) {
			this.classNumber = classNumber;
			this.skill = skill;
		}

		@Override
		public int compareTo(Student o) {
			return this.skill - o.skill; // 오름차순
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PriorityQueue<Student> pq;
	static PriorityQueue<Student> minPQ;
	static PriorityQueue<Student> maxPQ;
	static int N, M, presentCount;
	static int[] classSkill;
	static boolean[] isNotPresent;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int answer = 1_000_000_000;

		while (!pq.isEmpty()) {
			Student current = pq.poll();
			int classNumber = current.classNumber;
			int skill = current.skill;

			if (isNotPresent[classNumber]) {
				isNotPresent[classNumber] = false;
				presentCount++;
			}

			classSkill[classNumber] = skill;
			minPQ.add(new Student(classNumber, skill));
			maxPQ.add(new Student(classNumber, skill));

			if (presentCount == N) {
				while (!minPQ.isEmpty() && classSkill[minPQ.peek().classNumber] != minPQ.peek().skill) {
					minPQ.poll();
				}
				while (!maxPQ.isEmpty() && classSkill[maxPQ.peek().classNumber] != maxPQ.peek().skill) {
					maxPQ.poll();
				}

				int min = minPQ.peek().skill;
				int max = maxPQ.peek().skill;
				answer = Math.min(answer, max - min);
			}
		}

		System.out.println(answer);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isNotPresent = new boolean[N + 1];
		classSkill = new int[N + 1];
		pq = new PriorityQueue<>();
		minPQ = new PriorityQueue<>((a, b) -> a.skill - b.skill);
		maxPQ = new PriorityQueue<>((a, b) -> b.skill - a.skill);

		presentCount = 0;
		Arrays.fill(isNotPresent, true);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				pq.add(new Student(i, Integer.parseInt(st.nextToken())));
			}
		}
	}
}
