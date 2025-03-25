import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int G = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        int left = 1;
        int right = 2;
        while (left < right) {
            while (Math.pow(right, 2) - Math.pow(left, 2) < G) {
                right++;
            }
            if (Math.pow(right, 2) - Math.pow(left, 2) == G) {
                answer.append(right).append("\n");
                right++;
            }
            while (Math.pow(right, 2) - Math.pow(left, 2) > G) {
                left++;
            }
        }
        System.out.println(answer.toString().isEmpty() ? -1 : answer.toString());
    }
}