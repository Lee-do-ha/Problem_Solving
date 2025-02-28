import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int MAX = 999999999;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		char[] arr = str.toCharArray();

		int[] dp = new int[N];
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		
		for(int i = 0 ; i < N ; i++) {
			char cur = arr[i];
			char next = 'B';
			
			switch (cur) {
				case 'B': 
					next = 'O';
					break;
				case 'O': 
					next = 'J';
					break;
			}
						
			for(int j = i + 1 ; j < N ; j++) {
				if(arr[j] == next) {
					dp[j] = Math.min(dp[j], dp[i] + (int) Math.pow(j-i, 2));
				}
			}
		}

		if(dp[N-1] == MAX) {
			sb.append(-1);
		} else {
			sb.append(dp[N-1]);
		}
		
		System.out.println(sb);
		
	}
	
	
}