import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class node implements Comparable<node> {
        int end, weight;

        public node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }

    static ArrayList<node>[] lists;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        lists = new ArrayList[N+1];
        ans = new int[N+1];
       
        for (int i = 1; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
            ans[i] = 999999999;
        }
        
        ans[1] = 0;



        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            lists[end].add(new node(start, weight));
            lists[start].add(new node(end, weight));
        }
        
        dijkstra();

        System.out.println(ans[N]);

    }

    private static void dijkstra() {

        PriorityQueue<node> pq = new PriorityQueue<>();

        pq.add(new node(1, 0));

        while (!pq.isEmpty()) {

            node cur = pq.poll();

            if(ans[cur.end] < cur.weight) continue;

            for (node next : lists[cur.end]) {

                if (ans[next.end] <= cur.weight + next.weight) {
                    continue;
                }

                ans[next.end] = cur.weight + next.weight;

                pq.add(new node(next.end, ans[next.end]));

            }

        }

    }

}