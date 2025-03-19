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
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		long time = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			time = time + Long.parseLong(st.nextToken()) - 1;
		}
		
		if(M > time) {
			sb.append("OUT");
		} else {
			sb.append("DIMI");
		}
		
		System.out.println(sb);
	}
	
}