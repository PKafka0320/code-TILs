import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[][] problems = new int[N][2];
        for(int i = 0; i < N ;i++ ){
            st = new StringTokenizer(br.readLine());
            problems[i][0] = Integer.parseInt(st.nextToken());
            problems[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(problems, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] problem : problems) {
            int deadline = problem[0];
            int ramen = problem[1];
            pq.offer(ramen);
            if(pq.size() > deadline) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            ans += pq.poll();
        }
        System.out.println(ans);
    }
}
