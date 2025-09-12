import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Camera {
		int row, col, type;

		public Camera(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer;
	static int N, M;
	static int[][] grid;
	static int[][] cameraCount;
	static ArrayList<Camera> cameras;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		init();
		solve(0);
		System.out.println(answer);
	}

	private static void solve(int index) {
		if (index >= cameras.size()) {
			checkResult();
			return;
		}

		Camera camera = cameras.get(index);
		int row, col;
		if (camera.type == 5) {
			for (int dir = 0; dir < 4; dir++) {
				row = camera.row + dr[dir];
				col = camera.col + dc[dir];
				while (inGrid(row, col) && grid[row][col] != 6) {
					cameraCount[row][col]++;
					row += dr[dir];
					col += dc[dir];
				}
			}
			solve(index + 1);
			for (int dir = 0; dir < 4; dir++) {
				row = camera.row + dr[dir];
				col = camera.col + dc[dir];
				while (inGrid(row, col) && grid[row][col] != 6) {
					cameraCount[row][col]--;
					row += dr[dir];
					col += dc[dir];
				}
			}
		} else if (camera.type == 2) {
			for (int rotate = 0; rotate < 2; rotate++) {
				for (int dir = rotate * 2; dir < 2 + rotate * 2; dir++) {
					row = camera.row + dr[dir];
					col = camera.col + dc[dir];
					while (inGrid(row, col) && grid[row][col] != 6) {
						cameraCount[row][col]++;
						row += dr[dir];
						col += dc[dir];
					}
				}
				solve(index + 1);
				for (int dir = rotate * 2; dir < 2 + rotate * 2; dir++) {
					row = camera.row + dr[dir];
					col = camera.col + dc[dir];
					while (inGrid(row, col) && grid[row][col] != 6) {
						cameraCount[row][col]--;
						row += dr[dir];
						col += dc[dir];
					}
				}
			}
		} else if (camera.type == 3) {
			for (int dir1 = 0; dir1 < 2; dir1++) {
				row = camera.row + dr[dir1];
				col = camera.col + dc[dir1];
				while (inGrid(row, col) && grid[row][col] != 6) {
					cameraCount[row][col]++;
					row += dr[dir1];
					col += dc[dir1];
				}
				for (int dir2 = 2; dir2 < 4; dir2++) {
					row = camera.row + dr[dir2];
					col = camera.col + dc[dir2];
					while (inGrid(row, col) && grid[row][col] != 6) {
						cameraCount[row][col]++;
						row += dr[dir2];
						col += dc[dir2];
					}
					solve(index + 1);
					row = camera.row + dr[dir2];
					col = camera.col + dc[dir2];
					while (inGrid(row, col) && grid[row][col] != 6) {
						cameraCount[row][col]--;
						row += dr[dir2];
						col += dc[dir2];
					}
				}
				row = camera.row + dr[dir1];
				col = camera.col + dc[dir1];
				while (inGrid(row, col) && grid[row][col] != 6) {
					cameraCount[row][col]--;
					row += dr[dir1];
					col += dc[dir1];
				}
			}
		} else {
			for (int rotate = 0; rotate < 4; rotate++) {
				if (camera.type == 1) {
					for (int dir = 0; dir < 4; dir++) {
						if (dir != rotate) {
							continue;
						}
						row = camera.row + dr[dir];
						col = camera.col + dc[dir];
						while (inGrid(row, col) && grid[row][col] != 6) {
							cameraCount[row][col]++;
							row += dr[dir];
							col += dc[dir];
						}
					}
				} else if (camera.type == 4) {
					for (int dir = 0; dir < 4; dir++) {
						if (dir == rotate) {
							continue;
						}
						row = camera.row + dr[dir];
						col = camera.col + dc[dir];
						while (inGrid(row, col) && grid[row][col] != 6) {
							cameraCount[row][col]++;
							row += dr[dir];
							col += dc[dir];
						}
					}
				}
				solve(index + 1);
				if (camera.type == 1) {
					for (int dir = 0; dir < 4; dir++) {
						if (dir != rotate) {
							continue;
						}
						row = camera.row + dr[dir];
						col = camera.col + dc[dir];
						while (inGrid(row, col) && grid[row][col] != 6) {
							cameraCount[row][col]--;
							row += dr[dir];
							col += dc[dir];
						}
					}
				} else if (camera.type == 4) {
					for (int dir = 0; dir < 4; dir++) {
						if (dir == rotate) {
							continue;
						}
						row = camera.row + dr[dir];
						col = camera.col + dc[dir];
						while (inGrid(row, col) && grid[row][col] != 6) {
							cameraCount[row][col]--;
							row += dr[dir];
							col += dc[dir];
						}
					}
				}
			}
		}
	}

	private static boolean inGrid(int row, int col) {
		return (0 <= row && row < N && 0 <= col && col < M);
	}

	private static void checkResult() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] != 0 || cameraCount[i][j] > 0) {
					result++;
				}
			}
		}
		answer = Math.min(answer, N * M - result);
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		answer = N * M;
		grid = new int[N][M];
		cameras = new ArrayList<>();
		cameraCount = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());

				if (grid[i][j] != 0 && grid[i][j] != 6) {
					cameras.add(new Camera(i, j, grid[i][j]));
				}
			}
		}
	}
}
