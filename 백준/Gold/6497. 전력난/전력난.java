import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class node implements Comparable<node>{
        int start, end, weight;

        public node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while (N != 0 && M != 0) {
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            PriorityQueue<node> priorityQueue = new PriorityQueue<>();

            long ans = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                ans += weight;

                priorityQueue.add(new node(start, end, weight));

            }

            int num = 0;
            while (!priorityQueue.isEmpty() && num != N - 1) {
                node cur = priorityQueue.poll();

                if (findSet(cur.start) != findSet(cur.end)) {
                    unionSet(cur.start, cur.end);
                    num++;
                    ans -= cur.weight;
                }
            }
            stringBuilder.append(ans).append("\n");
            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.println(stringBuilder);
        
    }

    private static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b) {
        if (findSet(a) == findSet(b)) {
            return;
        }

        parents[findSet(b)] = findSet(a);
    }



}