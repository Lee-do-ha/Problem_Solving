import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 8방향 탐색
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] visited = new boolean[N][M];
		int[][] map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 최종 글자 수
		int ans = 0;
		
		// BFS
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				// 글자인데 방문안한점일 경우 BFS 진행
				if(!visited[i][j] && map[i][j] == 1) {
					queue.add(new int[] {i, j});
					visited[i][j] = true;
					// 글자수 1개 추가
					ans++;
					
					// BFS
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						
						// 8방향 탐색
						for(int k = 0 ; k < 8 ; k++) {
							int changeX = cur[0] + dx[k];
							int changeY = cur[1] + dy[k];
							
							// 범위벗어나는경우
							if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;
							
							// 글자인데 방문안한점일경우 BFS 진행
							if(!visited[changeX][changeY] && map[changeX][changeY] == 1) {
								queue.add(new int[] {changeX, changeY});
								visited[changeX][changeY] = true;
							}
						}
					}
					
				}
			}
		}
		
		System.out.println(ans);
	}
}
