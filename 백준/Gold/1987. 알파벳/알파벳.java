import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer;
	static int R, C;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static char[][] grid;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		boolean[] check = new boolean[27];
		check[grid[0][0] - 'A'] = true;
		findDepth(0, 0, 1, check);
		System.out.println(answer);
	}

	private static void findDepth(int row, int col, int depth, boolean[] check) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (cannotMove(nr, nc) || check[grid[nr][nc] - 'A']) {
				continue;
			}

			check[grid[nr][nc] - 'A'] = true;
			findDepth(nr, nc, depth + 1, check);
			check[grid[nr][nc] - 'A'] = false;
		}
		answer = Math.max(answer, depth);
	}

	private static boolean cannotMove(int nr, int nc) {
		return (nr < 0 || nr >= R || nc < 0 || nc >= C);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		answer = 1;
		grid = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				grid[i][j] = line.charAt(j);
			}
		}
	}
}
