import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Stack<Character> left = new Stack<Character>();
		Stack<Character> right = new Stack<Character>();
		
		for(int x = 0 ; x < n ; x++) {
			
			String input = br.readLine();
			int length = input.length();
			for(int k = 0 ; k < length ; k++) {
				char cur = input.charAt(k);
				
				if(cur == '<') {
					if(!left.isEmpty()) {
						right.push(left.pop());
					}
				} else if(cur == '>') {
					if(!right.isEmpty()) {
						left.push(right.pop());
					}
				} else if(cur == '-') {
					if(!left.isEmpty()) {
						left.pop();
					}
				} else {
					left.add(cur);
				}
			}
			
			while (!left.isEmpty()) {
				right.push(left.pop());
			}
			while (!right.isEmpty()) {
				sb.append(right.pop());
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
