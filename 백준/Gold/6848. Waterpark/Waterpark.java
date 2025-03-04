import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			lists.add(new ArrayList<Integer>());
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x == 0 && y == 0) break;
			
			lists.get(x-1).add(y-1);
		}
		
		long[] dp = new long[N];
		dp[0] = 1;
		
		for(int i = 0 ; i < N ; i++) {
			for(int next : lists.get(i)) {
				dp[next] += dp[i];
			}
		}
		
		System.out.println(dp[N-1]);
	}
	
}