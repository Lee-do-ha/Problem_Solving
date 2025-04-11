import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	// 직사각형 저장할 class
	static class rect {
		int x1, y1, x2, y2;

		public rect(int x1, int y1, int x2, int y2) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	// 분리 집합에 사용할 배열과 조합에 사용할 배열
	static int[] parents, cal;
	// 직사각형 좌표 저장할 배열
	static rect[] rects;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		// 원점을 지나는 직사각형이 있는지 저장
		boolean flag = false;

		// 변수들 세팅
		rects = new rect[N];
		parents = new int[N];
		cal = new int[2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			rects[i] = new rect(a, b, c, d);
			parents[i] = i;
			
			// 해당 직사각형이 원점을 지나는지 체크
			if(!flag && throughZero(rects[i])) {
				flag = true;
			}
			
		}

		// 조합해서 2개의 직사각형씩 검사
		calcuration(N, 0, 0);

		// 분리 집합의 갯수 세기
		HashSet<Integer> hashSet = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hashSet.add(findSet(parents[i]));
		}
		
		// 원점을 지난다면 -1한 값 출력 
		// 원점을 지나지않는다면 원래 값 출력
		if(flag) {
			System.out.println(hashSet.size() - 1);
		} else {
			System.out.println(hashSet.size());
		}

	}
	
	// 원점을 지나는지 체크
	private static boolean throughZero(rect rect) {
		
		// x축이 0에 걸칠 때
		if(rect.x1 ==  0 || rect.x2 == 0) {
			// y축도 0에 걸칠 때
			if(rect.y1 <= 0 && rect.y2 >= 0) {
				return true;
			} 
			return false;
		// y축이 0에 걸칠 때
		} else if(rect.y1 == 0 || rect.y2 == 0) {
			// x축도 0에 걸칠 때
			if(rect.x1 <= 0 && rect.x2 >= 0) {
				return true;
			}
			return false;
		}
		
		return false;
	}

	// 두 직사각형이 겹치는 부분이 있는지 체크
	private static void rectCheck(int[] arr) {
		int a = arr[0];
		int b = arr[1];

		// a직사각형이 b직사각형 오른쪽에 있을 때
		if (rects[a].y1 > rects[b].y2)
			return;

		// a직사각형이 b직사각형 왼쪽에 있을 때
		if (rects[a].y2 < rects[b].y1)
			return;

		// a직사각형이 b직사각형 아래에 있을 때
		if (rects[a].x2 < rects[b].x1)
			return;

		// a직사각형이 b직사각형 위에 있을 때
		if (rects[a].x1 > rects[b].x2)
			return;

		// a직사각형이 b직사각형을 감싸고 있을 때
		if (rects[a].x1 < rects[b].x1 && rects[a].x2 > rects[b].x2 && rects[a].y1 < rects[b].y1
				&& rects[a].y2 > rects[b].y2)
			return;

		// b직사각형이 a직사각형을 감싸고 있을 때
		if (rects[a].x1 > rects[b].x1 && rects[a].x2 < rects[b].x2 && rects[a].y1 > rects[b].y1
				&& rects[a].y2 < rects[b].y2)
			return;

		// 모두 통과했다면 둘은 겹치는 직사각형이므로 같은 집합으로 표시
		if (findSet(arr[0]) != findSet(arr[1])) {
			unionSet(arr[0], arr[1]);
		}

	}

	// 조합 공식
	private static void calcuration(int n, int a, int b) {
		// 2개 뽑았다면 두개의 직사각형 겹치는지 여부 체크
		if (b == 2) {
			rectCheck(cal);
			return;
		}

		// 2개의 서로 다른 두 인덱스 찾기
		for (int k = a; k < n; k++) {
			cal[b] = k;
			calcuration(n, k + 1, b + 1);
		}

	}

	// 분리 집합의 대표 찾기
	private static int findSet(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = findSet(parents[a]);
	}

	// 분리 집합에서 같은 집합으로 표시
	private static void unionSet(int a, int b) {
		int pA = parents[a];
		int pB = parents[b];

		parents[pB] = pA;
	}

}