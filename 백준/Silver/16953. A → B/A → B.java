import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class Main {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		long time = 1;
		long max = 1000000001;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		boolean flag = false;
		boolean[] visited = new boolean[(int) max];
		
		queue.add(a);
		visited[a] = true;
		
		while(!flag) {
			
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {
				long cur = queue.poll();
				
				if(cur*2 < max && !visited[(int) (cur*2)]) {
					queue.add((int) (cur*2));
					visited[(int) (cur*2)] = true;
				}
				
				if(cur*10 + 1 < max && !visited[(int) (cur*10 + 1)]) {
					queue.add((int) (cur*10 + 1));
					visited[(int) (cur*10 + 1)] = true;
				}
			}
			
			time++;
			if(visited[b]) flag = true;
			
			if(flag || queue.isEmpty()) break;
		}
		
		if(!flag) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}
	
}
