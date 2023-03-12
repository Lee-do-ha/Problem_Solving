import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
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
			
			Arrays.sort(Path);
			
			long result = 0 ;
			int count = 0;
			
			for(int i = 0 ; i < M ; i++) {
				if(findSet(Path[i].start) == findSet(Path[i].end)) {
					continue;
				}else {
					union(Path[i].start , Path[i].end);
					result += Path[i].weight;
				}
				
				if(++count == N-1) break;
			}
			
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	private static int findSet(int n) {
		if(parents[n] == n) return n;
		
		return parents[n] = findSet(parents[n]);
	}
	
	private static void union(int a, int b) {
		if(findSet(a) == findSet(b)) {
			return;
		}else {
			parents[findSet(a)] = findSet(b);
		}
	}
}