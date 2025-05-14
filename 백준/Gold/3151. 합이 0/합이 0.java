import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = sc.nextInt();
        }

        Arrays.sort(v); // 오름차순 정렬
        long ans = 0;

        for (int i = 0; i < v.length - 1; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int target = -(v[i] + v[j]);
                int left = lowerBound(v, j + 1, v.length, target);
                int right = upperBound(v, j + 1, v.length, target);
                ans += right - left;
            }
        }

        System.out.println(ans);
    }

    // lower_bound: target 이상인 첫 번째 인덱스
    private static int lowerBound(int[] arr, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    // upper_bound: target 초과인 첫 번째 인덱스
    private static int upperBound(int[] arr, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
