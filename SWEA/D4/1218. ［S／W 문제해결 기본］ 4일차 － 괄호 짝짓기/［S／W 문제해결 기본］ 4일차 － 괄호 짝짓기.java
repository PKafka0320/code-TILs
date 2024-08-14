import java.io.*;
import java.util.*;

public class Solution {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringBuilder answer = new StringBuilder();
    	test:
    	for (int tc = 1; tc <= 10; tc++) {
    		answer.append("#").append(tc).append(" ");
    		
    		int len = Integer.parseInt(br.readLine());
    		String string = br.readLine();
    		
    		Stack<Character> stack = new Stack<>();
    		for (char c : string.toCharArray()) {
    			if (c == '{' || c == '[' || c == '(' || c == '<') {
    				stack.push(c);
    			}
    			else {
    				if (stack.isEmpty()) {
						answer.append("0\n");
    					continue test;
    				}
    				switch (c) {
    					case '}':
    						if (stack.peek() != '{') {
    							answer.append("0\n");
    							continue test;
    						}
    						stack.pop();
    						break;
    					case ']':
    						if (stack.peek() != '[') {
    							answer.append("0\n");
    							continue test;
    						}
    						stack.pop();
    						break;
    					case ')':
    						if (stack.peek() != '(') {
    							answer.append("0\n");
    							continue test;
    						}
    						stack.pop();
    						break;
    					case '>':
    						if (stack.peek() != '<') {
    							answer.append("0\n");
    							continue test;
    						}
    						stack.pop();
    						break;
    					default:
    						break;
    				}
    			}
    		}
    		
    		answer.append("1\n");
    	}
    	
    	System.out.println(answer);
    }
}