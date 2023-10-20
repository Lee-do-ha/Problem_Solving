import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node {
        int end, weight;

        public node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static ArrayList<node>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            list[i] = new ArrayList<>();
        }

        boolean[] isLive = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 3 ; i++){
            isLive[Integer.parseInt(st.nextToken())] = true;
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new node(end, weight));
            list[end].add(new node(start, weight));

        }

        int[] dijkstra = new int[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            dijkstra[i] = 1000000000;
        }

        PriorityQueue<node> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.weight)));

        for(int i = 1 ; i < N+1 ; i++){
            if(isLive[i]){
                dijkstra[i] = 0;

                queue.add(new node(i, 0));
            }
        }

        while (!queue.isEmpty()){

            node cur = queue.poll();

            if(dijkstra[cur.end] < cur.weight) continue;

            for(node next : list[cur.end]){

                if(dijkstra[next.end] > cur.weight + next.weight){
                    dijkstra[next.end] = cur.weight + next.weight;
                    queue.add(new node(next.end, dijkstra[next.end]));
                }

            }

        }

        int distance = 0;
        int ans = 0;

        for(int i = 0 ; i < N+1 ; i++){
            if(dijkstra[i] < distance) continue;

            distance = dijkstra[i];
            ans = i;
        }

        System.out.println(ans);

    }

}