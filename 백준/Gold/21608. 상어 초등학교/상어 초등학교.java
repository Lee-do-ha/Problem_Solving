import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static int[][] map;
	static boolean[][] visited;
	static int N, result;
	// 가장 우선순위 높은 자리 한자리만 뽑으면 되므로 PriorityQueue 사용
	static PriorityQueue<elementSchool> PQ;
	// 4방 탐색 위해 배열 선언
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<Integer>[] List;
	
	static class elementSchool implements Comparable<elementSchool>{
		int x, y, favorite, empty;

		public elementSchool(int x, int y, int favorite, int empty) {
			super();
			this.x = x;
			this.y = y;
			this.favorite = favorite;
			this.empty = empty;
		}

		// 좋아하는 친구가 옆에 있는 수 -> 빈자리의 수 -> x좌표 -> y좌표 순으로 정렬
		@Override
		public int compareTo(elementSchool o) {
			if(this.favorite == o.favorite) {
				if(this.empty == o.empty) {
					if(this.x == o.x) {
						return this.y - o.y;
					}else {
						return this.x - o.x;
					}
				}else {
					return o.empty - this.empty;
				}
			}else {
				return o.favorite - this.favorite;
			}
		}
		
	}
	// 좋아하는 학생 접해 있는 수
	// 비어있는 칸이 가장 많은 수
	// 행 번호 작은 순 -> 열 번호 작은 순
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		// 인덱스번호가 좋아하는 친구 담을 리스트
		List = new ArrayList[N*N+1];
		for(int i = 1 ; i <= N*N ; i++) {
			List[i] = new ArrayList<>();
		}
		
		// 순서대로 자리 착석위해 학생 번호 저장할 리스트
		ArrayList<Integer> student = new ArrayList<>();
		
		for(int i = 0 ; i < N*N ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			student.add(a);
			for(int k = 0 ; k < 4 ; k++) {
				List[a].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 모든 학생들 findmap 실행해서 자리 앉히기
		for(int i = 0 ; i < student.size() ; i++) {
			PQ = new PriorityQueue<>();
			findmap(student.get(i));
		}
		
		// 결과값 0으로 초기화
		result = 0;
		
		// 각 자리에 앉은 학생들 4방에 좋아하는 친구 수 구하기
		for(int x = 0 ; x < N ; x++) {
			for(int y = 0 ; y < N ; y++) {
				int favoriteNum = 0;
				for(int k = 0 ; k < 4 ; k++) {
					int changex = x + dx[k];
					int changey = y + dy[k];
					
					if(changex >= 0 && changex < N && changey >= 0 && changey < N) {
						if(List[map[x][y]].contains(map[changex][changey])) favoriteNum++;
					}
				}
				// 좋아하는 친구 수에 맞게 결과값에 더해주기
				if(favoriteNum == 4) {
					result += 1000;
				}else if(favoriteNum == 3) {
					result += 100;
				}else if(favoriteNum == 2) {
					result += 10;
				}else if(favoriteNum == 1) {
					result += 1;
				}
			}
		}
		System.out.println(result);
	}
	
	private static void findmap(int n) {
		
		// 이미 방문한 자리는 이미 앉은학생이 있는 경우이므로 pass
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visited[i][j] == true) continue;
				
				// 좋아하는 친구의 수와 비어있는 자리의 수
				int favoriteNum = 0;
				int emptyNum = 0;
				
				for(int k = 0 ; k < 4 ; k++) {
					int changex = i + dx[k];
					int changey = j + dy[k];
					
					if(changex >= 0 && changex < N && changey >= 0 && changey < N) {
						if(visited[changex][changey] == false) emptyNum++;
						if(List[n].contains(map[changex][changey])) favoriteNum++;
					}
				}
				// 각 자리마다 좌표와 좋아하는 친구의수 그리고 비어있는 좌석의 수를 담은 클래스 PriorityQueue에 저장
				PQ.add(new elementSchool(i, j, favoriteNum, emptyNum));
			}
		}
		// PriorityQueue에서 우선순위가 가장 높은 클래스의 좌표에 해당 번호 학생 앉히고 방문체크하기
		elementSchool A = PQ.poll();
		map[A.x][A.y] = n;
		visited[A.x][A.y] = true;
	}
}
