import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Member {
        int id, time, number;

        public Member(int id, int time, int number) {
            this.id = id;
            this.time = time;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", time=" + time +
                    ", number=" + number +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Member> q = new LinkedList<>();
        PriorityQueue<Integer> nq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        PriorityQueue<Member> pq = new PriorityQueue<>(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if (o1.time == o2.time) {
                    return o2.number - o1.number;
                }
                return o1.time - o2.time;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            q.offer(new Member(id, w, -1));
        }

        for (int i = 0; i < K; i++) {
            nq.offer(i);
        }

        int currentNumber = 1;
        int currentTime = 0;
        long answer = 0;
        while (true) {
            while (!nq.isEmpty() && !q.isEmpty()) {
                Member member = q.poll();
                member.time += currentTime;
                member.number = nq.poll();
                pq.offer(member);
                //System.out.printf("entering member: " + member + "\n");
            }
            if (pq.isEmpty()) break;

            int time = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == time) {
                Member member = pq.poll();
                //System.out.printf("=======================%d + %d * %d = ", answer, currentNumber, member.id);
                answer += (long) member.id * currentNumber;
                //System.out.printf("%d\n", answer);
                currentNumber++;
                nq.offer(member.number);
                //System.out.printf("leaving member: " + member + "\n");
            }
            currentTime = time;
        }
        System.out.println(answer);
    }
}
