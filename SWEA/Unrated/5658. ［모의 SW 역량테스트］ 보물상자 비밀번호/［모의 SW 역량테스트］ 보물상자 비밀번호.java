import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
H
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
			
			// 문자열 중복을 해결하기 위해 HastSet에 문자열 저장
			HashSet<String> hash = new HashSet<>();
			
			// 문자열 돌리기 위해 문자열 2개 더하기
			String strsum = str+str;
			
			// 문자열의 크기가 N이라 할때 N/4의 크기로 자르면서 HastSet에 저장
			for(int k = 0 ; k < N/4 ; k++) {
				for(int i = 0 ; i < N ; i += N/4) {
					hash.add(strsum.substring(0+i+k, N/4+i+k));
				}
			}
			
			// 16진수를 10진수로 변환하기 위해 추가해줄 문자열
			String ox = "0x";
			
			// 10진수로 바뀐 수 저장할 ArrayList
			ArrayList<Long> List = new ArrayList<>();
			
			// 10진수로 변환후 ArrayList에 저장
			for(String i : hash) {
				List.add(Long.decode(ox+i));
			}
			
			// 내림차순으로 정렬
			Collections.sort(List, Collections.reverseOrder());
			
			// M번째로 큰 숫자 출력
			sb.append("#" + test + " " + List.get(M-1)).append("\n");
		}
		System.out.println(sb);
	}
}
