import java.io.*;
import java.util.*;

public class Main {
    static int G, P;
    static int[] g;
    static int[] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        g = new int[G+1];
        next = new int[G+1];
        for (int i = 0; i <= G; i++) {
            next[i] = i;
        }

        int count = 0;
        for (int i = 0; i < P; i++) {
            int position = Integer.parseInt(br.readLine());

            int nextPosition = find(position);
            if (nextPosition == 0) break;

            count++;
            next[nextPosition] = nextPosition-1;
            next[position] = next[nextPosition];
        }
        System.out.println(count);
    }

    private static int find(int position) {
        if (next[position] == position) return position;
        return next[position] = find(next[position]);
    }
}