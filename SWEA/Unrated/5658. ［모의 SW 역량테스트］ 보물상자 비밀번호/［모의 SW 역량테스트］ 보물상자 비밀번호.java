import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test = 1 ; test < T+1 ; test++) {
			long result = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			String str = br.readLine();
			
			HashSet<String> hash = new HashSet<>();
			
			String strsum = str+str;
			
//			System.out.println(strsum);
			
			for(int k = 0 ; k < N/4 ; k++) {
				for(int i = 0 ; i < N ; i += N/4) {
					hash.add(strsum.substring(0+i+k, N/4+i+k));
				}
			}
			
//			System.out.println(hash);
			
			String ox = "0x";
			
			ArrayList<Long> List = new ArrayList<>();
			
			for(String i : hash) {
				List.add(Long.decode(ox+i));
			}
			
			Collections.sort(List, Collections.reverseOrder());
			
//			System.out.println(List);
									
			sb.append("#" + test + " " + List.get(M-1)).append("\n");
		}
		System.out.println(sb);
	}
}