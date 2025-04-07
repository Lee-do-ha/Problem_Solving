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
		lazy = new int[N * 4];
		segTree = new int[N * 4];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init(1, 1, N);

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());

			if (command == 1) {
				int a = Integer.parseInt(st.nextToken()) + 1;
				int b = Integer.parseInt(st.nextToken()) + 1;
				int c = Integer.parseInt(st.nextToken());
				
				update(1, 1, N, a, b, c);
			} else {
				int a = Integer.parseInt(st.nextToken()) + 1;
				int b = Integer.parseInt(st.nextToken()) + 1;
				
				sb.append(find(1, 1, N, a, b)).append("\n");
			}
		}
		
		System.out.println(sb);

	}

	private static int init(int idx, int start, int end) {
		if (start == end) {
			segTree[idx] = arr[start];
			return segTree[idx];
		}

		int half = (start + end) / 2;

		return segTree[idx] = init(idx * 2, start, half) ^ init(idx * 2 + 1, half + 1, end);
	}

	private static void lazy_update(int idx, int start, int end) {
		if (lazy[idx] == 0)
			return;

		if ((end - start + 1) % 2 != 0) {
			segTree[idx] ^= lazy[idx];
		}

		if (start != end) {
			lazy[idx * 2] ^= lazy[idx];
			lazy[idx * 2 + 1] ^= lazy[idx];
		}

		lazy[idx] = 0;
	}

	private static void update(int idx, int start, int end, int left, int right, int changeX) {
		lazy_update(idx, start, end);

		if (start > right || end < left)
			return;

		if (start >= left && end <= right) {
			lazy[idx] ^= changeX;
			lazy_update(idx, start, end);
			return;
		}

		int half = (start + end) / 2;
		update(idx * 2, start, half, left, right, changeX);
		update(idx * 2 + 1, half + 1, end, left, right, changeX);

		segTree[idx] = segTree[idx * 2] ^ segTree[idx * 2 + 1];
	}
	
	private static int find(int idx, int start, int end, int left, int right) {
		lazy_update(idx, start, end);
		
		if(start > right || end < left) return 0;
		
		if(start >= left && end <= right) return segTree[idx];
		
		int half = (start + end) / 2;
		
		return find(idx * 2, start, half, left, right) ^ find(idx * 2 + 1, half + 1, end, left, right);
		
		
	}

}
