import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (K > M) {
			System.out.println(0);
			return;
		}

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < K; j++) {
				if (arr[i][j] == 0)
					count++;
			}

			if (count == K) {
				answer++;
			}

			for (int j = K; j < M; j++) {
				if (arr[i][j] == 0)
					count++;
				if (arr[i][j - K] == 0)
					count--;

				if (count == K) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}