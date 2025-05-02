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

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = 0; i < m; i++) {
			sum += arr[i];
		}

		ans = Math.max(ans, sum);

		if (m < n) {
			for (int i = m; i < n; i++) {
				sum += arr[i];
				sum -= arr[i - m];

				ans = Math.max(ans, sum);

			}
		}

		System.out.println(ans);
	}

}
