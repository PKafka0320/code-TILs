import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        int nextN = N;
        while (count == 0 || nextN != N) {
            count++;
            int right = nextN % 10;
            int left = (nextN - right) / 10;
            int sum = left + right;

            nextN = (right * 10) + (sum % 10);
        }
        System.out.println(count);
    }
}