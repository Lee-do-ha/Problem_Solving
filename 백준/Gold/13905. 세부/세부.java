import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 다리 저장할 클래스
	// 최대힙 사용하기 위해 정렬 설정
	static class path implements Comparable<path> {
		int end, weight;

		public path(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(path o) {
			return o.weight - this.weight;
		}

	}
	static ArrayList<path>[] paths;
	static int MAX = 1000001;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 다리 저장할 배열
		paths = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			paths[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		// 다리 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			paths[a].add(new path(b, c));
			paths[b].add(new path(a, c));
		}

		// 다익스트라에 사용할 배열과 힙
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 0);
		PriorityQueue<path> pq = new PriorityQueue<>();

		dp[s] = MAX;
		pq.add(new path(s, MAX));

		// 다익스트라
		while (!pq.isEmpty()) {
			path cur = pq.poll();
			
			// 현재 빼빼로가 가려는 지점 빼빼로 보다 적은 경우 방문할 필요가 없음
			if(cur.weight < dp[cur.end]) continue;
			
			// 다음 지점을 방문하는 경우
			for(path next : paths[cur.end]) {
				int max = Math.min(cur.weight, next.weight);
				
				// 이번에 가지고 갈 수 있는 빼빼로가 다음 지점 빼빼로보다 많은 경우
				if(max > dp[next.end]) {
					pq.add(new path(next.end, max));
					dp[next.end] = max;
				}
			}
			
		}
		
		System.out.println(dp[e]);
	}

}
