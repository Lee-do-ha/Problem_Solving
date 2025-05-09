import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		int max = 0;

		for (int i = 0; i < n; i++) {
			int m = Integer.parseInt(br.readLine());
			
			list.add(m);
			
			max = Math.max(max, m);
		}
		
		int[][] dp = new int[max + 1][10];
		for(int i = 0 ; i < 10 ; i++) {
			dp[1][i] = 1;
		}
		
		int INF = 1234567;
		
		for(int i = 2 ; i < max + 1 ; i++) {
			dp[i][0] = (dp[i-1][7]) % INF;
			dp[i][1] = (dp[i-1][2] + dp[i-1][4]) % INF;
			dp[i][2] = (dp[i-1][1] + dp[i-1][3] + dp[i-1][5]) % INF;
			dp[i][3] = (dp[i-1][2] + dp[i-1][6]) % INF;
			dp[i][4] = (dp[i-1][1] + dp[i-1][5] + dp[i-1][7]) % INF;
			dp[i][5] = (dp[i-1][2] + dp[i-1][4] + dp[i-1][6] + dp[i-1][8]) % INF;
			dp[i][6] = (dp[i-1][3] + dp[i-1][5] + dp[i-1][9]) % INF;
			dp[i][7] = (dp[i-1][4] + dp[i-1][8] + dp[i-1][0]) % INF;
			dp[i][8] = (dp[i-1][5] + dp[i-1][7] + dp[i-1][9]) % INF;
			dp[i][9] = (dp[i-1][6] + dp[i-1][8]) % INF;
			
		}
		
		for(int i : list) {
			int ans = 0 ;
			for(int k = 0 ; k < 10  ;k++) {
				ans = (ans + dp[i][k]) % INF;
			}
			
			sb.append(ans % INF).append("\n");
		}
		
		System.out.println(sb);
	}

}