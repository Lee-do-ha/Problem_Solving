import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 경로 저장 클래스
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

	// 경로 저장할 리스트
	static List<path>[] paths;
	// 다익스트라에 사용할 변수
	static int[] dijkstra;
	static int INF = 999999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// 총 테스트케이스
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

			// 다익스트라 배열 초기 설정
			dijkstra = new int[n + 1];
			Arrays.fill(dijkstra, INF);

			// 배열 저장할 리스트 초기 설정
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

			// 다익스트라
			dijkstra(s);

            // 후보 지점들 저장
			ArrayList<Integer> candidate = new ArrayList<>();
			for (int i = 0; i < t; i++) {
				candidate.add(Integer.parseInt(br.readLine()));
			}
			
            // 오름차순으로 출력해야하므로 정렬
			Collections.sort(candidate);
			
            // 특정 경로를 지났는지 탐색
			for(int i = 0 ; i < candidate.size() ; i++) {
				if(reverseDijkstra(candidate.get(i), g, h)) {
					sb.append(candidate.get(i) + " ");
				}
			}
			
			sb.append("\n");

		}
		System.out.println(sb);
	}

	// 다익스트라
	private static void dijkstra(int start) {
		PriorityQueue<path> pq = new PriorityQueue<>();

		// 시작점부터 시작
		pq.add(new path(start, 0));
		dijkstra[start] = 0;

		while (!pq.isEmpty()) {
			path cur = pq.poll();
			
			// 이미 최단경로보다 길때는 할 필요 없음
			if(cur.weight > dijkstra[cur.end]) continue;
			
			for(path next : paths[cur.end]) {
				// 이번경로를 통해 가는것이 최단경로일 경우
				if(cur.weight + next.weight < dijkstra[next.end]) {
					dijkstra[next.end] = cur.weight + next.weight;
					pq.add(new path(next.end, cur.weight + next.weight));
				}
			}
			
		}

	}
	
	// reverse다익스트라
	private static boolean reverseDijkstra(int end, int g, int h) {
		PriorityQueue<path> queue = new PriorityQueue<>();
		
		// 후보 지점에서부터 시작
		queue.add(new path(end, dijkstra[end]));
		
		while(!queue.isEmpty()) {
			path cur = queue.poll();
			
			// 이미 계산된 최단경로가 아닌경우 종료
			if(cur.weight != dijkstra[cur.end]) continue;
			
			for(path pre : paths[cur.end]) {
				// 이전 경로까지의 최단경로가 현재까지의 최단경로까지 오는 경로일 경우에만 진행
				if(cur.weight - pre.weight == dijkstra[pre.end]) {
					queue.add(new path(pre.end, cur.weight - pre.weight));
					// 만약 현재 지점과 이전 지점이 냄새를 맡은 경로라면 return true;
					if(pre.end == g && cur.end == h) {
						return true;
					} else if(pre.end == h && cur.end == g) {
						return true;
					}
				}
			}
			
		}
		
		// 냄새를 맡은 경로를 통과하지 못한 경우 return false;
		return false;
	}
}
