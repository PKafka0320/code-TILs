import java.io.*;
import java.util.*;

public class Main {
    static class Station implements Comparable<Station> {
        int pos, oil;

        public Station(int pos, int oil) {
            this.pos = pos;
            this.oil = oil;
        }

        @Override
        public int compareTo(Station o) {
            if (this.pos == o.pos) return o.oil - this.oil;
            return this.pos - o.pos;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Queue<Station> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int oil = Integer.parseInt(st.nextToken());
            pq.add(new Station(pos, oil));
        }

        st = new StringTokenizer(br.readLine());
        int goal = Integer.parseInt(st.nextToken());
        int movableDistance = Integer.parseInt(st.nextToken());

        int answer = 0;
        Queue<Station> selectable = new PriorityQueue<>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o2.oil - o1.oil;
            }
        });
        while (!pq.isEmpty() || !selectable.isEmpty()) {
            while (!pq.isEmpty() && pq.peek().pos <= movableDistance) {
                selectable.add(pq.poll());
            }

            if (selectable.isEmpty()) {
                break;
            }

            if (movableDistance < goal) {
                answer++;
                movableDistance += selectable.poll().oil;
            } else {
                break;
            }
        }

        System.out.println(movableDistance < goal ? -1 : answer);
    }
}
