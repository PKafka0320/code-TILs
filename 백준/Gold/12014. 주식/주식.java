import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int testNum = 1; testNum <= T; testNum++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] list = new int[N];
            int length = 0;

            list[length++] = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] > list[length - 1]) {
                    list[length++] = arr[i];
                } else {
                    int changeIdx = search(list, length - 1, arr[i]);
                    list[changeIdx] = arr[i];
                }
            }

            answer.append("Case #").append(testNum).append("\n").append(length >= K ? 1 : 0).append("\n");
        }
        System.out.println(answer);
    }

    private static int search(int[] list, int size, int target) {
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) / 2;
            if (list[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}