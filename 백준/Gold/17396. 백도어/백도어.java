import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node{
        Long end, weight;

        public node(Long end, Long weight) {
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

        boolean[] enemy = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            if(Integer.parseInt(st.nextToken()) == 1){
                enemy[i] = true;
            }
        }

        Long[] dijkstra = new Long[N];

        Long INF = 100000000001L;

        ArrayList<node>[] lists = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            lists[i] = new ArrayList<>();
            dijkstra[i] = INF;
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            lists[start].add(new node((long) end, (long) weight));
            lists[end].add(new node((long) start, (long) weight));
        }

        PriorityQueue<node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> Math.toIntExact(o.weight)));
        priorityQueue.add(new node(0L, 0L));
        dijkstra[0] = 0L;

        enemy[N-1] = false;

        while (!priorityQueue.isEmpty()){

            node cur = priorityQueue.poll();

            if(cur.weight > dijkstra[Math.toIntExact(cur.end)]) continue;

            for(node next : lists[Math.toIntExact(cur.end)]){

                if(!enemy[Math.toIntExact(next.end)] && dijkstra[Math.toIntExact(cur.end)] + next.weight < dijkstra[Math.toIntExact(next.end)]){

                    priorityQueue.add(new node(next.end, next.weight + cur.weight));
                    dijkstra[Math.toIntExact(next.end)] = cur.weight + next.weight;

                }

            }

        }

        if(dijkstra[N-1] == INF){
            System.out.println(-1);
        }else{
            System.out.println(dijkstra[N-1]);
        }


    }

}