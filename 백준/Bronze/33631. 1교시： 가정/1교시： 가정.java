import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int a1, a2, a3, a4, b1, b2, b3, b4;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// 가지고 있는 재료의 양
		st = new StringTokenizer(br.readLine());
		a1 = Integer.parseInt(st.nextToken());
		a2 = Integer.parseInt(st.nextToken());
		a3 = Integer.parseInt(st.nextToken());
		a4 = Integer.parseInt(st.nextToken());
		
		// 만드는데 필요로 하는 재료의 양
		st = new StringTokenizer(br.readLine());
		b1 = Integer.parseInt(st.nextToken());
		b2 = Integer.parseInt(st.nextToken());
		b3 = Integer.parseInt(st.nextToken());
		b4 = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		
		// 총 만든 쿠키의 양
		int clear = 0;
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// 각 명령어 처리
			if(command == 1) {
				
				if(canMake(k)) {
					a1 -= b1 * k;
					a2 -= b2 * k;
					a3 -= b3 * k;
					a4 -= b4 * k;
					
					clear += k;
					
					sb.append(clear).append("\n");
				} else {
					sb.append("Hello, siumii").append("\n");
				}
				
			} else if (command == 2) {
				a1 += k;
				sb.append(a1).append("\n");
			} else if (command == 3) {
				a2 += k;
				sb.append(a2).append("\n");
			} else if (command == 4) {
				a3 += k;
				sb.append(a3).append("\n");
			} else {
				a4 += k;
				sb.append(a4).append("\n");
			}
		}
		
		System.out.println(sb);

	}
	
	// k개의 쿠키를 만들 수 있는지 확인하는 함수
	private static boolean canMake(int k) {
		if(a1 < b1 * k) return false;
		if(a2 < b2 * k) return false;
		if(a3 < b3 * k) return false;
		if(a4 < b4 * k) return false;
		
		return true;
	}

}
