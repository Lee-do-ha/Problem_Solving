import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Ticket {
        int v, c, d;

        public Ticket(int v, int c, int d) {
            this.v = v;
            this.c = c;
            this.d = d;
        }
    }

    static int INF = 2147483647;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<Ticket>[] tickets = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                tickets[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                tickets[u].add(new Ticket(v, c, d));
            }

            int[][] DP = new int[N + 1][M + 1];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(DP[i], INF);
            }
            DP[1][0] = 0;

            for (int c = 0; c <= M; c++) {
                for (int d = 1; d <= N; d++) {
                    if (DP[d][c] == INF) continue;

                    int t = DP[d][c];
                    for (Ticket ticket : tickets[d]) {
                        int dv = ticket.v;
                        int dc = ticket.c;
                        int dd = ticket.d;

                        if (dc + c > M) continue;

                        DP[dv][dc + c] = Math.min(DP[dv][dc + c], t + dd);
                    }
                }
            }

            int result = Arrays.stream(DP[N]).min().getAsInt();
            if (result == INF) {
                System.out.println("Poor KCM");
            } else {
                System.out.println(result);
            }
        }

        br.close();
    }
}