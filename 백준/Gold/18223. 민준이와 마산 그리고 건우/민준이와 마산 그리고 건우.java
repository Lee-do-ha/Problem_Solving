import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	
	// node 저장할 크래스
	static class node implements Comparable<node>{
		
		// 출발점, 도착점, 가중치
		int start, end, weight;

		public node(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		// PriorityQueue 사용하기 위해 가중치순으로 정렬
		@Override
		public int compareTo(node o) {
			return this.weight - o.weight;
		}
		
	}
	// 초기 dijkstra값 저장할 MAX
	static int MAX = 999999999;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		// dijkstra 최단 경로 값 저장
		int[] dijkstra = new int[V];
		// 해당 지점까지 가는 경로에 건우가 관여했는지 여부 체크
		boolean[] visitedM = new boolean[V];
		ArrayList<node>[] nodes = new ArrayList[V];
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		// 초기값 세팅
		for(int i = 0; i < V ; i++) {
			visitedM[i] = false;
			dijkstra[i] = MAX;
			nodes[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			nodes[s].add(new node(s, e, weight));
			nodes[e].add(new node(e, s, weight));
		}
		
		// 출발점 세팅
		dijkstra[0] = 0;
		// 건우 위치 세팅
		visitedM[P-1] = true;
		for(int i = 0 ; i < nodes[0].size() ; i++) {
			pq.add(nodes[0].get(i));
		}
		
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			
			// 새로운 최단경로를 찾았을 때
			if(dijkstra[cur.start] + cur.weight < dijkstra[cur.end]) {
				dijkstra[cur.end] = dijkstra[cur.start] + cur.weight;
				
				// 새로운 경로가 건우 위치가 아닌경우 이전 위치의 방문값과 동일하게 세팅
				if(cur.end != P-1) {
					visitedM[cur.end] = visitedM[cur.start];
				}
				
				for(int i = 0 ; i < nodes[cur.end].size() ; i++) {
					pq.add(nodes[cur.end].get(i));
				}
								
			}
			// 또 다른 최단경로의 루트를 찾았을 때는 해당 루트가 건우를 거친 경우 방문값 다시 세팅
			else if(dijkstra[cur.start] + cur.weight == dijkstra[cur.end]) {
				if(visitedM[cur.start] && cur.end != P-1) { 
					visitedM[cur.end] = true;
				}
				
				
			}
			
		}
		
		
		if(visitedM[V-1]) {
			sb.append("SAVE HIM");
		} else {
			sb.append("GOOD BYE");
		}
		
		System.out.println(sb);
		
	}
	
}