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
            
            int gridS = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridS][gridS];
            positions = new Position[10];
            stairs = new Stair[2];
            pIndex = 0;
            result = Integer.MAX_VALUE;
            
            int sIndex = 0;
            for (int r = 0; r < gridS; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < gridS; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                    
                    if (grid[r][c] == 1) {
                        positions[pIndex++] = new Position(r, c);
                    }
                    
                    if (grid[r][c] != 1 && grid[r][c] != 0) {
                        stairs[sIndex++] = new Stair(r, c, grid[r][c]);
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
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        
        for (int i = 0; i < pIndex; i++) {
            if (selected[i] == 0) {
                int preTime = Math.abs(positions[i].row - stairs[0].row) + Math.abs(positions[i].col - stairs[0].col) + 1;
                pq1.add(preTime);
            } else {
                int preTime = Math.abs(positions[i].row - stairs[1].row) + Math.abs(positions[i].col - stairs[1].col) + 1;
                pq2.add(preTime);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        int maxTime = 0;
        
        while (!pq1.isEmpty()) {
            int preTime = pq1.poll();
            
            if (q.size() < 3) {
                q.add(preTime + stairs[0].time);
            } else {
                int time = q.poll();
                while (!q.isEmpty() && q.peek() == time) {
                    q.poll();
                }
                
                if (preTime <= time) {
                    q.add(time + stairs[0].time);
                } else {
                    q.add(preTime + stairs[0].time);
                }
            }
        }
        
        while (!q.isEmpty()) {
            maxTime = Math.max(maxTime, q.poll());
        }
        
        while (!pq2.isEmpty()) {
            int preTime = pq2.poll();
            
            if (q.size() < 3) {
                q.add(preTime + stairs[1].time);
            } else {
                int time = q.poll();
                while (!q.isEmpty() && q.peek() == time) {
                    q.poll();
                }
                
                if (preTime <= time) {
                    q.add(time + stairs[1].time);
                } else {
                    q.add(preTime + stairs[1].time);
                }
            }
        }
        
        while (!q.isEmpty()) {
            maxTime = Math.max(maxTime, q.poll());
        }
        
        result = Math.min(result, maxTime);
    }

}
