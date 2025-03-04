import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 2];
		
		int[] days = new int[N + 1];
		int[] moneys = new int[N + 1];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			
			days[i] = day;
			moneys[i] = money;
		}
		
		for(int i = 1 ; i < N + 2 ; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			
			if(i + days[i - 1] > N + 1) continue;
			
			dp[i + days[i - 1]] = Math.max(dp[i + days[i - 1]], dp[i] + moneys[i - 1]);
			
		}
		
		
		System.out.println(dp[N + 1]);
	}
	
}