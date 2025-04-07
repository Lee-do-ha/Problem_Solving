import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 느리게 갱신되는 세그먼트 트리 필요한 배열
	static int[] segTree, arr, lazy;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 세그먼트 트리 배열
		segTree = new int[N * 4];
		// 느린 갱신 배열
		lazy = new int[N * 4];

		init(1, 1, N);

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());

			if (command == 1) {
				int start = Integer.parseInt(st.nextToken()) + 1;
				int end = Integer.parseInt(st.nextToken()) + 1;
				int changeX = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, start, end, changeX);

			} else {
				int a = Integer.parseInt(st.nextToken()) + 1;
								
				sb.append(find(1, 1, N, a)).append("\n");
			}
		}

		System.out.println(sb);
	}

	// 세그먼트 트리 만들기
	private static void init(int idx, int start, int end) {
		// 시작점과 끝점이 같다면 인덱스와 같으므로 배열값 저장
		if (start == end) {
			segTree[idx] = arr[start];
			return;
		}

		// 구간 나눠서 탐색
		int half = (start + end) / 2;
		init(idx * 2, start, half);
		init(idx * 2 + 1, half + 1, end);

	}

	// lazy배열 업데이트
	private static void lazy_update(int idx, int start, int end) {
		// lazy배열 값이 0이라면 갱신할 값이 없음
		if (lazy[idx] == 0)
			return;

		// 인덱스 좌표가 아니라면 아래 배열로 lazy값 전달
		if (start != end) {
			lazy[idx * 2] ^= lazy[idx];
			lazy[idx * 2 + 1] ^= lazy[idx];
		// 인덱스 좌표라면 값 갱신
		} else {
			segTree[idx] ^= lazy[idx];
		}

		// lazy값 0으로 초기화
		lazy[idx] = 0;

	}

	// 범위 갱신
	private static void update(int idx, int start, int end, int left, int right, int changeX) {
		// lazy값 반영
		lazy_update(idx, start, end);

		// 범위가 벗어났다면 return
		if (start > right || end < left)
			return;

		// 현재 범위가 갱신 범위에 포함된다면 lazy값 갱신하고 return
		if (start >= left && end <= right) {
			lazy[idx] ^= changeX;
			return;
		}

		// 구간 나눠서 탐색
		int half = (start + end) / 2;
		update(idx * 2, start, half, left, right, changeX);
		update(idx * 2 + 1, half + 1, end, left, right, changeX);
		
	}

	// 해당 좌표 값 찾기
	private static int find(int idx, int start, int end, int search) {
		// lazy값 반영
		lazy_update(idx, start, end);

		// 찾으려는 좌표 범위 밖이라면 0 return
		if (start > search || end < search) {
			return 0;
		}

		// 시작점과 끝점이 같고 범위를 안벗어났다면 찾으려는 좌표
		if (start == end) {
			return segTree[idx];
		}

		// 구간 나눠서 탐색
		int half = (start + end) / 2;

		int left = find(idx * 2, start, half, search);
		int right = find(idx *2 +1, half + 1, end, search);

		
		return left + right;
	}

}
