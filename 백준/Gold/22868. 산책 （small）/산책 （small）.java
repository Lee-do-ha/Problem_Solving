import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] paths;
	static int[] preVisted;
	static boolean[] visited;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		paths = new ArrayList[N + 1];
		preVisted = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i < N + 1; i++) {
			paths[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			paths[s].add(e);
			paths[e].add(s);
		}

		for (int i = 1; i < N + 1; i++) {
			Collections.sort(paths[i]);
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		Queue<Integer> queue = new LinkedList<>();

		queue.add(s);
		visited[s] = true;

		int time = 0;
		
		boolean flag = false;

		while (!queue.isEmpty() && !flag) {

			int size = queue.size();
			
			for(int t = 0 ; t < size ; t++) {
				int cur = queue.poll();
				
				for(int next : paths[cur]) {
					if(!visited[next]) {
						queue.add(next);
						visited[next] = true;
						preVisted[next] = cur;
						
						if(next == e) {
							flag = true;
							break;
						}
						
					}
				}
			}

			time++;

		}
		
//		System.out.println(time + " " + Arrays.toString(preVisted));
		
		Arrays.fill(visited, false);
		
		queue.clear();
		
		queue.add(e);
		visited[e] = true;
		
		int pre = e;
		while (pre > 0) {
			visited[pre] = true;
			pre = preVisted[pre];
		}
		flag = false;
		visited[s] = false;
		
		while (!queue.isEmpty() && !flag) {

			int size = queue.size();
			
			for(int t = 0 ; t < size ; t++) {
				int cur = queue.poll();
				
				for(int next : paths[cur]) {
					if(!visited[next]) {
						queue.add(next);
						visited[next] = true;
						preVisted[next] = cur;
						
						if(next == s) {
							flag = true;
							break;
						}
						
					}
				}
			}

			time++;

		}
		
		System.out.println(time);
		
	}

}
