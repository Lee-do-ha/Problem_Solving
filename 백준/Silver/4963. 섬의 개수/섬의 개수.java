import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map = new int[51][51];
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, -1, 1, -1 , 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Queue<int[]> queue = new LinkedList<>();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            visited = new boolean[M+1][N+1];

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = 0;

            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        queue.add(new int[] {i, j});

                        ans++;

                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();

                            for (int k = 0; k < 8; k++) {
                                int cx = cur[0] + dx[k];
                                int cy = cur[1] + dy[k];

                                if(cx <= 0 || cx > M || cy <= 0 || cy > N || visited[cx][cy] || map[cx][cy] == 0) continue;

                                visited[cx][cy] = true;
                                queue.add(new int[] {cx, cy});
                            }
                        }

                    }
                }
            }

            sb.append(ans).append("\n");

        }

        System.out.println(sb);
    }

}