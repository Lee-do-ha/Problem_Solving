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
		
        // 점수 저장 배열
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		long s = Long.parseLong(st.nextToken());
		
		// 최종 출력값
		int k = -1;
		
        // 이분탐색 하기위한 변수
		int left = 1;
		int right = 110000; 
		
        // 이분탐색
		while(left < right) {
			int half = (left + right) / 2;
			
			long sum = 0;
			
            // 점수 구하기
			for(int i = 0 ; i < N ; i++) {
				int cur = arr[i];
				
				if(cur > half + r) {
					cur -= p;
				} else if(cur < half) {
					cur += q;
				}
				
				sum += cur;
			}
			
            // 문제 조건 만족하는 경우 k값 변경
			if(sum >= s) {
				right = half;
				
				if(half > 0) {
					k = half;
				}
			} else {
				left = half + 1;
			}
			
		}
		
		System.out.println(k);
	}
	
	
}

