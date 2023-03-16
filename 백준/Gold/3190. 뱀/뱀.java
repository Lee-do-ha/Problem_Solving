import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	// 뱀의 몸이 차지하는 칸인지 확인하기 위한 배열
	static boolean[][] visited;
	
	// 뱀이 차지하는 칸들 저장할 클래스
	static class location{
		int x, y;

		public location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	// 시간순으로 처리하기위한 클래스
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
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		// 사과가 있는 위치 map에서 -1로 표시
		for(int i = 0 ; i < K ; i++) {
			st  = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = -1;
		}
		ArrayList<location> List = new ArrayList<>();
		int L = Integer.parseInt(br.readLine());
		
		// 시간순으로 방향전환 저장할 PriorityQueue
		PriorityQueue<change> PQ = new PriorityQueue<>();
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			PQ.add(new change(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		visited = new boolean[N][N];
		
		// 
		visited[0][0] = true;
		List.add(new location(0, 0));
		
		// 지나온 시간
		int curtime = -1;
		// 뱀이 바라보고 있는 방향
		int go = 0;
		// 벽에 부딪혔는지 체크하기 위한 flag
		boolean flag = true;
		
		while(flag && !List.isEmpty()) {
			curtime++;
			
			// 남은 명령 남은지 체크하고 현재 시간과 같다면 방향 전환
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
			
			// 마지막 배열의 좌표가 뱀의 머리
			int curx = List.get(List.size()-1).x;
			int cury = List.get(List.size()-1).y;
			
			// 방향에 따른 뱀의 이동
			switch(go) {
			// 동쪽
			case 0:
				// 벽에 부딪혔거나 본인 몸에 부딪힐 경우 종료
				if(cury+1 < 0 || cury+1 > N-1 || visited[curx][cury+1]) {
					flag = false;
					break;
				}
				
				// 리스트에 다음 좌표 추가하고 몸에 길이에 추가
				List.add(new location(curx, cury+1));
				visited[curx][cury+1] = true;
				
				// 사과가 있었다면 해당 좌표 0으로 바꾸고 계속 진행
				if(map[curx][cury+1] == -1) {
					map[curx][cury+1] = 0;
					continue;
				// 사과가 없다면 꼬리 자르고 해당 좌표 몸의 길이에서 빼기
				}else {
					visited[List.get(0).x][List.get(0).y] = false;
					List.remove(0);
				}
				break;
			// 북쪽
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
			// 서쪽
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
			// 남쪽
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
