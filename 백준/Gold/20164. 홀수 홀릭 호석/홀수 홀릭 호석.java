import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class word {
		int k, odd;

		public word(int k, int odd) {
			super();
			this.k = k;
			this.odd = odd;
		}

	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int input = Integer.parseInt(br.readLine());

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		Queue<word> queue = new LinkedList<>();

		queue.add(new word(input, checkOdd(input)));
		

		while (!queue.isEmpty()) {
			word cur = queue.poll();
			
//			System.out.println("input = " + cur.k  + " " + cur.odd);

			if (cur.k / 10 == 0) {
//				System.out.println("length 1 : " + cur.k);
				min = Math.min(min, cur.odd);
				max = Math.max(max, cur.odd);
			} else if (cur.k / 10 < 10) {
//				System.out.println("length 2 : " + cur.k);

				int next = 0;

				next = cur.k / 10 + cur.k % 10;

				queue.add(new word(next, cur.odd + checkOdd(next)));

			} else if (cur.k / 100 > 0) {
//				System.out.println("length 3 : " + cur.k);
				
				String s = String.valueOf(cur.k);
				
				for(int i = s.length() - 1; i > 0 ; i--) {
					for(int j = i - 1 ; j > 0 ; j--) {						
						int a = 0;
						a += Integer.parseInt(s.substring(0, j)) + Integer.parseInt(s.substring(j, i)) + Integer.parseInt(s.substring(i));
//						System.out.print(s.substring(0, j) + " ");
//						System.out.print(s.substring(j, i) + " ");
//						System.out.println(s.substring(i));
						
						queue.add(new word(a, checkOdd(a) + cur.odd));
					}
				}
				
			}
			

		}

		System.out.println(min + " " + max);

	}

	private static int checkOdd(int k) {
		int ans = 0;

		while (k != 0) {
			if (k % 2 == 1)
				ans++;

			k /= 10;
		}

		return ans;
	}

}