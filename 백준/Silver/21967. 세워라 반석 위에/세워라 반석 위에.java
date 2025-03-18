import java.awt.im.spi.InputMethod;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        TreeMap<Integer, Integer> map = new TreeMap<>(); // 개수

        int left = 0;
        int right = 0;
        int maxLen = 0;

        map.put(arr[right], 1);
        right = 1;

        while (right < N) {
            int number = arr[right];
            if (!map.containsKey(number)) {
                map.put(number, 1);
            } else {
                map.put(number, map.get(number) + 1);
            }

            while (map.lastKey() - map.firstKey() > 2) {
                int removeNumber = arr[left];
                if (map.get(removeNumber) == 1) {
                    map.remove(removeNumber);
                } else {
                    map.put(removeNumber, map.get(removeNumber) - 1);
                }

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        System.out.println(maxLen);
    }
}