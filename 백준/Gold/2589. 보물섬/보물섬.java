import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 상하좌우 이동하기 위한 변수 저장
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 입력받은 값 저장
		char[][] map = new char[N][M];
		boolean[][] visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// BFS실행하기위한 큐
		Queue<int[]> queue = new LinkedList<>();
		int ans = 0;
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				
				// 만약 현재 지점이 보물이라면 방문탐색실행
				if(map[i][j] == 'L') {
					visited[i][j] = true;
					queue.add(new int[] {i, j});
									
					// 이번 지점에서 갈 수 있는 최대 거리
					int distance = -1;
					
					
					// BFS
					while(!queue.isEmpty()) {
						int curSize = queue.size();
						
						for(int k = 0 ; k < curSize ; k++) {
							int[] cur = queue.poll();
							
							for(int x = 0 ; x < 4 ; x++) {
								int changeX = cur[0] + dx[x];
								int changeY = cur[1] + dy[x];
								
								if(changeX >= 0 && changeX < N && changeY >= 0 && changeY < M) {
									if(map[changeX][changeY] == 'L' && !visited[changeX][changeY]) {
										visited[changeX][changeY] = true;
										queue.add(new int[] {changeX, changeY});
									}
								}
							}
						}
						// 한 사이클 돌때마다 거리++
						distance++;
						
						// 최종답안 갱신
						ans = Math.max(ans, distance);
					}
					
					// 다시 다른 지점 찾아보기위해 방문배열 초기화
					for(int x = 0 ; x < N ; x++) {
						Arrays.fill(visited[x], false);
					}
					
				}
			}
		}
		
		
		System.out.println(ans);
		
	}
	
}