import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for(int i = 0 ; i < N ; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		boolean flag = false;
		
		for(int i = 0 ; i < T ; i++) {
			int cur = pq.peek();
			
			if(cur < H) {
				flag = true;
				sb.append("YES\n").append(i);
				break;
			} else {
				if(pq.peek() != 1) {
					cur = pq.poll() / 2;
				}
				pq.add(cur);
				
				if(pq.peek() < H) {
					flag = true;
					sb.append("YES\n").append(i+1);
					break;
				}
				
			}
			
		}
	
		if(!flag) {
			sb.append("NO\n").append(pq.peek());
		}
		
		System.out.println(sb);
		
	}
	
}