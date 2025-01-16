import java.io.*;
import java.util.*;

public class Main {
    static class Cow implements Comparable<Cow> {
        int L, R;

        public Cow(int l, int r) {
            L = l;
            R = r;
        }

        @Override
        public int compareTo(Cow o) {
            return Integer.compare(this.L, o.L);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] chickens = new int[C];
        for (int i = 0; i < C; i++) {
            chickens[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(chickens);

        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(L, R);
        }
        Arrays.sort(cows);

        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0, cowIdx = 0; i < C; i++) {
            while(cowIdx < N && cows[cowIdx].L <= chickens[i]) {
                pq.add(cows[cowIdx++].R);
            }

            while(!pq.isEmpty() && pq.peek() < chickens[i]) {
                pq.poll();
            }

            if(!pq.isEmpty()) {
                answer++;
                pq.poll();
            }
        }
        System.out.println(answer);
    }
}
