import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 4방향 탐색
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 초기 입력값
		char[][] map = new char[N][M];
		boolean[][] light = new boolean[N][M];
		for(int i =  0 ; i < N ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int curX = Integer.parseInt(st.nextToken()) - 1;
		int curY = Integer.parseInt(st.nextToken()) - 1;
		
		String move = br.readLine();
		
		// 모든 이동 완료할때까지 진행
		for(int i = 0 ; i < move.length() ; i++) {
			char curAl = move.charAt(i);
			
			// 와드를 박은 경우
			if(curAl == 'W') {
				
				Queue<int[]> queue = new LinkedList<>();
				queue.add(new int[] {curX, curY});
				
				// 현재 위치 시야 확보
				light[curX][curY] = true;
				
				// 현재 영역
				char dis = map[curX][curY];
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					for(int k = 0 ; k < 4 ; k++) {
						int changeX = cur[0] + dx[k];
						int changeY = cur[1] + dy[k];
						
						if(changeX < 0 || changeY < 0 || changeX >= N || changeY >= M) continue;
						
						// 시야 확보되지 않았으며 현재 영역과 같으면 시야 확보
						if(!light[changeX][changeY] && map[changeX][changeY] == dis) {
							queue.add(new int[] {changeX, changeY});
							light[changeX][changeY] = true;
						}
					}
					
				}
				
			// 4방향 이동
			} else if(curAl == 'L') {
				curY--;
			} else if(curAl == 'R') {
				curY++;
			} else if(curAl == 'U') {
				curX--;
			} else if(curAl == 'D') {
				curX++;
			}
		}
		
		// 이동끝낸후 현재자리로부터 상하좌우 시야 확보
		light[curX][curY] = true;
		for(int k = 0 ; k < 4 ; k++) {
			int changeX = curX + dx[k];
			int changeY = curY + dy[k];
			
			if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;
			
			light[changeX][changeY] = true;
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(light[i][j]) {
					sb.append(".");
				} else {
					sb.append("#");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
}
