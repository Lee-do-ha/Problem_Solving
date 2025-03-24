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
		
		long[] arr = new long[1000001];
		
		arr[0] = 1;
		
		for(int i = 1 ; i < 1000001 ; i++) {
			int cur = i;
			
			arr[i] = count(i) + arr[i-1];
			
		}
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start == 0) {
				sb.append(arr[end]).append("\n");
			} else {
				sb.append(arr[end] - arr[start - 1]).append("\n");
			}
		}
		
		System.out.println(sb);
		
	}
	
	private static int count(int k) {
		
		int count = 0;
		
		while(k > 0) {
			if(k % 10 == 0) count++;
			
			k /= 10;
		}
		
		return count;
		
	}
	
}

