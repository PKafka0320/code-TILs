import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer = 4;
	static int N, M, H;
	static boolean[][] grid;

	public static void main(String[] args) throws IOException {
		init();
		solve(0, 0);
		if (answer <= 3) {
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
	}

	private static void solve(int index, int count) {
		if (count > 3) {
			return;
		}
		if (index >= N * H) {
			check(count);
			return;
		}

		int row = index / N;
		int col = index % N;
		if (cannot(row, col)) {
			solve(index + 1, count);
			return;
		}
		
		grid[row][col] = true;
		solve(index + 1, count + 1);
		grid[row][col] = false;
		solve(index + 1, count);
	}

	private static boolean cannot(int row, int col) {
		return grid[row][col] || (col > 0 && grid[row][col - 1]) || (col < N - 1 && grid[row][col + 1]);
	}

	private static void check(int count) {
		for (int i = 0; i <= N; i++) {
			int row = 0;
			int col = i;

			while (row < H) {
				if (col > 0 && grid[row][col - 1]) {
					col --;
				} else if (col < N && grid[row][col]) {
					col++;
				}
				row++;
			}

			if (col != i) {
				return;
			}
		}
		answer = Math.min(answer, count);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) - 1;
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		grid = new boolean[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			grid[a][b] = true;
		}
	}
}
