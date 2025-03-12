import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class path implements Comparable<path>{
		int s, e, weight;

		public path(int s, int e, int weight) {
			super();
			this.s = s;
			this.e = e;
			this.weight = weight;
		}

		@Override
		public int compareTo(path o) {
			return this.weight - o.weight;
		}
		
	}
	static int[] parents;
	static boolean[] light;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		light = new boolean[N+1];
		PriorityQueue<path> pq = new PriorityQueue<>();
		PriorityQueue<path> lightOff = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i++) {
			light[Integer.parseInt(st.nextToken())] = true;
		}
		
		for(int i = 1 ; i < N+1 ; i++) {
			parents[i] = i;
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int ans = 0;
		
		while(!pq.isEmpty()) {
			path cur = pq.poll();
						
			if(light[findSet(cur.s)] && light[findSet(cur.e)]) continue;
			
			if(!light[findSet(cur.s)] && !light[findSet(cur.e)]) {
				lightOff.add(cur);
				continue;
			}
			
			unionSet(cur.s, cur.e);
			
			if(light[cur.s] || light[cur.e]) {
				light[cur.s] = true;
				light[cur.e] = true;
			}
			
			ans += cur.weight;
						
			pq.addAll(lightOff);
			lightOff.clear();
		}
		
		System.out.println(ans);
	}
	
	private static void unionSet(int a, int b) {
		int pA = findSet(a);
		int pB = findSet(b);
		
		if(pA == pB) return ;
		if(light[pA] && light[pB]) return;
		
		if(light[pB]) light[pA] = true;
		
		parents[pB] = pA;
	}
	
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
}