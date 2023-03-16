import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static boolean[][] visited;
	static class location{
		int x, y;

		public location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class change implements Comparable<change>{
		int time; 
		char a;
		
		public change(int time, char a) {
			super();
			this.time = time;
			this.a = a;
		}

		@Override
		public int compareTo(change o) {
			return this.time - o.time;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int	N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0 ; i < K ; i++) {
			st  = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = -1;
		}
		ArrayList<location> List = new ArrayList<>();
		int L = Integer.parseInt(br.readLine());
		
		PriorityQueue<change> PQ = new PriorityQueue<>();
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			PQ.add(new change(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		visited = new boolean[N][N];
		visited[0][0] = true;
		List.add(new location(0, 0));
		
		int curtime = -1;
		int go = 0;
		boolean flag = true;
		
		while(flag && !List.isEmpty()) {
			curtime++;
//			for(location l : List) {
//				System.out.print("현재좌표" + l.x + " " + l.y + "\t");
//			}
//			System.out.println();
			
			if(!PQ.isEmpty()) {
				if(curtime == PQ.peek().time) {
					if(PQ.peek().a == 'D') {
						go = (go+3)%4;
					}else if(PQ.peek().a == 'L') {
						go = (go+1)%4;
					}
					PQ.poll();
				}
			}
			
			int curx = List.get(List.size()-1).x;
			int cury = List.get(List.size()-1).y;
//			System.out.println(go + " " + curtime);
//			System.out.println();
			switch(go) {
			case 0:
				if(cury+1 < 0 || cury+1 > N-1 || visited[curx][cury+1]) {
					flag = false;
					break;
				}
				List.add(new location(curx, cury+1));
				visited[curx][cury+1] = true;
				if(map[curx][cury+1] == -1) {
					map[curx][cury+1] = 0;
					continue;
				}else {
					visited[List.get(0).x][List.get(0).y] = false;
					List.remove(0);
				}
				break;
			case 1:
				if(curx-1 < 0 || curx-1 > N-1 || visited[curx-1][cury]) {
					flag = false;
					break;
				}
				List.add(new location(curx-1, cury));
				visited[curx-1][cury] = true;
				if(map[curx-1][cury] == -1) {
					map[curx-1][cury] = 0;
					continue;
				}else {
					visited[List.get(0).x][List.get(0).y] = false;
					List.remove(0);
				}
				break;
			case 2:
				if(cury-1 < 0 || cury-1 > N-1 || visited[curx][cury-1]) {
					flag = false;
					break;
				}
				List.add(new location(curx, cury-1));
				visited[curx][cury-1] = true;
				if(map[curx][cury-1] == -1) {
					map[curx][cury-1] = 0;
					continue;
				}else {
					visited[List.get(0).x][List.get(0).y] = false;
					List.remove(0);
				}
				break;
			case 3:
				if(curx+1 < 0 || curx+1 > N-1 || visited[curx+1][cury]) {
					flag = false;
					break;
				}
				List.add(new location(curx+1, cury));
				visited[curx+1][cury] = true;
				if(map[curx+1][cury] == -1) {
					map[curx+1][cury] = 0;
					continue;
				}else {
					visited[List.get(0).x][List.get(0).y] = false;
					List.remove(0);
				}
				break;
			}
		}
		System.out.println(curtime+1);
	}

}