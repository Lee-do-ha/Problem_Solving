import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[N][M][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});
        visited[0][0][0] = true;
        if (map[0][0] == 2) {
            visited[0][0][1] = true;
            queue.add(new int[] {0, 0, 1});
        }

        int time = -1;
        boolean flag = false;
        while (!queue.isEmpty() && time <= max && !flag) {
            int size = queue.size();

            time++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
//                System.out.println("time = " + time + " X = " + cur[0] + " Y = " + cur[1]);

                if (cur[0] == N - 1 && cur[1] == M - 1) {
                    flag = true;
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int changeX = cur[0] + dx[k];
                    int changeY = cur[1] + dy[k];

                    if (changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) {
                        continue;
                    }

                    switch (cur[2]) {
                        case 0:
                            if (map[changeX][changeY] == 1) {
                                break;
                            } else if (map[changeX][changeY] == 0 && !visited[changeX][changeY][0]) {
                                queue.add(new int[] {changeX, changeY, 0});
                                visited[changeX][changeY][0] = true;
                                break;
                            } else if (map[changeX][changeY] == 2 && !visited[changeX][changeY][0]) {
                                queue.add(new int[] {changeX, changeY, 1});
                                visited[changeX][changeY][0] = true;
                                visited[changeX][changeY][1] = true;
                                break;
                            }
                            break;
                        case 1:
                            if (!visited[changeX][changeY][1]) {
                                queue.add(new int[] {changeX, changeY, 1});
                                visited[changeX][changeY][1] = true;
                                break;
                            }
                    }

                }
            }
        }

        if (flag) {
            System.out.println(time);
        } else {
            System.out.println("Fail");
        }

    }

}
