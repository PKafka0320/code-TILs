import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        while (T-- > 0) {
            String string = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] indexList = makeIndexList(string);

            int min = Integer.MAX_VALUE;
            int max = -1;
            for (int i = 0; i < 26; i++) {
                List<Integer> list = indexList[i];
                int left = 0;
                int right = 0;

                while (right < list.size()) {
                    if (right - left + 1 == K) {
                        min = Math.min(min, list.get(right) - list.get(left) + 1);
                        max = Math.max(max, list.get(right) - list.get(left) + 1);
                    }
                    right++;
                    while (right - left + 1 > K) {
                        left++;
                    }
                }
            }

            if (max == -1) {
                answer.append("-1\n");
            } else {
                answer.append(min).append(" ").append(max).append("\n");
            }
        }
        System.out.println(answer.toString());
    }

    private static List<Integer>[] makeIndexList(String string) {
        List<Integer>[] indexList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            indexList[i] = new ArrayList<>();
        }

        for (int i = 0; i < string.length(); i++) {
            indexList[string.charAt(i) - 'a'].add(i);
        }

        return indexList;
    }
}