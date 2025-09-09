import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int L, C, CC;
	static char[] chars;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		check(-1, 0, 0, "");
		System.out.println(answer);
	}

	private static void check(int charIndex, int countA, int countB, String result) {
		if (result.length() == L) {
			if (countA >= 1 && countB >= 2) {
				answer.append(result).append("\n");
			}
			return;
		}

		for (int index = charIndex + 1; index < CC; index++) {
			if (chars[index] == 'a' || chars[index] == 'e' || chars[index] == 'i' || chars[index] == 'o'
				|| chars[index] == 'u') {
				check(index, countA + 1, countB, result + chars[index]);
			} else {
				check(index, countA, countB + 1, result + chars[index]);
			}
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		answer = new StringBuilder();

		HashSet<Character> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			set.add(st.nextToken().charAt(0));
		}

		CC = set.size();
		chars = new char[CC];

		int idx = 0;
		for (char ch : set) {
			chars[idx++] = ch;
		}

		Arrays.sort(chars);
	}
}
