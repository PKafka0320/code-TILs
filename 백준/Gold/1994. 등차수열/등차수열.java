import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }

        int[] nums = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int answer = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 1 + i; j <= N; j++) {
                dp[i][j] = 2;
                int pre = nums[i] - (nums[j] - nums[i]);

                int lt = 1, rt = i - 1;
                int mid = 0;
                while (lt < rt) {
                    mid = (lt + rt) / 2;
                    if (nums[mid] < pre) lt = mid + 1;
                    else if (nums[mid] == pre && nums[rt] == pre) lt = mid + 1;
                    else rt = mid;
                }

                if (nums[rt] == pre) dp[i][j] = Math.max(dp[i][j], dp[rt][i] + 1);
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) return true;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}
