import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int k = 0; k < 2; k++) {
                int cX = cur[0] + dx[k];
                int cY = cur[1] + dy[k];

                if(cX < 0 || cX >= N || cY < 0 || cY >= M) continue;
                if(map[cX][cY] == 0) continue;
                if(visited[cX][cY]) continue;

                queue.add(new int[] {cX, cY});
                visited[cX][cY] = true;

                if(visited[N-1][M-1]) break;

            }
        }

        if (visited[N - 1][M - 1]) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}