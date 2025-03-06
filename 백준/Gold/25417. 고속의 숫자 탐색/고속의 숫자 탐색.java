import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int ansX, ansY;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		ansX = 0;
		ansY = 0;
		
		// 입력받은 값 저장
		map = new int[5][5];
		for(int i = 0 ; i < 5 ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 5 ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					ansX = i;
					ansY = j;
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
				
		boolean[][] visited = new boolean[5][5];
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {sx, sy});
		visited[sx][sy] = true;
		
		int time = 0;
		
		// 큐가 비어있을떄까지 BFS 진행
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			
			// 현재 저장된 값만큼만 진행
			for(int s = 0 ; s < qSize ; s++) {
				int[] cur = queue.poll();
				
//				System.out.println(time + " ---- " + cur[0] +  ", " + cur[1]);
				
				// 4방향 순환
				for(int k = 0 ; k < 4 ; k++) {
					// 초기값 초기화 
					int changeX = cur[0];
					int changeY = cur[1];
					
					// 5x5칸이므로 for문 5번 진행
					for(int x = 0 ; x < 5 ; x++) {
						changeX += dx[k];
						changeY += dy[k];
						
						// 첫번째에는 상하좌우 모두 갈 수 있으므로 진행
						if(x == 0) {
							// 방문 가능한 지점일 경우 
							if(isPossible(changeX, changeY) && !visited[changeX][changeY]) {
								if(check(changeX, changeY)) {
									System.out.println(++time);
									return ;
								}
								queue.add(new int[] {changeX, changeY});
								visited[changeX][changeY] = true;
							}
						}
						
						// 방문할 수 없는 지점인 경우
						if(!isPossible(changeX, changeY)) {
							int preX = changeX - dx[k];
							int preY = changeY - dy[k];
							
							// 그렇다면 이전 지점에 방문이 가능한 경우
							if(isPossible(preX, preY) && !visited[preX][preY]) {
								if(check(preX, preY)) {
									System.out.println(++time);
									return ;
								}
								queue.add(new int[] {preX, preY});
								visited[preX][preY] = true;
							}
							// 방문 종료
							break;
							
						}
						
						
						// 방문 가능한 경우에 해당 좌표가 7인 경우
						if(isPossible(changeX, changeY) && map[changeX][changeY] == 7) {
							// 방문 하지 않았을때만 진행
							if(!visited[changeX][changeY]) {
								queue.add(new int[] {changeX, changeY});
								visited[changeX][changeY] = true;
							}
							// 7을 만났으므로 종료
							break;
						}
						
					}
					
				}
				
			}
			
			time++;
		}
		
		System.out.println(-1);
		
	}
	
	// 해당 좌표가 도착하려는 지점인지 검사
	private static boolean check(int x, int y) {
		
		if(x == ansX && y == ansY) return true;
		
		return false;
	}

	// 해당 좌표가 이동할 수 있는 지점인지 검사
	static boolean isPossible(int x, int y) {
		
		if(x < 0 || x >= 5 || y < 0 || y >= 5 || map[x][y] == -1) return false;
		
		return true;
	}
	
}