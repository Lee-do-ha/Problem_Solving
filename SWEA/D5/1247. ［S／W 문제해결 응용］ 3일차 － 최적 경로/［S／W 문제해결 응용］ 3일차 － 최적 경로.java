import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class customer{
		int x;
		int y;
		
		public customer(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
	
	static int ans, N, startX, startY, endX, endY;
	static int[] orders;
	static boolean[] visited;
	static customer[] Customer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T  = Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i <= T ; i++) {
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			orders = new int[N];
			visited = new boolean[N];

			Customer = new customer[N];
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			for(int k = 0 ; k < N ; k++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				Customer[k] = new customer(a, b);
			}
			
			Combination(0);
			
			sb.append("#" + i + " " + ans + " \n");
		}
		
		System.out.println(sb);
	}
	
	private static void Combination(int cnt) {
		if(cnt == N) {
			int distance = 0;
			distance += (int)Math.abs(Customer[orders[0]].x - startX);
			distance += (int)Math.abs(Customer[orders[0]].y - startY);
			distance += (int)Math.abs(Customer[orders[N-1]].x - endX);
			distance += (int)Math.abs(Customer[orders[N-1]].y - endY);
			
			for(int i = 1 ; i < N ; i++) {
				distance += (int)Math.abs(Customer[orders[i]].x - Customer[orders[i-1]].x);
				distance += (int)Math.abs(Customer[orders[i]].y - Customer[orders[i-1]].y);
			}
			
			if(distance < ans) {
				ans = distance;
			}
			return;
		}
		
		for(int i = 0 ; i < N ; i++) {
			if(visited[i] ==false) {
				orders[cnt] = i;
				visited[i] = true;
				Combination(cnt + 1);
				visited[i] = false;
			}
		}
	}
}