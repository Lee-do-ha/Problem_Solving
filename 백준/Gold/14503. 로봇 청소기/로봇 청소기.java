import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static boolean[][] cleaned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int curX = Integer.parseInt(st.nextToken());
        int curY = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        int ans = 0;
        cleaned = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 청소되지 않은 경우
            if (!cleaned[curX][curY]) {
                cleaned[curX][curY] = true;
                ans++;
            } else {
                // 주변 4칸에 청소되지 않은 빈칸이 있는 경우
                if (haveClean(curX, curY)) {
                    for (int i = 0; i < 4; i++) {
                        direction--;
                        if(direction < 0) direction += 4;

                        if (!cleaned[curX + dx[direction]][curY + dy[direction]] && map[curX + dx[direction]][curY + dy[direction]] != 1) {
                            curX += dx[direction];
                            curY += dy[direction];
                            break;
                        }
                    }
                } else {
                    if(map[curX + dx[(direction+2)%4]][curY + dy[(direction+2)%4]] == 1) break;

                    curX += dx[(direction+2)%4];
                    curY += dy[(direction+2)%4];
                }
            }
        }
        System.out.println(ans);
    }


    private static boolean haveClean(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int changeX = x + dx[i];
            int changeY = y + dy[i];
            if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

            if (!cleaned[changeX][changeY] && map[changeX][changeY] != 1) {
                return true;
            }
        }

        return false;
    }

}