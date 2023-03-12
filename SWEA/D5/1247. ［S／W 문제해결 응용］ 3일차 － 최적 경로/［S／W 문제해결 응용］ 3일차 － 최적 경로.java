import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	// 고객의 집 저장할 클래스
	static class customer{
		int x;
		int y;
		
		public customer(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
	
	static int ans, N, startX, startY, endX, endY;
	static int[] orders;
	static boolean[] visited;
	static customer[] Customer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T  = Integer.parseInt(st.nextToken());
		
		// 테스트 케이스
		for(int i = 1 ; i <= T ; i++) {
			// 초기결과값 최대값으로 설정
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// 순서대로 들릴 고객의 집 번호 저장할 배열
			orders = new int[N];
			// 들린 고객의 번호 체크하기 위한 배열
			visited = new boolean[N];
			// 고객들 저장할 배열
			Customer = new customer[N];
			
			st = new StringTokenizer(br.readLine());
			// 회사 좌표 = 시작점
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			// 집 좌표 = 도착점
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			for(int k = 0 ; k < N ; k++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				Customer[k] = new customer(a, b);
			}
			
			// 들릴 고객의 번호 순열 뽑기
			Combination(0);
			
			sb.append("#" + i + " " + ans + " \n");
		}
		
		System.out.println(sb);
	}
	
	private static void Combination(int cnt) {
		// 순열 완성 됐을 때
		if(cnt == N) {
			int distance = 0;
			// 시작점에서 첫 고객의 집까지의 거리
			distance += (int)Math.abs(Customer[orders[0]].x - startX);
			distance += (int)Math.abs(Customer[orders[0]].y - startY);
			// 마지막 고객의 집에서 도착점까지의 거리
			distance += (int)Math.abs(Customer[orders[N-1]].x - endX);
			distance += (int)Math.abs(Customer[orders[N-1]].y - endY);
			
			// 고객과 다음 고객의 집 사이의 거리
			for(int i = 1 ; i < N ; i++) {
				distance += (int)Math.abs(Customer[orders[i]].x - Customer[orders[i-1]].x);
				distance += (int)Math.abs(Customer[orders[i]].y - Customer[orders[i-1]].y);
			}
			
			// 현재 저장된 결과가 현재 결과보다 크다면 현재 결과로 
			if(distance < ans) {
				ans = distance;
			}
			return;
		}
		
		// 순열완성하기
		for(int i = 0 ; i < N ; i++) {
			if(visited[i] ==false) {
				orders[cnt] = i;
				visited[i] = true;
				Combination(cnt + 1);
				visited[i] = false;
			}
		}
	}
}
