import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] seg;
	static int[] books;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		seg = new int[200000 * 4];
		books = new int[100001];

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			init(1, 1, N + M, N);

//			System.out.print("INIT || ");
//			for (int k = 1; k < N + 1; k++) {
//				System.out.print(books[k] + " ");
//			}
//			System.out.println();
//			for (int i = 1; i < N + 1; i++) {
//				System.out.println(i + "번째 책 = " + books[i] + "층");
//			}


			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
//				System.out.print("books = ");
//				for (int k = 1; k < N + 1; k++) {
//					System.out.print(books[k] + " ");
//				}
//				System.out.println("");
				int book = Integer.parseInt(st.nextToken());
				
				int bookFloor = books[book];
				
				int up = upBook(1, 1, N + M, bookFloor + 1, N + i);
//				System.out.println("UP || " + up);
				
				sb.append(up + " ");

				deleteBook(1, 1, N + M, bookFloor);
//				System.out.println("DELETE || seg[1] = " + seg[1]);
				
				insertBook(1, 1, N + M, N + i + 1);
				books[book] = N + i + 1;
//				System.out.println("INSERT || seg[1] = " + seg[1]);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	private static void init(int idx, int start, int end, int N) {
		if (start > N) {
			return;
		}

		if (start == end && start <= N) {
			seg[idx] = 1;
			books[start] = N - start + 1;
			return;
		}

		int half = (start + end) / 2;

		init(idx * 2, start, half, N);
		init(idx * 2 + 1, half + 1, end, N);

		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];

	}
	
	private static int upBook(int idx, int start, int end, int left, int right) {
		if(start > right || end < left) return 0;
		
		if(start >= left && end <= right) return seg[idx];
		
		int half = (start + end) / 2;
		return seg[idx] = upBook(idx * 2, start, half, left, right) + upBook(idx * 2 + 1, half + 1, end, left, right);
		
	}
	
	private static void deleteBook(int idx, int start, int end, int bookFloor) {
		if(start > bookFloor || end < bookFloor) return;
		
		if(start == end) {
			seg[idx] = 0;
			return ;
		}
		
		int half = (start + end) / 2;
		deleteBook(idx * 2, start, half, bookFloor);
		deleteBook(idx * 2 + 1, half + 1, end, bookFloor);
		
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
	}
	

	private static void insertBook(int idx, int start, int end, int bookFloor) {
		if(start > bookFloor || end < bookFloor) return;
		
		if(start == end) {
			seg[idx] = 1;
			return ;
		}
		
		int half = (start + end) / 2;
		insertBook(idx * 2, start, half, bookFloor);
		insertBook(idx * 2 + 1, half + 1, end, bookFloor);
		
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
	}

}

/*
1
5 3
4 4 5
 */
