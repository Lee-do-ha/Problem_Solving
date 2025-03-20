import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		if(N == 0) {
			System.out.println(0);
			return;
		}
		
		int[] dp = new int[N + 1];
		dp[1] = 1;
		
		for(int i = 2 ; i < N + 1 ; i++) {
			dp[i] = dp[i-2] + dp[i-1];
		}
		System.out.println(dp[N]);
	}
	
}

