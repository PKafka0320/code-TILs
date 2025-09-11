import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer;
	static int[][] grid;
	static boolean[][] rowCheck, colCheck, squareCheck;

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		fill(0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				answer.append(grid[i][j]).append(" ");
			}
			answer.append("\n");
		}
		System.out.println(answer);
	}

	private static boolean fill(int index) {
		if (index == 81) {
			return true;
		}
		int row = index / 9;
		int col = index % 9;

		if (grid[row][col] != 0) {
			return fill(index + 1);
		} else {
			for (int value = 1; value <= 9; value++) {
				if (rowCheck[row][value] || colCheck[col][value] || squareCheck[squareNumber(row, col)][value]) {
					continue;
				}

				grid[row][col] = value;
				rowCheck[row][value] = colCheck[col][value] = squareCheck[squareNumber(row, col)][value] = true;
				if (fill(index + 1)) {
					return true;
				}
				grid[row][col] = 0;
				rowCheck[row][value] = colCheck[col][value] = squareCheck[squareNumber(row, col)][value] = false;
			}
		}
		return false;
	}

	private static int squareNumber(int row, int col) {
		return (row / 3) * 3 + (col / 3);
	}

	private static void init() throws IOException {
		answer = new StringBuilder();

		grid = new int[9][9];
		rowCheck = new boolean[9][10];
		colCheck = new boolean[9][10];
		squareCheck = new boolean[9][10];

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());

				if (grid[i][j] != 0) {
					rowCheck[i][grid[i][j]] = true;
					colCheck[j][grid[i][j]] = true;
					squareCheck[squareNumber(i,j)][grid[i][j]] = true;
				}
			}
		}
	}
}
