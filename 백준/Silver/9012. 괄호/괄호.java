import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0 ; i < N ; i++) {
			String input = br.readLine();
			
			stack.clear();
			
			for(int k = 0 ; k < input.length() ; k++) {
				char cur = input.charAt(k);
				
				if(!stack.isEmpty() && stack.peek() == '(' && cur == ')') {
					stack.pop();
				} else {
					stack.add(cur);
				}
			}
			
			if(stack.isEmpty()) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
			
		}
		
		System.out.println(sb);
		
	}

}