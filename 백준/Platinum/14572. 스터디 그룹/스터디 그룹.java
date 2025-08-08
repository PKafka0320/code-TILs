import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, D;
	static Student[] students;

	static class Student implements Comparable<Student> {
		int skill;
		ArrayList<Integer> algo;

		public Student(int skill, ArrayList<Integer> algo) {
			this.skill = skill;
			this.algo = algo;
		}

		@Override
		public int compareTo(Student o) {
			return this.skill - o.skill;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		int[] algoCount = new int[K + 1];
		int max = 0;
		int start = 0;
		int end = 0;

		for (int a : students[0].algo) {
			algoCount[a]++;
		}

		while (true) {
			int gap = students[end].skill - students[start].skill;

			if (gap <= D) {
				max = Math.max(max, getEfficiency(algoCount, start, end));
				end++;
				if (end >= N)
					break;

				for (int a : students[end].algo) {
					algoCount[a]++;
				}
				
				continue;
			}

			for (int a : students[start].algo) {
				algoCount[a]--;
			}
			start++;
		}

		System.out.println(max);
	}

	private static int getEfficiency(int[] arr, int start, int end) {
		int cnt1 = 0;
		int cnt2 = 0;
		int n = end - start + 1;

		for (int j : arr) {
			if (j != 0)
				cnt1++;

			if (j == n)
				cnt2++;
		}

		return (cnt1 - cnt2) * n;
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		students = new Student[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			ArrayList<Integer> algo = new ArrayList<>(m);
			st = new StringTokenizer(br.readLine());
			while (m-- > 0)
				algo.add(Integer.parseInt(st.nextToken()));
			students[i] = new Student(d, algo);
		}
		Arrays.sort(students);
	}
}