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
		
		int sector = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					queue.add(new int[] {i,j});
					visited[i][j] = true;
					hash.put(i*10+j, sector);
					
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
		int[][] dist = new int[sector][sector];
		
		for(int i = 0 ; i < sector ; i++) {
			for(int j = 0 ; j < sector ; j++) {
				if(i == j) continue;
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int x;
		int y;
		
		for(int k : hash.keySet()) {
			int curx = k/10;
			int cury = k%10;
			
			for(int z = 0 ; z < 4 ; z++) {
				x = curx;
				y = cury;
				int distance = 0;
				boolean flag = true;
				while(x >= 0 && x < N && y >= 0 && y < M && flag) {
					distance++;
					x += dx[z];
					y += dy[z];
					
					if(x >= 0 && x < N && y >= 0 && y < M) {
						if(map[x][y] == 1) {
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
		
//		for(int[] o : dist) {
//			System.out.println(Arrays.toString(o));
//		}
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int i = 0 ; i < sector ; i++) {
			for(int j = i ; j < sector ; j++) {
				if(dist[i][j] != 0 && dist[i][j] != Integer.MAX_VALUE) {
					pq.add(new node(i, j, dist[i][j]));
				}
			}
		}
		
		parents = new int[sector];
		
		for(int i = 0 ; i < sector ; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int n = 0;
		
//		for(node o : pq) {
//			System.out.println(o.start + " " + o.end + " " + o.path);
//		}
		
		while(!pq.isEmpty()) {
			node temp = pq.poll();
			
			if(findSet(temp.start) != findSet(temp.end)) {
				unionSet(temp.start, temp.end);
				result += temp.path;
				n++;
			}else {
				continue;
			}
			if(n == sector-1) break;
		}
		
		int k = findSet(0);
		
		for(int i = 0 ; i < sector ; i++) {
			if(findSet(i) == k) continue;
			
			result = -1;
			break;
		}
		
		System.out.println(result);
		
	}
	 
	private static int findSet(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	private static void unionSet(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa == pb) return;
		
		parents[pb] = pa;
	}
}
