import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	// 마을 정보 저장 클래스
	static class village implements Comparable<village>{
		int location, human;

		public village(int location, int human) {
			super();
			this.location = location;
			this.human = human;
		}

		// 정렬하기 위해 설정
		@Override
		public int compareTo(village o) {
			return Integer.compare(this.location, o.location);
		}
		
	}
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		village[] villages = new village[N];
		
		// 총 인구수의 중간값 찾기위함
		long half = 0;
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			villages[i] = new village(a, b);
			
			half += b;
		}
		
		// 마을 번호순서대로 정렬
		Arrays.sort(villages);
		 
		half = (half + 1) / 2;
		
		// 인구수가 중간값 이상이 되는 마을 위치 찾기
		long humans = 0;
		for(int i = 0 ; i < N ; i++) {
			humans += villages[i].human;
			
			if(humans >= half) {
				System.out.println(villages[i].location);
				return;
			}
		}
		
	}
	
}

