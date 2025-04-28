import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

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
	static ArrayList<path>[] lists;
	static int INF = 9999999;
	
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
			
			lists[a].add(new path(b, c));
		}
		
		int[] dijkstraX = new int[n + 1];
		dijkstra(x, dijkstraX);
		
		int ans = 0;
		
		int[] dijkstra = new int[n + 1];
		for(int i = 1 ; i < n + 1 ; i++) {
			if(i == x) continue;
			
			dijkstra(i, dijkstra);
			
			ans = Math.max(dijkstra[x] + dijkstraX[i], ans);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dijkstra(int start, int[] arr) {
		Arrays.fill(arr, INF);
		arr[start] = 0;
		
		PriorityQueue<path> pq = new PriorityQueue<>();
		
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