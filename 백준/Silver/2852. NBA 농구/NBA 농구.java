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

		// 이전에 골이 들어간 시간
		int pre = 0;
		// 각 팀이 넣은 골 수
		int aGole = 0;
		int bGole = 0;
		// 각 팀이 이기고 있던 시간
		int aScore = 0;
		int bScore = 0;

		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String[] time = st.nextToken().split(":");
			
			// 골넣은 시간 분으로 계산
			int cur = 60 * Integer.parseInt(time[0]) + Integer.parseInt(time[1]);
			
			// 현재 골 반영하기 전 골의 수
			if(aGole > bGole) {
				aScore += cur - pre;
			} else if(aGole < bGole) {
				bScore += cur - pre;
			}
			
			// 골이 들어간 시간 갱신
			pre = cur;
			
			// 골의 수 갱신
			if(a == 1) {
				aGole++;
			} else {
				bGole++;
			}
			
		}
		
		// 마지막 48분을 기준으로 계산
		if(aGole > bGole) {
			aScore += 48 * 60 - pre;
		} else if (aGole < bGole) {
			bScore += 48 * 60 - pre;
		}
		
		sb.append(change(aScore / 60) + ":" + change(aScore % 60)).append("\n");
		sb.append(change(bScore / 60) + ":" + change(bScore % 60));
		
		System.out.println(sb);

	}
	
	// 한자리수 시간을 위해 문자열 변환
	private static String change(int k) {
		if(k / 10 == 0) {
			String s = "0";
			
			return s + String.valueOf(k);
		}
		
		return String.valueOf(k);
	}

}
