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
		
		long ans = 0;
		long idx = 1;
		long k = 10;
		
		for(int i = 1 ; i <= N ; i++) {
			if(i % k == 0) { 
				idx++;
				k *= 10;
			}
			
			ans += idx;
		}
		System.out.println(ans);
		
	}
	
}