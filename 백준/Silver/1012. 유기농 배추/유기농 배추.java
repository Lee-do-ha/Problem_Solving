import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        queue = new LinkedList<>();

        int testCase = Integer.parseInt(br.readLine());

        for (int test = 0; test < testCase; test++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            int ans = 0;

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = 1;

            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]){
                        queue.add(new int[] {i, j});
                        visited[i][j] = true;

                        ans++;

                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();

                            for (int dk = 0; dk < 4; dk++) {
                                int changeX = cur[0] + dx[dk];
                                int changeY = cur[1] + dy[dk];

                                if(changeY < 0 || changeX < 0 || changeX >= N || changeY >= M) continue;

                                if (map[changeX][changeY] == 1 && !visited[changeX][changeY]) {
                                    queue.add(new int[] {changeX, changeY});
                                    visited[changeX][changeY] = true;
                                }

                            }
                        }

                    }
                }
            }

            stringBuilder.append(ans).append("\n");

        }

        System.out.println(stringBuilder);
    }

}