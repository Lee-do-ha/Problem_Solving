import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 문자 정렬하기 위함
	static class node implements Comparable<node>{
		String word;

		public node(String word) {
			super();
			this.word = word;
		}

		@Override
		public int compareTo(node o) {
			String a = this.word + o.word;
			String b = o.word + this.word;
			return b.compareTo(a);
		}
		
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		
		// 정렬하기 위한 PriorityQueue
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			String input = st.nextToken();
			
			pq.add(new node(input));
			
		}
		
		// 정답 문자열
		String ans = "";
		// 모두가 0인지 체크하기 위함
		boolean flag = false;
		
		// 문자열 붙히기
		while (!pq.isEmpty()) {
			String cur = pq.poll().word;
			
			// 0이 아니라면 flag 처리
			if(!cur.equals("0")) flag = true;
			
			ans += cur;
		}
		
		// 모든수가 0인 경우엔 0 출력
		if(!flag) {
			System.out.println(0);
			return ;
		}
		
		System.out.println(ans);
	}

}
