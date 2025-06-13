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
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// i -> j 로 가는데 걸리는 시간
		int[][] distance = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// s -> e로 갈때 k를 경유해서 가는 경우와 비교
		for (int k = 1; k <= n; k++) {
			for (int s = 1; s <= n; s++) {
				for (int e = 1; e <= n; e++) {
					distance[s][e] = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			if (distance[s][e] <= t) {
				sb.append("Enjoy other party\n");
			} else {
				sb.append("Stay here\n");
			}

		}

		System.out.println(sb);

	}

}