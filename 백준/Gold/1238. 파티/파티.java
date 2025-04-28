import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 도로 저장할 클래스
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
	// 도로 저장할 배열
	static ArrayList<path>[] lists;
	// 최댓값
	static int INF = 9999999;
    // 다익스트라용 PQ
	static PriorityQueue<path> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		lists = new ArrayList[n + 1];
		for(int i = 1 ; i < n + 1 ; i++) {
			lists[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 단방향이므로 한쪽에만 넣기
			lists[a].add(new path(b, c));
		}
		
		// 갔다오는 경우이므로 x에서 다익스트라 돌리기
		int[] dijkstraX = new int[n + 1];
		dijkstra(x, dijkstraX);
		
		// 최종 출력값
		int ans = 0;
		
		// x를 제외헌 모든 지점에서 다익스트라 돌리기
		int[] dijkstra = new int[n + 1];
		for(int i = 1 ; i < n + 1 ; i++) {
			if(i == x) continue;
			
			dijkstra(i, dijkstra);
			
			// 갔다오는 거리가 가장 긴 거리를 저장
			ans = Math.max(dijkstra[x] + dijkstraX[i], ans);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dijkstra(int start, int[] arr) {
		Arrays.fill(arr, INF);
		arr[start] = 0;
				
		pq.add(new path(start, 0));
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
			
			if(cur.weight > arr[cur.end]) continue;
			
			for(path next : lists[cur.end]) {
				if(cur.weight + next.weight < arr[next.end]) {
					pq.add(new path(next.end, cur.weight + next.weight));
					arr[next.end] = cur.weight + next.weight;
				}
			}
		}
		
	}

}