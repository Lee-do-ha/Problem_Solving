import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class word {
		String word;
		int x, y;

		public word(String word, int x, int y) {
			super();
			this.word = word;
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> hashMap = new HashMap<>();

		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < K; i++) {
			String s = br.readLine();

			if (hashMap.keySet().contains(s)) {
				sb.append(hashMap.get(s)).append("\n");
				continue;
			}

			Queue<word> queue = new LinkedList<>();

			char cur = s.charAt(0);
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < M; b++) {
					if (map[a][b] == cur) {
						queue.add(new word(s, a, b));
					}
				}
			}

			int time = 1;
			while (time < s.length()) {
				int size = queue.size();

				char next = s.charAt(time);

				for (int k = 0; k < size; k++) {
					word now = queue.poll();
					
					for (int x = 0; x < 8; x++) {
						int changeX = now.x + dx[x];
						int changeY = now.y + dy[x];

						if(changeX < 0) {
							changeX += N;
						} else if(changeX >= N) {
							changeX -= N;
						}
						
						if(changeY < 0) {
							changeY += M;
						} else if(changeY >= M) {
							changeY -= M;
						}						
						

						if (map[changeX][changeY] == next) {
							queue.add(new word(now.word + map[changeX][changeY], changeX, changeY));
						}

					}
				}
				
				time++;

			}

			hashMap.put(s, queue.size());
			sb.append(hashMap.get(s)).append("\n");

		}

		System.out.println(sb);

	}

}