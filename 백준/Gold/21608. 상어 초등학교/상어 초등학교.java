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
	static PriorityQueue<elementSchool> PQ;
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
		
		List = new ArrayList[N*N+1];
		for(int i = 1 ; i <= N*N ; i++) {
			List[i] = new ArrayList<>();
		}
		
		ArrayList<Integer> student = new ArrayList<>();
		
		for(int i = 0 ; i < N*N ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			student.add(a);
			for(int k = 0 ; k < 4 ; k++) {
				List[a].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 0 ; i < student.size() ; i++) {
			PQ = new PriorityQueue<>();
			findmap(student.get(i));
		}
		
		result = 0;
		
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
		
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visited[i][j] == true) continue;
				
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
				PQ.add(new elementSchool(i, j, favoriteNum, emptyNum));
			}
		}
		elementSchool A = PQ.poll();
		map[A.x][A.y] = n;
		visited[A.x][A.y] = true;
	}
}