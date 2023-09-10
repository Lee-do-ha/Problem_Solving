import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    static int n, m;
    static char[][] s = new char[701][701];
    static boolean[][] chk = new boolean[701][701];
    static int[][] lv = new int[701][701];
    static int[][] tm = new int[701][701];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static final int inf = 0x3f3f3f3f;
    static int[] na = new int[2];
    static int[] viking = new int[2];
    static int[] treasure = new int[2];
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            s[i] = scanner.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (s[i][j] == 'Y') {
                    na[0] = i;
                    na[1] = j;
                } else if (s[i][j] == 'V') {
                    viking[0] = i;
                    viking[1] = j;
                } else if (s[i][j] == 'T') {
                    treasure[0] = i;
                    treasure[1] = j;
                }
            }
        }
        s[na[0]][na[1]] = s[viking[0]][viking[1]] = s[treasure[0]][treasure[1]] = '.';

        q.add(viking);
        chk[viking[0]][viking[1]] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lv[i][j] = -1;
            }
        }

        int level = 0;
        lv[viking[0]][viking[1]] = level++;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int z = 0; z < sz; z++) {
                int[] tmp = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = tmp[0] + dx[i];
                    int ny = tmp[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (lv[nx][ny] >= 0) continue;
                    if (s[nx][ny] == '.') {
                        lv[nx][ny] = level;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
            level++;
        }

        for (int i = 0; i < n; i++) {
            int mn = inf;
            for (int j = 0; j < m; j++) {
                mn = Math.min(mn, lv[i][j]);
                if (lv[i][j] < 0) {
                    mn = inf;
                    tm[i][j] = -1;
                } else {
                    tm[i][j] = mn;
                }
            }

            mn = inf;
            for (int j = m - 1; j >= 0; j--) {
                mn = Math.min(mn, lv[i][j]);
                if (lv[i][j] < 0) {
                    mn = inf;
                } else {
                    tm[i][j] = Math.min(mn, tm[i][j]);
                }
            }
        }

        for (int j = 0; j < m; j++) {
            int mn = inf;
            for (int i = 0; i < n; i++) {
                mn = Math.min(mn, lv[i][j]);
                if (lv[i][j] < 0) {
                    mn = inf;
                } else {
                    tm[i][j] = Math.min(mn, tm[i][j]);
                }
            }

            mn = inf;
            for (int i = n - 1; i >= 0; i--) {
                mn = Math.min(mn, lv[i][j]);
                if (lv[i][j] < 0) {
                    mn = inf;
                } else {
                    tm[i][j] = Math.min(mn, tm[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                chk[i][j] = false;
            }
        }

        q.add(na);
        chk[na[0]][na[1]] = true;
        level = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int z = 0; z < sz; z++) {
                int[] tmp = q.poll();
                if (tmp[0] == treasure[0] && tmp[1] == treasure[1]) {
                    System.out.println("YES");
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = tmp[0] + dx[i];
                    int ny = tmp[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (tm[nx][ny] <= level) continue;
                    if (s[nx][ny] == '.' && !chk[nx][ny]) {
                        chk[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
            level++;
        }
        System.out.println("NO");
    }
}