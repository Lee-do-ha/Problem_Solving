import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N ; i++) {
			StringBuilder sb2 = new StringBuilder();
			
			String input = br.readLine();
			
			double cur = Double.parseDouble(input);
			sb2.append(input);
			double reverse = Double.parseDouble(sb2.reverse().toString());
			
			if(Math.sqrt(cur) % 1 == 0 && Math.sqrt(reverse) % 1 == 0) {
				sb.append("YES");
			} else {
				sb.append("NO");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}