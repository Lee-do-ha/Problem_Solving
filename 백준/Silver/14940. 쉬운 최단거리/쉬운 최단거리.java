import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int startX = 0, startY = 0;

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 0});

        map[startX][startY] = 0;
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int changeX = cur[0] + dx[k];
                int changeY = cur[1] + dy[k];

                if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M || visited[changeX][changeY] || map[changeX][changeY] == 0) continue;

                queue.add(new int[] {changeX, changeY, cur[2] + 1});
                visited[changeX][changeY] = true;
                map[changeX][changeY] = cur[2] + 1;

            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    stringBuilder.append(-1).append(" ");
                } else {
                    stringBuilder.append(map[i][j]).append(" ");
                }
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);

    }

}