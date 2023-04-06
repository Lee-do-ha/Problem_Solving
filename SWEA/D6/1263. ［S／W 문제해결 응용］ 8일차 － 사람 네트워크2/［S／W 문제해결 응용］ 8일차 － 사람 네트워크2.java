import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int INF = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test = 1 ; test <= T ; test++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && map[i][j] == 0) {
						map[i][j] = INF;
					}
				}
			}
	
			// 거쳐가는점
			for(int i = 0 ; i < N ; i++) {
				// 출발점
				for(int j = 0 ; j < N ; j++) {
					if(i == j) continue;
					// 도착점
					for(int k = 0 ; k < N ; k++) {
						if(i == k || j ==k) continue;
						
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			int result = INF;
			
			for(int i = 0 ; i < N ; i++) {
				int sum = 0;
				for(int j = 0 ; j < N ; j++) {
					sum += map[i][j];
				}
				result = Math.min(sum, result);
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
