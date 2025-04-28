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

		int N = Integer.parseInt(br.readLine());

		long ans = 0;

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		int pre = 0;
		for (int i = 1; i < N; i++) {
			double r = Math.ceil(Math.log(arr[i - 1] / (double) arr[i]) / Math.log(2)) + pre;

			if (r > 0) {
				pre = Math.max(0, (int) r);
				ans += pre;
			} else {
				pre = 0;
			}
		}

		System.out.println(ans);
	}

}