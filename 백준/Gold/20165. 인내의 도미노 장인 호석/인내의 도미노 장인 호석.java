import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int ans = 0;

		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			String e = st.nextToken();

			if (!visited[a][b]) {
				ans++;
				int d;
				if (e.equals("E")) {
					d = 0;
				} else if (e.equals("W")) {
					d = 1;
				} else if (e.equals("S")) {
					d = 2;
				} else  {
					d = 3;
				}
				
				int k = map[a][b] - 1;
				int changeX = a;
				int changeY = b;
				
				visited[changeX][changeY] = true;
				for(int j = 0 ; j < k ; j++) {
					changeX += dx[d];
					changeY += dy[d];
					
					if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) break;
					
					if(!visited[changeX][changeY]) {
						ans++;
						visited[changeX][changeY] = true;
						k = Math.max(k, j + 1 + map[changeX][changeY] - 1);
					}
										
				}
				
			}

			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			
			visited[a][b] = false;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) {
					sb.append("F ");
				} else {
					sb.append("S ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(ans);
		System.out.println(sb);

	}

}