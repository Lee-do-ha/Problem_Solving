import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 경로 저장 class
	static class path implements Comparable<path>{
		int end, weight;

		public path(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(path o) {
			return this.weight - o.weight;
		}
		
	}
	static ArrayList<path>[] paths;
	// 다익스트라 이용할 변수들
	static int[] dijk;
	static int INF = 200000000;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 경로 저장할 배열
		paths = new ArrayList[N + 1];
		for(int i = 1 ; i < N + 1 ; i++) {
			paths[i] = new ArrayList<>();
		}
		
		// 경로 배열에 추가
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			paths[s].add(new path(e, w));
			paths[e].add(new path(s, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int s1 = Integer.parseInt(st.nextToken());
		int s2 = Integer.parseInt(st.nextToken());
		
		// 1 -> s1 -> s2 -> N
		int ans1 = dijkstra(1, s1, N) + dijkstra(s1, s2, N) + dijkstra(s2, N, N);
		// 1-> s2 -> s1 -> N
		int ans2 = dijkstra(1, s2, N) + dijkstra(s2, s1, N) + dijkstra(s1, N, N);
		
		// INF보다 크거나 같다면 경로가 없다는 뜻
		if(ans1 >= INF && ans2 >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(ans1, ans2));
		}
		
		
	}

	// 다익스트라
	private static int dijkstra(int s, int e, int N) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		dijk = new int[N + 1];
		
		Arrays.fill(dijk, INF);
		dijk[s] = 0;
		
		pq.add(new int[] {s, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int curEnd = cur[0];
			int curWeight = cur[1];
			
			if(curWeight > dijk[curEnd]) continue;
			
			for(path p : paths[curEnd]) {
				int nextEnd = p.end;
				int nextCost = p.weight;
				
				if(curWeight + nextCost < dijk[nextEnd]) {
					dijk[nextEnd] = curWeight + nextCost;
					pq.add(new int[] {nextEnd, dijk[nextEnd]});
				}
			}
			
		}
				
		return dijk[e];
	}
	
	
}