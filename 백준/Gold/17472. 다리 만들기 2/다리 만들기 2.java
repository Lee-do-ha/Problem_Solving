import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static HashMap<Integer, Integer> hash;
	static int[] parents;
	
	// 출발지, 목적지, 비용 저장할 
	static class node implements Comparable<node>{
		int start, end, path;

		public node(int start, int end, int path) {
			super();
			this.start = start;
			this.end = end;
			this.path = path;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.path - o.path;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Queue<int[]> queue = new LinkedList<int[]>();
		hash = new HashMap<>();
		
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
		
		// 나누어진 섬의 개수
		int sector = 0;
		
		// 섬의 개수 BFS로 찾고 각 나라별 어느 섬에 소속되있는지 HashMap으로 저장
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					queue.add(new int[] {i,j});
					visited[i][j] = true;
					hash.put(i*10+j, sector);
					
					// BFS 진행
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						
						for(int k = 0 ; k < 4 ; k++) {
							int changeX = cur[0] + dx[k];
							int changeY = cur[1] + dy[k];
							if(changeX >= 0 && changeX < N && changeY >= 0 && changeY < M) {
								if(!visited[changeX][changeY] && map[changeX][changeY] == 1) {
									queue.add(new int[] {changeX, changeY});
									visited[changeX][changeY] = true;
									hash.put(changeX*10 + changeY, sector);
								}
							}
						}
					}
					sector++;
				}
			}
		}
		
		// 각 섬별 가는데 가는 최소비용 저장할 배열
		int[][] dist = new int[sector][sector];
		
		// 최소경로 구해야하므로 먼저 최대값으로 저장
		for(int i = 0 ; i < sector ; i++) {
			for(int j = 0 ; j < sector ; j++) {
				if(i == j) continue;
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int x;
		int y;
		
		// 각 나라별 다른 섬으로 이어질 수 있는지 체크
		for(int k : hash.keySet()) {
			int curx = k/10;
			int cury = k%10;
			
			// 4방향으로 탐색
			for(int z = 0 ; z < 4 ; z++) {
				x = curx;
				y = cury;
				int distance = 0;
				boolean flag = true;
				
				// 범위 안에서 계속 체크
				while(x >= 0 && x < N && y >= 0 && y < M && flag) {
					distance++;
					x += dx[z];
					y += dy[z];
					
					if(x >= 0 && x < N && y >= 0 && y < M) {
						// 범위 안에서 나라를 만나면 무조건 종료
						if(map[x][y] == 1) {
							// 다른 섬이 다다르다면 최소 비용 갱신
							if(hash.get(10*x+y) != hash.get(k) && distance > 2) {
								dist[hash.get(10*x+y)][hash.get(k)] = Math.min(dist[hash.get(10*x+y)][hash.get(k)], distance-1);
								dist[hash.get(k)][hash.get(10*x + y)] = dist[hash.get(10*x+y)][hash.get(k)];
							}
							flag = false;
						}
					}else {
						flag = false;
					}
				}
			}
		}
		
		// 최소 스패닝 트리 만들기
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		// 비용이 0과 최대값이 아닌값들은 모두 PriorityQueue에 넣어주기
		for(int i = 0 ; i < sector ; i++) {
			for(int j = i ; j < sector ; j++) {
				if(dist[i][j] != 0 && dist[i][j] != Integer.MAX_VALUE) {
					pq.add(new node(i, j, dist[i][j]));
				}
			}
		}
		
		// 조상 저장할 배열
		parents = new int[sector];
		
		for(int i = 0 ; i < sector ; i++) {
			parents[i] = i;
		}
		
		// 총 드는 비용
		int result = 0;
		
		// 섬끼리 연결한 선의 개수 -> N-1개가 되면 모두 연결됨
		int n = 0;

		// MST만들기
		while(!pq.isEmpty()) {
			node temp = pq.poll();
			
			if(findSet(temp.start) != findSet(temp.end)) {
				unionSet(temp.start, temp.end);
				result += temp.path;
				n++;
			}else {
				continue;
			}
			
			// N-1개의 선이 연결되면 종료
			if(n == sector-1) break;
		}
		
		// 모두 연결되었는지 체크하기
		int k = findSet(0);
		
		// 모두 연결되었는지 체크하기
		for(int i = 0 ; i < sector ; i++) {
			if(findSet(i) == k) continue;
			
			// 연결안되어있다면 result에 -1값 저장하고 종료
			result = -1;
			break;
		}
		System.out.println(result);
	}
	 
	// 조상 누군지 찾는 메소드
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	// 조상 합쳐주는 메소드
	private static void unionSet(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return;
		
		parents[pb] = pa;
	}
}
