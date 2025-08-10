import java.io.*;
import java.util.*;

public class Main {
	static class Number implements Comparable<Number> {
		int number, index;
		
		public Number(int number, int index) {
			this.number = number;
			this.index = index;
		}
		
		@Override
		public int compareTo(Number o) {
			return this.number - o.number;
		}
	}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int l  = Integer.parseInt(st.nextToken());
        
        LinkedList<Number> ll = new LinkedList<>();
        
        st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
        	Number current = new Number(Integer.parseInt(st.nextToken()), i);
        	while (!ll.isEmpty() && ll.getLast().number >= current.number) ll.removeLast();
        	ll.addLast(current);
        	while (!ll.isEmpty() && ll.getFirst().index <= i - l) ll.removeFirst();
        	answer.append(ll.getFirst().number).append(" ");
        }
        
        System.out.println(answer);
    }

}
