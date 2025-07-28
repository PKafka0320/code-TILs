import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		// arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arr = new int[N];
		sum = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		sum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}

		long answer = 0;
		for (int i = 0; i < N - 3; i++) {
			for (int j = i + 1; j < N - 2; j++) {
				for (int k = j + 1; k < N - 1; k++) {
					int sum1 = sum[i];
					int sum2 = sum[j] - sum[i];
					int sum3 = sum[k] - sum[j];
					int sum4 = sum[N - 1] - sum[k];

					if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4) {
						answer++;
					}
				}
			}
		}

		System.out.println(answer);
	}
}