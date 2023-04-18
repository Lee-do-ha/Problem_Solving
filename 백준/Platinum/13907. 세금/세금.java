import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class node implements Comparable<node> {
        int end, weight, visited;

        public node(int end, int weight, int visited) {
            this.end = end;
            this.visited = visited;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }

    static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][N + 1];
        ArrayList<node>[] List = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(dp[i - 1], INF);
            List[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        dp[0][S] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            List[a].add(new node(b, c, 0));
            List[b].add(new node(a, c, 0));
        }

        pq.add(new node(S, 0, 0));

        while (!pq.isEmpty()) {
            node cur = pq.poll();

            if(cur.weight > dp[cur.visited][cur.end]) continue;

            if (cur.visited != N - 1) {
                for (node o : List[cur.end]) {
                    if (dp[cur.visited + 1][o.end] > dp[cur.visited][cur.end] + o.weight) {
                        dp[cur.visited + 1][o.end] = dp[cur.visited][cur.end] + o.weight;
                        pq.add(new node(o.end, dp[cur.visited + 1][o.end], cur.visited + 1));
                    }
                }
            }
        }

        ArrayList<int[]> path = new ArrayList<>();

        int minWeight = INF;
        int minLength;
        for (int i = 0; i < N; i++) {
            if (dp[i][D] != INF) {
                if(dp[i][D] >= minWeight){
                    continue;
                }
                path.add(new int[] {dp[i][D], i});

                if(dp[i][D] < minWeight){
                    minWeight = dp[i][D];
                }
            }
        }

//        for(int[] o : dp){
//            System.out.println(Arrays.toString(o));
//        }

        sb.append(minWeight).append("\n");

        for (int i = 0; i < K; i++) {
            int tax = Integer.parseInt(br.readLine());
            minWeight = INF;
            minLength = INF;
            for (int k = path.size() - 1; k >= 0; --k) {
                path.get(k)[0] += tax * path.get(k)[1];

                if (minWeight > path.get(k)[0]) {
                    minWeight = path.get(k)[0];
                    minLength = path.get(k)[1];
                } else if (path.get(k)[0] >= minWeight && path.get(k)[1] > minLength) {
                    path.remove(k);
                }
            }
            sb.append(minWeight).append("\n");
        }
        System.out.println(sb);
    }
}