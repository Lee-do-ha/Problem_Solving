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
	
	// 경로 저장할 class
	static class path implements Comparable<path>{
		double distance;
		int x, y;
		
		public path(double distance, int x, int y) {
			super();
			this.distance = distance;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(path o) {
			if(this.distance - o.distance < 0 ) return -1;
			return 1;
		}
		
	}
	// 어느 소속인지 표시할 배열
	static int[] parents;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 입력받고 N번째 좌표의 소속은 N번으로 설정
		parents = new int[N+1];
		int[] dx = new int[N + 1];
		int[] dy = new int[N + 1];
		for(int i = 1 ; i < N + 1 ; i++) {
			st = new StringTokenizer(br.readLine());
			dx[i] = Integer.parseInt(st.nextToken());
			dy[i] = Integer.parseInt(st.nextToken());
			
			parents[i] = i;
		}
		
		// 현재 연결된 우주신 갯수
		int connected = 0;
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 만약 다른 소속이라면 같은 소속으로 합치고 연결된 우주신 갯수 추가
			if(findSet(x) != findSet(y)) {
				connected++;
				unionSet(x, y);
			}
		}
		
		// 최종 답안 거리
		double ans = 0;
		
		// 가장 짧은 거리순으로 연결
		PriorityQueue<path> pq = new PriorityQueue<>();
				
		for(int i = 1 ; i < N+1 ; i++) {
			for(int j = i + 1 ; j < N+1 ; j++) {
				
				// 이미 연결되었다면 연결할 필요 X
				if(findSet(i) == findSet(j)) continue;
				
				// 거리 계산
				double distance = Math.sqrt(Math.pow(dx[i] - dx[j], 2) + Math.pow(dy[i] - dy[j], 2));
						
				pq.add(new path(distance, i, j));
			}
		}
		
		// pq가 비거나 연결된 간선 갯수가 N-1개가 되면 종료
		while(!pq.isEmpty() && connected < N-1) {
			path cur = pq.poll();
			
			// 이미 같은 소속이라면 continue
			if(findSet(cur.x) == findSet(cur.y)) continue;
			
			// 같은 소속으로 합치고 거리 추가, 연결 소속 갯수 추가
			unionSet(cur.x, cur.y);
			ans += cur.distance;
			connected++;
		}
		
		System.out.printf("%.2f", Math.round(ans * 100) / 100.0);
		
	}
	
	// 같은 소속으로 합치기
	private static void unionSet(int a, int b) {
		int pA = findSet(a);
		int pB = findSet(b);
		
		parents[findSet(pB)] = pA;
	}
	
	// a의 소속 찾기
	private static int findSet(int a) {
		if(a == parents[a]) return a;
		
		// 해당 소속 찾고 돌아오면서 갱신
		return parents[a] = findSet(parents[a]);
	}
	
}