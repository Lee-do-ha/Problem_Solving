import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
    // 고속도로 저장할 class
    // 정렬하기 위해 Comparable 사용
	static class node implements Comparable<node>{
		int s, e, w;

		public node(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(node o) {
			return this.w - o.w;
		}
		
	}
	static int[] parents;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

        // 본인의 소속 저장할 배열
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
		
        // 정렬하기위한 PriorityQueue
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new node(s, e, w));
		}
		
        // 출력해야 할 최대 비용의 고속도로
		int ans = 0;
		
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			
            // 연결할 필요가 없는 도로라면 continue
			if(findSet(cur.s) == findSet(cur.e)) continue;
			
            // 고속도로 연결
			unionSet(cur.s, cur.e);
			
            // 출력값 갱신
			ans = Math.max(ans, cur.w);
		}
		
		System.out.println(ans);

	}
	
    // 본인의 소속 찾기
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
    // 소속 합치기
	private static void unionSet(int a, int b) {
		int pA = findSet(a);
		int pB = findSet(b);
		
		if(pA == pB) return;
		
		parents[pB] = pA;
	}
}
