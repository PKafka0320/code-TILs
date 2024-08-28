import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = 10;
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int root = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[101];
            List<Integer>[] edges = new ArrayList[101];
            
            for (int i = 1; i <= 100; i++) {
                edges[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                edges[from].add(to);
            }
            
            Queue<Integer> queue = new ArrayDeque<>();
            Queue<Integer> nextq = new ArrayDeque<>();
            nextq.add(root);
            visited[root] = true;
            
            int lastMax = -1;
            List<Integer> lastAdded = new ArrayList<>();
            while (!nextq.isEmpty()) {
                // 다음으로 순회할 목록을 현재 순회할 목록으로 가져오기
                Queue temp = queue;
                queue = nextq;
                nextq = temp;
                
                // 마지막으로 추가되는 숫자 리스트 초기화
                lastAdded.clear();
                
                // bfs
                while(!queue.isEmpty()) {
                    int current = queue.poll();
                    
                    for (int next : edges[current]) {
                        if (visited[next]) continue;
                        
                        visited[next] = true;
                        nextq.add(next); // 다음으로 순회할 목록에 추가
                        lastAdded.add(next);
                    }
                }
                
                // 마지막으로 추가된 숫자가 있다면 최댓값 초기화
                if (!lastAdded.isEmpty()) {
                    lastMax = -1;
                }
                
                // 마지막으로 추가된 숫자 중 최댓값 갱신
                for (int num : lastAdded) {
                    lastMax = Math.max(num, lastMax);
                }
            }
            answer.append(lastMax).append("\n");
        }
        System.out.println(answer);
    }
    
}
