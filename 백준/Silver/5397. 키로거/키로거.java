import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Deque<Character> left = new ArrayDeque<Character>();
		Deque<Character> right = new ArrayDeque<Character>();
		
		for(int x = 0 ; x < n ; x++) {
			
			String input = br.readLine();
			int length = input.length();
			for(int k = 0 ; k < length ; k++) {
				char cur = input.charAt(k);
				
				if(cur == '<') {
					if(!left.isEmpty()) {
						right.addFirst(left.pollLast());
					}
				} else if(cur == '>') {
					if(!right.isEmpty()) {
						left.addLast(right.pollFirst());
					}
				} else if(cur == '-') {
					if(!left.isEmpty()) {
						left.pollLast();
					}
				} else {
					left.addLast(cur);
				}
			}
			
			while (!left.isEmpty()) {
				sb.append(left.pollFirst());
			}
			while (!right.isEmpty()) {
				sb.append(right.pollFirst());
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
