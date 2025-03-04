import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class node implements Comparable<node>{
		int start, end, weight;

		public node(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(node o) {
			return this.weight - o.weight;
		}
		
	}
	static int MAX = 999999999;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] dijkstra = new int[V];
		boolean[] visitedM = new boolean[V];
		ArrayList<node>[] nodes = new ArrayList[V];
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int i = 0; i < V ; i++) {
			visitedM[i] = false;
			dijkstra[i] = MAX;
			nodes[i] = new ArrayList<node>();
		}
		
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			nodes[s].add(new node(s, e, weight));
			nodes[e].add(new node(e, s, weight));
		}
		
		dijkstra[0] = 0;
		visitedM[P-1] = true;
		for(int i = 0 ; i < nodes[0].size() ; i++) {
			pq.add(nodes[0].get(i));
		}
		
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			
//			System.out.println("node = " + cur.start + " " + cur.end + " " + cur.weight);
			
			if(dijkstra[cur.start] + cur.weight < dijkstra[cur.end]) {
				dijkstra[cur.end] = dijkstra[cur.start] + cur.weight;
				
				if(cur.end != P-1) {
					visitedM[cur.end] = visitedM[cur.start];
				}
				
				for(int i = 0 ; i < nodes[cur.end].size() ; i++) {
					pq.add(nodes[cur.end].get(i));
				}
								
			} else if(dijkstra[cur.start] + cur.weight == dijkstra[cur.end]) {
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