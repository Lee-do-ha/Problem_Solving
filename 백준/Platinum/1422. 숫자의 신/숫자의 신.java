import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

        // 가장 큰수를 여러번 쓰기위해 미리 저장
		int max = 0;
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			max = Math.max(max, arr[i]);
		}
		
        // 문자열 비교하고 큰 순서대로 내림차순 정렬
		PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
			String s1 = o1 + o2;
			String s2 = o2 + o1;
			
			return s2.compareTo(s1);
		});
		
        // 모든 수를 한번씩은 써야하므로 n보다 작을땐 배열의 값을 추가
        // n이상일때는 가장 큰 수 넣기
		for(int i = 0 ; i < m ; i++) {
			if(i < n) {
				pq.add(String.valueOf(arr[i]));
			} else {
				pq.add(String.valueOf(max));
			}
		}
				
        // 최종 출력값
		while(!pq.isEmpty()) {
			sb.append(pq.poll());
		}
		
		System.out.println(sb);

	}

}