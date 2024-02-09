import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class path implements Comparable<path>{
        int index, weight;

        public path(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(path o) {
            return this.weight - o.weight;
        }
    }
    static int[] pre = new int[20];
    static int[] dist = new int[20];
    static ArrayList<path>[] lists;
    private static final int INF = Integer.MAX_VALUE;
    static PriorityQueue<path> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            sb.append("Case #" + tc + ": ");

            Arrays.fill(pre, 0);
            Arrays.fill(dist, INF);

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            lists = new ArrayList[M];
            for (int i = 0; i < M; i++) {
                lists[i] = new ArrayList<>();
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                lists[a].add(new path(b, c));
                lists[b].add(new path(a, c));
            }

            dijkstra(M);

            if (dist[M - 1] == INF) {
                sb.append(-1 + "\n ");
            } else {
                int start = M-1;
                ArrayList<Integer> list = new ArrayList<>();
                while (true) {
                    list.add(start);

                    if(start == 0) break;

                    start = pre[start];
                }

                for (int i = list.size() - 1; i >= 0; i--) {
                    sb.append(list.get(i) + " ");
                }
                sb.append("\n");

            }

        }
        System.out.println(sb);
    }

    private static void dijkstra(int M) {

        pq.clear();
        dist[0] = 0;

        pq.add(new path(0, 0));

        while (!pq.isEmpty()) {
            path cur = pq.poll();

            if(cur.index == M-1) return;

            if(cur.weight > dist[cur.index]) continue;

            for (path next : lists[cur.index]) {
                int nextDist = cur.weight + next.weight;

                if (nextDist < dist[next.index]) {
                    pq.add(new path(next.index, nextDist));
                    dist[next.index] = nextDist;
                    pre[next.index] = cur.index;
                }
            }
        }

    }

}