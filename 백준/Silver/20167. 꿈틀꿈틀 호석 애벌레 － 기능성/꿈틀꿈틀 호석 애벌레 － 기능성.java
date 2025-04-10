import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// DP 사용 배열
		int[] dp = new int[N + 1];

		// 이동하는 좌표와 누적합
		int left = 0;
		int right = 1;
		int sum = arr[0];

		// 끝점까지 이동
		while (right <= N) {
			// 합이 M 이상일때
			if (sum >= M) {
				// 합이 M 이상일때까지
				while (sum >= M) {
					// 이전에 저장된 값과 현재 위치에서 축적된 양 비교
					dp[right] = Math.max(dp[right], dp[left] + sum - M);
					// 왼쪽 좌표 이동
					sum -= arr[left++];
				}
			// 합이 M 미만일때
			} else {
				// DP 비교
				dp[right] = Math.max(dp[right], dp[right - 1]);
				// 끝점에 도착했다면 종료
				if(right == N) break;
				// 오른쪽 좌표 이동
				sum += arr[right++];
				
			}
		}

		System.out.println(dp[N]);

	}

}