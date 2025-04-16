import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		int distance = 0;
		boolean[][] visited = new boolean[N][M];

		Queue<int[]> queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				int t = map[i][j];
				
				if(t == 0) continue;
				ans = Math.max(ans, 2);
				
				queue.add(new int[] {i, j, 0});
				visited[i][j] = true;
				
//				System.out.println("START : " + i + ", " + j + " -> " + map[i][j]);
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
						
					if(cur[2] > distance) {
						distance = Math.max(distance, cur[2]);
						ans = map[cur[0]][cur[1]] + map[i][j];
					} else if(cur[2] == distance) {
						ans = Math.max(ans, map[i][j] + map[cur[0]][cur[1]]);
					}
					
					for(int k = 0 ; k < 4 ; k++) {
						int changeX = cur[0] + dx[k];
						int changeY = cur[1] + dy[k];
						
						if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M || visited[changeX][changeY] || map[changeX][changeY] == 0) continue;
						
						queue.add(new int[] {changeX, changeY, cur[2] + 1});
						visited[changeX][changeY] = true;
//						System.out.println(changeX + " " + changeY + " 좌표 " + (cur[2] + 1) + "에 방문");
						
					}
				}
				
				for(int k = 0 ; k < N ; k++) {
					Arrays.fill(visited[k], false);
				}
				
			}
		}
		
		System.out.println(ans);

	}

}