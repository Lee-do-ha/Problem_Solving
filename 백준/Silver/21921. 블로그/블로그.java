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

		int max = 0;

		int[] arr = new int[n + 1];
		int[] sum = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			sum[i] = sum[i - 1] + arr[i];
			
			if(i >= m) {
				max = Math.max(max, sum[i] - sum[i -m]);
			}
		}
				
		if(max == 0) {
			System.out.println("SAD");
			return;
		}
		
		int count = 0;
		
		for(int i = m ; i < n + 1 ; i++) {
			if(sum[i] - sum[i - m] == max) count++;
		}
		
		System.out.println(max);
		System.out.println(count);

	}

}