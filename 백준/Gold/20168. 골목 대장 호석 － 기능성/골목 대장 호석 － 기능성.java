import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class path {
		int end, weight;

		public path(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}

	}

	static class node implements Comparable<node> {
		int end, weight, shame;

		public node(int end, int weight, int shame) {
			super();
			this.end = end;
			this.weight = weight;
			this.shame = shame;
		}

		@Override
		public int compareTo(node o) {
			if (this.shame == o.shame) {
				return this.end - o.end;
			}
			return this.shame - o.shame;
		}

	}

	static int[] dijkstra;
	static int ans;
	static ArrayList<path>[] pLists;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		pLists = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			pLists[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			pLists[a].add(new path(b, c));
			pLists[b].add(new path(a, c));
		}

		ans = Integer.MAX_VALUE;

		dijkstra = new int[N + 1];

		dijkstra(s, e, C);

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	private static void dijkstra(int start, int end, int money) {

		PriorityQueue<node> pq = new PriorityQueue<>();

		pq.add(new node(start, 0, 0));

		Arrays.fill(dijkstra, 1001);
		dijkstra[start] = 0;

		while (!pq.isEmpty()) {
			node cur = pq.poll();

//			System.out.println(cur.end + "까지 " + cur.weight + "의 비용 들어서 " + cur.shame + "의 수치심");

			if (cur.shame > dijkstra[cur.end])
				continue;

			if (cur.end == end) {
				ans = Math.min(ans, cur.shame);
				continue;
			}

			for (path next : pLists[cur.end]) {
				if (cur.weight + next.weight > money)
					continue;

				if (cur.shame > dijkstra[next.end])
					continue;

				if (Math.max(cur.shame, next.weight) < dijkstra[next.end]) {
					dijkstra[next.end] = Math.max(cur.shame, next.weight);
					pq.add(new node(next.end, cur.weight + next.weight, dijkstra[next.end]));
				}
			}

		}

	}

}