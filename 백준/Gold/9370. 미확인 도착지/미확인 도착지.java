import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class path implements Comparable<path> {
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

	static List<path>[] paths;
	static int[] dijkstra;
	static int INF = 999999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			dijkstra = new int[n + 1];
			Arrays.fill(dijkstra, INF);

			paths = new ArrayList[n + 1];
			for (int i = 1; i < n + 1; i++) {
				paths[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				paths[a].add(new path(b, c));
				paths[b].add(new path(a, c));
			}

			dijkstra(s);

			ArrayList<Integer> candidate = new ArrayList<>();
			for (int i = 0; i < t; i++) {
				candidate.add(Integer.parseInt(br.readLine()));
			}
			
			Collections.sort(candidate);
			
			for(int i = 0 ; i < candidate.size() ; i++) {
				if(reverseDijkstra(candidate.get(i), g, h)) {
					sb.append(candidate.get(i) + " ");
				}
			}
			
			sb.append("\n");

		}
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<path> pq = new PriorityQueue<>();

		pq.add(new path(start, 0));
		dijkstra[start] = 0;

		while (!pq.isEmpty()) {
			path cur = pq.poll();
			
			if(cur.weight > dijkstra[cur.end]) continue;
			
			for(path next : paths[cur.end]) {
				if(cur.weight + next.weight < dijkstra[next.end]) {
					dijkstra[next.end] = cur.weight + next.weight;
					pq.add(new path(next.end, cur.weight + next.weight));
				}
			}
			
		}

	}
	
	private static boolean reverseDijkstra(int end, int g, int h) {
		PriorityQueue<path> queue = new PriorityQueue<>();
		
		queue.add(new path(end, dijkstra[end]));
		
		while(!queue.isEmpty()) {
			path cur = queue.poll();
			
			if(cur.weight != dijkstra[cur.end]) continue;
			
			for(path pre : paths[cur.end]) {
				if(cur.weight - pre.weight == dijkstra[pre.end]) {
					queue.add(new path(pre.end, cur.weight - pre.weight));
					if(pre.end == g && cur.end == h) {
						return true;
					} else if(pre.end == h && cur.end == g) {
						return true;
					}
				}
			}
			
		}
		
		
		return false;
	}
}
