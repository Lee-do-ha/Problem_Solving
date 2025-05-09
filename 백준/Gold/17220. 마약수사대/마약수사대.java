import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited;
	static ArrayList<Integer>[] give;
	static Queue<Integer> queue;
	static int[] take;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		queue = new LinkedList<>();
		
		take = new int[n];
		
		visited = new boolean[n];
		
		give = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			give[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char s = st.nextToken().charAt(0);
			char e = st.nextToken().charAt(0);

			give[s - 'A'].add(e - 'A');
			take[e - 'A']++;
		}
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < k; i++) {
			char d = st.nextToken().charAt(0);
			
			bfs(d - 'A');
//			System.out.println(Arrays.toString(visited));
		}
		
		
		int ans = 0;
		for(int i = 0 ; i < n ; i++) {
			if(!visited[i] && take[i] != 0) {
//				System.out.println((char) (i + 'A'));
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	private static void bfs(int start) {
		
		if(visited[start]) {
			return ;
		}
				
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int k : give[cur]) {
				if(!visited[k] && --take[k] == 0) {
					queue.add(k);
					visited[k] = true;
				}
			}
		}
		
	}

}