import java.io.*;
import java.util.*;

public class Main {
    static class Square implements Comparable<Square> {
        int L, H, R;

        public Square(int l, int h, int r) {
            L = l;
            H = h;
            R = r;
        }

        @Override
        public int compareTo(Square o) {
            if (this.L == o.L) return this.R - o.R;
            return this.L - o.L;
        }

        @Override
        public String toString() {
            return "Square{" +
                    "L=" + L +
                    ", H=" + H +
                    ", R=" + R +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Queue<Square> squares = new PriorityQueue<>();
        Set<Integer> positions = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            positions.add(L);
            positions.add(R);
            squares.add(new Square(L, H, R));
        }

        Queue<Square> current = new PriorityQueue<>(new Comparator<Square>() {
            @Override
            public int compare(Square o1, Square o2) {
                if (o1.H == o2.H) return o1.R - o2.R;
                return o2.H - o1.H;
            }
        });

        StringBuilder answer = new StringBuilder();
        int posIdx = 0;
        int currentHeight = 0;
        List<Integer> list = new ArrayList<>(positions);
        while(!squares.isEmpty() || !current.isEmpty()) {
            if (list.isEmpty()) {
                break;
            }
            int position = list.get(posIdx);
            int maxHeight = 0;
//            System.out.println("current position: " + position);

            while (!current.isEmpty() && current.peek().R <= position) {
//                System.out.println("remove " + current.peek());
                current.poll();
            }

            while (!squares.isEmpty() && squares.peek().L <= position) {
                Square square = squares.poll();
//                System.out.println("add " + square);
                maxHeight = Math.max(maxHeight, square.H);
                current.add(square);
            }

            maxHeight = current.isEmpty() ? 0 : current.peek().H;
            if (currentHeight != maxHeight) {
//                System.out.println("update: " + position + " " + maxHeight);
                currentHeight = maxHeight;
                answer.append(position).append(" ").append(currentHeight).append(" ");
            }

            posIdx++;
            if (posIdx == positions.size()) {
                break;
            }
        }
        System.out.println(answer.toString());
    }
}
