import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 사전에 배열에 넣을 최댓값
		int INF = 999999999;

		// DP 배열 초기 설정
		int[] dp = new int[M + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;

		// DP
		for (int i = 0; i < N; i++) {
			// 입력된 동전
			int money = Integer.parseInt(br.readLine());

			// 해당 금액부터 DP 수행
			for (int k = money; k < M + 1; k++) {
				dp[k] = Math.min(dp[k], dp[k - money] + 1);
			}

		}

		// 최댓값이 나온다면 만들 수 없는 금액이므로 -1 출력
		if (dp[M] == INF) {
			System.out.println(-1);
		} else {
			System.out.println(dp[M]);
		}

	}

}
