import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Pair> pairs = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.right == o2.right) return o1.left - o2.left;
                return o1.right - o2.right;
            }
        });
        Queue<Pair> selects = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.left == o2.left) return o1.right - o2.right;
                return o1.left - o2.left;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            if (h > o) {
                int temp = o;
                o = h;
                h = temp;
            }

            pairs.add(new Pair(h, o));
        }

        int d = Integer.parseInt(br.readLine());

        int answer = 0;
        while (!pairs.isEmpty()) {
            int end = pairs.peek().right;
            int start = end - d;

            selects.add(pairs.poll());

            while (!selects.isEmpty() && selects.peek().left < start) {
                selects.poll();
            }

            answer = Math.max(answer, selects.size());
        }
        System.out.println(answer);
    }
}
