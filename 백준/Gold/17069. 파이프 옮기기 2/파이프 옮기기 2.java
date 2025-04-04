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

		int[][] map = new int[N][N];

		// 맵 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp 배열
		long[][][] dp = new long[N][N][3];

		// 가로로만 가능 지점
		// 만약 돌이 있다면 Break
		for (int i = 1; i < N; i++) {
			if (map[0][i] == 1)
				break;
			dp[0][i][0] = 1;
		}

		// DP 탐색
		for (int i = 1; i < N; i++) {
			for (int j = 2; j < N; j++) {
				// 돌이 있다면 종료
				if (map[i][j] == 1)
					continue;

				// 각자 방향으로 파이프가 올 수 있는 지점 추가
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
				dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];

				// 만약 돌때문에 대각선 이동이 안되는 경우
				if (map[i - 1][j] == 1 || map[i][j - 1] == 1) {
					dp[i][j][1] = 0;
				}

			}

		}

		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}

}
