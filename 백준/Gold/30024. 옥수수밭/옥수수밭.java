import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<node> pq = new PriorityQueue<node>((o1, o2) -> {
			return o2.weight - o1.weight;
		});
		
		int[][] map = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(i == 0 || i == n-1 || j == 0 || j == m-1) {
					pq.add(new node(i, j, map[i][j]));
					
					visited[i][j] = true;
				}
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		
		int time = 0;
		
		while(time < k) {
			
			node cur = pq.poll();
						
			for(int i = 0 ; i < 4 ; i++) {
				int changeX = cur.x + dx[i];
				int changeY = cur.y + dy[i];
				if(changeX >= 0 && changeX < n && changeY >= 0 && changeY < m) {
					if(!visited[changeX][changeY]) {
						pq.add(new node(changeX, changeY, map[changeX][changeY]));
						visited[changeX][changeY] = true;
					}
				}
			}
			
			sb.append((cur.x + 1) + " " + (cur.y + 1)).append("\n");
			time++;
		}
		
		System.out.println(sb);
	}
	
	static class node {
		int x;
		int y;
		int weight;
		public node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		
	}
	
}