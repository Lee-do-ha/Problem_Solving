import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
        // 정답 문자열 넣을 리스트
		ArrayList<Character> list = new ArrayList<>();
		
		for(int i = 0 ; i < N ; i++) {
            // 입력 문자
			String str = br.readLine();
			
            // 문자 길이
			int length = str.length();
			
            // 첫 단어 넣고 시작
			char pre = str.charAt(0);
			list.add(pre);
			
			for(int k = 1 ; k < length ; k++) {
				char cur = str.charAt(k);
				
                // 현재 단어와 이전 들어가있는 단어가 같은 경우
				if(pre == cur) continue;
				
                // 현재 단어 넣고 단어 갱신
				list.add(cur);
				pre = cur;
				
			}
			
            // 출력해야하는 문자열
			String ans = "";
			for(char k : list) {
				ans += k;
			}
			
			sb.append(ans).append("\n");
			
            // 리스트 초기화
			list.clear();
		}
		
		System.out.println(sb);
		
	}
	
}