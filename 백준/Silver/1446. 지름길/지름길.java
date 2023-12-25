import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int INF = 999999999;
    static ArrayList<node>[] lists;
    static class node{
        int end, weight;

        public node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[M+1];
        lists = new ArrayList[M+1];
        for (int i = 0; i < M + 1; i++) {
            dp[i] = INF;
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (start >= M) {
                continue;
            }

            lists[start].add(new node(end, weight));
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        queue.add(new int[] {0, 0});
        dp[0] = 0;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if(dp[cur[0]] < cur[1]) continue;

            if(cur[0] >= M) continue;

            if (cur[0] + 1 <= M && cur[1] + 1 < dp[cur[0] + 1]) {
                dp[cur[0] + 1] = cur[1] + 1;
                queue.add(new int[]{cur[0] + 1, cur[1] + 1});
            }

            for (node node : lists[cur[0]]) {

                if(node.end > M) continue;

                int next = node.end;
                int cost = node.weight;

                if (dp[next] > cur[1] + cost) {
                    dp[next] = cur[1] + cost;
                    queue.add(new int[] {next, cur[1] + cost});
                }

            }

        }

        System.out.println(dp[M]);

    }

}