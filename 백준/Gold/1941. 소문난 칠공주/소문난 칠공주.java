import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 4방향 탐색
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	// 7좌표 저장할 배열
	static int[] arr;
	// 최종 정답
	static int ans;
	// BFS에 사용할 Queue
	static Queue<Integer> queue;
	// 좌표 찍고 검사할때 사용할 map
	static int[][] intMap;
	// 입력값 저장할 map
	static char[][] map;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		map = new char[5][5];

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		ans = 0;
		arr = new int[7];

		queue = new LinkedList<>();
		intMap = new int[25][25];

		// 조합
		combination(0, 0);
		
		System.out.println(ans);

	}

	// 조합
	private static void combination(int n, int k) {
		// 7까지 오면 7자리가 완성되었으므로 종료
		if (k == 7) {
			// true시 모든 조건 만족하므로 ans++
			if(check1()) ans++;
			return ;
		}

		// k번째 자리 완성시키면 k+1번쨰 자리 탐색
		for (int i = n; i < 25; i++) {
			arr[k] = i;
			combination(i + 1, k + 1);
		}
	}

	// 조건 만족하는지 검사
	private static boolean check1() {
		// 상태 초기화
		start();
		
		// 이다솜파 수
		int S = 0;
		
		// 몇개의 친구 무리인지 세기
		int result = 0;

		for (int i = 0; i < 7; i++) {
			int x = arr[i] / 5;
			int y = arr[i] % 5;

			intMap[x][y] = 1;
			
			if(map[x][y] == 'S') S++;
		}

		// 값이 1이고 50 이하인 경우에만 BFS 진행
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intMap[i][j] == 1 && intMap[i][j] < 50) {
					queue.add(5 * i + j);
					// 값이 1 아니면 0 이므로 +50해서 방문체크
					intMap[i][j] += 50;
					
					// BFS 진행
					while(!queue.isEmpty()) {
						int cur = queue.poll();
						
						for(int k = 0 ; k < 4 ; k++) {
							int changeX = cur / 5 + dx[k];
							int changeY = cur % 5 + dy[k];
							
							if(changeX < 0 || changeX >= 5 || changeY < 0 || changeY >= 5) continue;
							
							if(intMap[changeX][changeY] == 1 && intMap[changeX][changeY] < 50) {
								queue.add(changeX * 5 + changeY);
								intMap[changeX][changeY] += 50;
							}
						}
					}
					result++;
 				}
			}
		}
		
		// 조건 만족시 true
		if(result == 1 && S >= 4) return true;
		
		return false;
		
	}

	// 상태 초기화
	private static void start() {
		queue.clear();
		for (int i = 0; i < 5; i++) {
			Arrays.fill(intMap[i], 0);			
		}
	}

}
