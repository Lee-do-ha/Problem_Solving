import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {

                    count++;
                    int ans = 1;

                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int cx = cur[0] + dx[k];
                            int cy = cur[1] + dy[k];

                            if (cx >= N || cx < 0 || cy < 0 || cy >= M || map[cx][cy] == 0 || visited[cx][cy]) continue;

                            queue.add(new int[] {cx, cy});
                            visited[cx][cy] = true;
                            ans++;

                        }
                    }

                    max = Math.max(max, ans);

                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }

}