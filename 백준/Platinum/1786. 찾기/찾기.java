import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tLength = text.length;
		int pLength = pattern.length;
		
		// pattern의 좌표에서 다시 돌아가는 좌표 저장
		int[] table = new int[pLength];
		
		// 테이블 설정
		for(int i = 1, j = 0; i < pLength; i++) {
			while(j > 0 && pattern[i] != pattern[j]) {
				j = table[j-1];
			}
			
			if(pattern[i] == pattern[j]) {
				table[i] = ++j;
			}else {
				table[i] = 0;
			}
		}
		
		// 똑같은 pattern의 갯수
		int cnt = 0;
		
		// 똑같은 pattern 찾기
		for(int i = 0, j = 0; i < tLength; ++i) {
			
			while(j > 0 && text[i] != pattern[j]) j = table[j-1];
			
			if(text[i] == pattern[j]) {
				if(j == pLength - 1) {
					cnt++;
					sb.append(i - j + 1).append("\n");
					j = table[j];
				}else {
					j++;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
		
	}

}

