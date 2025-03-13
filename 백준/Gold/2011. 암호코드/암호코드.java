import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		String input = br.readLine();
		int length = input.length();
		char[] words = input.toCharArray();
		
		// 나누기 해줄 값
		int INF = 1000000;
		
		// DP 배열
		int[][] dp = new int[length][2];
		
		// 시작값이 0이라면 잘못된 암호
		if(words[0] - 48 == 0) {
			System.out.println(0);
			return ;
		}
		
		// 처음 시작할땐 무조건 1개로 만드는 암호 1개
		dp[0][0] = 1;
		for(int i = 1 ; i < length ; i++) {
			
			// 이전 숫자와 이번 숫자 아스키 코드 변환
			int pre = words[i - 1] - 48;
			int cur = words[i] - 48;
			
			// 현재 0이 아닐때는 무조건 한자리로 만들 수 있음
			if(cur != 0) {
				dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % INF;
			// 현재 0인 경우에는 앞 숫자에 1이나 2밖에 올 수가 없음
			} else {
				if(pre != 1 && pre != 2) {
					System.out.println(0);
					return ;
				}
			}
			
			// 이전이 1이라면 이번에 아무숫자나와도 2자리로 변환 가능
			if(pre == 1) {
				dp[i][1] = dp[i-1][0] % INF;
			// 이전이 2라면 6이하의 숫자만 2자리로 변환 가능
			} else if(pre == 2 && cur < 7) {
				dp[i][1] = dp[i-1][0] % INF;
			}
		}
		
		System.out.println((dp[length-1][0] + dp[length-1][1]) % INF);
		
	}
	
}