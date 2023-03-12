import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	// 출발 정점, 도착 정점, 가중치 저장할 클래스
	// Kruskal's Algorithm 사용하려면 가중치 오름차순으로 정렬해야하므로 compareTo 사용
	static class path implements Comparable<path>{
		int start, end, weight;
		public path(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(path o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	// 조상 저장할 배열
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1 ; test_case <= T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			path[] Path = new path[M];
			parents = new int [N+1];
			
			for(int i = 1 ; i < N+1 ; i++) {
				parents[i] = i;
			}
			
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				Path[i] = new path(a, b, c);
			}
			
			// 가중치 순으로 정렬
			Arrays.sort(Path);
			
			// 결과가 될 최소 비용
			long result = 0 ;
			
			// 연결된 간선의 갯수
			int count = 0;
			
			// 가중치가 낮은 것부터 시작 정점과 도착 정점이 연결되있는지 체크하고 안되어있으면 연결하고 연결된 간선 갯수 1개 플러스
			for(int i = 0 ; i < M ; i++) {
				if(findSet(Path[i].start) == findSet(Path[i].end)) {
					continue;
				}else {
					union(Path[i].start , Path[i].end);
					result += Path[i].weight;
				}
				
				// 총 N개의 정점 연결해야하므로 N-1개의 간선이면 모두 연결됨
				if(++count == N-1) break;
			}
			
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	// 조상 찾을 메소드
	private static int findSet(int n) {
		if(parents[n] == n) return n;
		
		return parents[n] = findSet(parents[n]);
	}
	
	// 조상 통합할 
	private static void union(int a, int b) {
		if(findSet(a) == findSet(b)) {
			return;
		}else {
			parents[findSet(a)] = findSet(b);
		}
	}
}
