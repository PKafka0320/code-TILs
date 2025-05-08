import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(weights);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (weights[i] > sum + 1) break;
            sum += weights[i];
        }
        System.out.println(sum + 1);
    }
}