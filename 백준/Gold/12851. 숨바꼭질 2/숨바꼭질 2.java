import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); // 수빈의 위치
        int K = Integer.parseInt(tokenizer.nextToken()); // 동생의 위치
        
        // 동생의 위치가 앞에 있는 경우 길이만큼 시간이 소요
        if (N >= K) {
            System.out.println((N-K) + "\n1");
            return;
        }
        
        Queue<Integer> positions = new LinkedList<Integer>(); // 위치
        HashMap<Integer, Integer> times = new HashMap<>(); // 시간
        int minTime = Integer.MAX_VALUE; // K 위치에 도달하는 최소 시간
        int count = 0; // K 위치에 최소시간으로 도달하는 방법의 수
        
        // 초기화
        positions.add(N);
        times.put(N, 1);
        
        while (!positions.isEmpty()) {
            int position = positions.poll();
            
            // 해당 위치에 도달하는 시간이 목표 위치에 도달하는 최소 시간보다 큰 경우 더 볼 필요 없음
            if (minTime < times.get(position)) break;
            
            for (int method = 0; method < 3; method++) {
                int nextPosition;

                if (method == 0) nextPosition = position + 1;
                else if (method == 1) nextPosition = position - 1;
                else nextPosition = position * 2;

                if (nextPosition < 0  || nextPosition > 100000) continue;

                if (nextPosition == K) {
                    minTime = times.get(position);
                    count++;
                }

                // 첫 방문이거나 이미 방문한 곳이어도 같은 시간에 방문했다면
                // 경우의 수에 추가될 수 있기 때문에 방문할 위치 목록에 추가
                int nextPositionTime = times.getOrDefault(nextPosition, 0);
                if (nextPositionTime == 0 || nextPositionTime == times.get(position) + 1) {
                    positions.add(nextPosition);
                    times.put(nextPosition, times.get(position) + 1);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(minTime).append("\n").append(count);
        System.out.println(answer);
    }
}
