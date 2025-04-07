import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] segTree, arr;

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

		segTree = new int[N * 4];

		init(1, 1, N);

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());

			if (command == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				arr[a] = b;
				update(1, 1, N, a);
			} else if (command == 2) {
				int ans = segTree[1];
				
				sb.append(ans).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void init(int index, int start, int end) {
		if (start == end) {
			segTree[index] = start;
			return;
		}

		int half = (start + end) / 2;
		init(index * 2, start, half);
		init(index * 2 + 1, half + 1, end);

		if (arr[segTree[index * 2]] > arr[segTree[index * 2 + 1]]) {
			segTree[index] = segTree[index * 2 + 1];
		} else {
			segTree[index] = segTree[index * 2];
		}

	}

	private static void update(int index, int start, int end, int changeIndex) {
		if (start == end)
			return;

		if (start > changeIndex || end < changeIndex)
			return;

		int half = (start + end) / 2;
		update(index * 2, start, half, changeIndex);
		update(index * 2 + 1, half + 1, end, changeIndex);
		
		if (arr[segTree[index * 2]] > arr[segTree[index * 2 + 1]]) {
			segTree[index] = segTree[index * 2 + 1];
		} else {
			segTree[index] = segTree[index * 2];
		}
	}

}
