import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node{
        int end;
        long weight;

        public node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuffer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] dijkstra = new long[N+1];
        ArrayList<node>[] list = new ArrayList[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            dijkstra[i] = Long.MAX_VALUE;
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[end].add(new node(start, weight));
        }

        PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return Long.compare(dijkstra[o1.end], dijkstra[o2.end]);
            }
        });

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++){
            int target = Integer.parseInt(st.nextToken());
            pq.add(new node(target, 0));
            dijkstra[target] = 0;
        }

        while (!pq.isEmpty()){

            node cur = pq.poll();

            if(cur.weight > dijkstra[cur.end]) continue;

            if(!list[cur.end].isEmpty()){
                for(int i = 0 ; i < list[cur.end].size() ; i++){

                    node next = list[cur.end].get(i);

                    if(cur.weight + next.weight < dijkstra[next.end]){
                        dijkstra[next.end] = cur.weight + next.weight;
                        pq.add(new node(next.end, dijkstra[next.end]));
                    }

                }
            }
        }

        int ansNum = 0;
        long ansWeight = 0;

        for(int i = 1 ; i < N+1 ; i++){
            if(dijkstra[i] == 0) continue;

            if(dijkstra[i] > ansWeight){
                ansNum = i;
                ansWeight = dijkstra[i];
            }
        }
        System.out.println(ansNum);
        System.out.println(ansWeight);
//        System.out.println(Arrays.toString(dijkstra));
    }
}