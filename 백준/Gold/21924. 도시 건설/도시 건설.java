import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class path implements Comparable<path>{
        int from, to, weight;
        public path(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(path o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "path{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }

        PriorityQueue<path> priorityQueue = new PriorityQueue<>();

        long sum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            sum += weight;
            priorityQueue.add(new path(from, to, weight));
        }

        int connected = 0;
        long ans = 0;

        while (!priorityQueue.isEmpty() && connected < N) {
            path cur = priorityQueue.poll();

            if (findSet(cur.from) != findSet(cur.to)) {
                unionSet(cur.from, cur.to);
                ans += cur.weight;
                connected++;
            }
        }

        int p = findSet(1);
        for (int i = 2; i < N + 1; i++) {
            if(findSet(i) == p) continue;

            System.out.println(-1);
            return ;
        }

        System.out.println(sum - ans);
    }

    private static int findSet(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b) {
        int pA = findSet(a);
        int pB = findSet(b);

        if (pA != pB) {
            parents[pB] = pA;
        }
    }

}