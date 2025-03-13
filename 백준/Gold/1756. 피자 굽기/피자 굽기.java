import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			if(stack.isEmpty()) {
				stack.add(Integer.parseInt(st.nextToken()));
			} else {
				stack.add(Math.min(Integer.parseInt(st.nextToken()), stack.peek()));
			}
			
		}
		
		int[] pizza = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}
		
		int now = 0;
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			
			if(cur >= pizza[now]) {
				now++;
				if(now == M) {
					System.out.println(stack.size() + 1);
					return ;
				}
			}
			
		}
		
		System.out.println(0);
		
	}
	
}