import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        if (N * M < K) {
            System.out.println(0);
            return ;
        }

        int[][] map = new int[N][M];

        int cur = 1;

        int x = N-1;
        int y = 0;
        int direction = 0;

        map[x][y] = cur;
        cur++;

        while (cur <= K) {
            if (direction == 0) {
                if (x - 1 >= 0 && map[x - 1][y] == 0) {
                    x--;
                    map[x][y] = cur;
                    cur++;
                } else {
                    direction++;
                }
            } else if (direction == 1) {
                if (y + 1 < M && map[x][y + 1] == 0) {
                    y++;
                    map[x][y] = cur;
                    cur++;
                } else {
                    direction++;
                }
            } else if (direction == 2) {
                if (x + 1 < N && map[x + 1][y] == 0) {
                    x++;
                    map[x][y] = cur;
                    cur++;
                } else {
                    direction++;
                }
            } else if (direction == 3) {
                if (y - 1 >= 0 && map[x][y - 1] == 0) {
                    y--;
                    map[x][y] = cur;
                    cur++;
                } else {
                    direction++;
                }
            }
            direction %= 4;
        }

        x = N - x;
        y += 1;

        System.out.println(y + " " + x);
    }
}