import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static int price[], parents[];
    static ArrayList<node>[] list;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        price = new int[N+1];
        parents = new int[N+1];

        for(int i = 1 ; i < N+1 ; i++){
            price[i] = Integer.parseInt(br.readLine());
            parents[i] = i;
        }

        list = new ArrayList[N+1];
        PriorityQueue<node> pq = new PriorityQueue<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new node(a, b, price[a] + price[b] + (2 * c)));
        }

        int conneted = 0;
        int cost = 0;

        while(!pq.isEmpty()){
            node cur = pq.poll();

            if(findSet(cur.start) == findSet(cur.end)) continue;

            unionSet(cur.start, cur.end);
            cost += cur.weight;
            conneted++;

            if(conneted == N-1 ) break;
        }

        int startPoint = Integer.MAX_VALUE;

        for(int i = 1 ; i < N+1 ; i++){
            startPoint = Math.min(startPoint, price[i]);
        }

        System.out.println(cost + startPoint);
    }

    private static int findSet(int a){
        if(parents[a] == a) return a;

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b){

        parents[findSet(b)] = findSet(a);
    }
}