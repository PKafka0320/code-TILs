import java.io.*;
import java.util.*;

public class Solution {
    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static class Stair {
        int row, col, time;

        public Stair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    static Position[] positions;
    static Stair[] stairs;
    static int pIndex, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            
            int n = Integer.parseInt(br.readLine());
            positions = new Position[10];
            stairs = new Stair[2];
            pIndex = 0;
            result = Integer.MAX_VALUE;
            
            int sIndex = 0;
            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < n; c++) {
                    int number = Integer.parseInt(st.nextToken());
                    
                    if (number == 1) {
                        positions[pIndex++] = new Position(r, c);
                    }
                    
                    if (number != 1 && number != 0) {
                        stairs[sIndex++] = new Stair(r, c, number);
                    }
                }
            }
            
            select(0, new int[pIndex]);

            answer.append(result).append("\n");
        }
        System.out.println(answer);
    }
    
    public static void select(int index, int[] selected) {
        if (index == pIndex) {
            evaluate(selected);
            return;
        }
        
        selected[index] = 0;
        select(index + 1, selected);
        selected[index] = 1;
        select(index + 1, selected);
    }
    
    public static void evaluate(int[] selected) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<Integer> q = new LinkedList<>();
        int maxTime = 0;
        
        for (int stairIdx = 0; stairIdx < 2; stairIdx++) {
            for (int i = 0; i < pIndex; i++) {
                if (selected[i] != stairIdx) continue;
                
                int preTime = Math.abs(positions[i].row - stairs[stairIdx].row) +
                        Math.abs(positions[i].col - stairs[stairIdx].col) + 1;
                pq.add(preTime);
            }
            
            while (!pq.isEmpty()) {
                int preTime = pq.poll();
                
                if (q.size() < 3) {
                    q.add(preTime + stairs[stairIdx].time);
                } else {
                    int time = q.poll();
                    while (!q.isEmpty() && q.peek() == time) {
                        q.poll();
                    }
                    
                    if (preTime <= time) {
                        q.add(time + stairs[stairIdx].time);
                    } else {
                        q.add(preTime + stairs[stairIdx].time);
                    }
                }
            }
            
            while (!q.isEmpty()) {
                maxTime = Math.max(maxTime, q.poll());
            }
        }
        
        result = Math.min(result, maxTime);
    }
}
