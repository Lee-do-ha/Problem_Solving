import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 4방향 이동하기 위한 배열
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 물건 갯수와 시작 위치
		int need = 0;
		int sX = 0;
		int sY = 0;
		
		HashMap<Integer, Integer> hashMap = new HashMap<>();

		char[][] map = new char[M][N];
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);

				// 물건 갯수 추가
				if (map[i][j] == 'X') {
					hashMap.put(i * N * M + j, need++);
				}

				// 시작점 설정
				if (map[i][j] == 'S') {
					sX = i;
					sY = j;
				}

			}
		}

		// 3차원으로 방문체크
		boolean[][][] visited = new boolean[M][N][1 << need];

		// BFS 위한 Queue
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작지점으로 시작
		queue.add(new int[] {sX, sY, 0});
		
		// 총 소요된 시간
		int time = 0;
		
		// 탈출 여부
		boolean flag = false;
		
		// BFS 실행
		while (!queue.isEmpty() && !flag) {
			
			// 현재 큐 사이즈만큼만 진행 - 1타임
			int size = queue.size();
			
			for(int i = 0 ; i < size ; i++) {
				int[] cur = queue.poll();
				
				// 만약 탈출구이면서 모든 물건을 챙겼다면 탈출
				if(map[cur[0]][cur[1]] == 'E' && (cur[2] ^ (int) (Math.pow(2, need) - 1)) == 0) {
					flag = true;
					break;
				}
				
				// 4방향 탐색
				for(int k = 0 ; k < 4 ; k++) {
					int changeX = cur[0] + dx[k];
					int changeY = cur[1] + dy[k];
					
					// 범위벗어났다면 continue
					if(changeX < 0 || changeX >= M || changeY < 0 || changeY >= N) continue;
					
					// 방문하지않은 지점이면서 방문할 수 있는 경우
					if(!visited[changeX][changeY][cur[2]] && map[changeX][changeY] != '#') {
						// 방문 체크
						visited[changeX][changeY][cur[2]] = true;

						// 해당 지점이 물건이 있는 지점이고 현재 차원에서 가지지못한 물건일 경우
						if(map[changeX][changeY] == 'X' && (cur[2] & 1 << (hashMap.get(changeX * N * M + changeY))) == 0) {
							// 해당 물건을 가진 차원에서도 아직 방문하지 않은 경우
							if(!visited[changeX][changeY][(cur[2] ^ 1 << (hashMap.get(changeX * N * M + changeY)))]) {
								visited[changeX][changeY][(cur[2] ^ 1 << (hashMap.get(changeX * N * M + changeY)))] = true;
								queue.add(new int[] {changeX, changeY, (cur[2] ^ 1 << (hashMap.get(changeX * N * M + changeY)))});
							}
						// 물건이 있는 자리가 아니거나 이미 가진 물건일 경우
						} else {
							queue.add(new int[] {changeX, changeY, cur[2]});
						}
						
					}
					
				}
				
				
			}
			// 시간 소요
			time++;
		}
		
		System.out.println(time - 1);

	}

}
