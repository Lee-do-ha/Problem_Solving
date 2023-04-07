import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] distance = new int[500][500];
    private static int[] minDistance = new int[500];
    private static ArrayList<Node>[] Path = new ArrayList[500];
    private static ArrayList<Integer>[] reversePath = new ArrayList[500];
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static Queue<Integer> queue = new LinkedList<>();

    static class Node implements Comparable<Node>{
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N != 0 && M != 0){
            reSet(N);
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    distance[i][j] = 0;
                }
            }

            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                distance[a][b] = c;
                Path[a].add(new Node(b,c));
                reversePath[b].add(a);

                if(a == start){
                    minDistance[b] = c;
                    pq.add(Path[a].get(Path[a].size()-1));
                }
            }

            dijkstra(start);

//            for(int i = 0 ; i < N ; i++){
//                System.out.print(minDistance[i] + " ");
//            }
//            System.out.println();

            deletePath(end, N);

            dijkstra(start);

//            for(int i = 0 ; i < N ; i++){
//                System.out.print(minDistance[i] + " ");
//            }
//            System.out.println();

            if(minDistance[end] == INF){
                sb.append(-1).append("\n");
            }else{
                sb.append(minDistance[end]).append("\n");
            }

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }

    private static void reSet(int N){

        for(int i = 0 ; i < N ; i++){
            Path[i] = new ArrayList<>();
            reversePath[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < 500 ; i++){
            minDistance[i] = INF;
        }
    }

    private static void dijkstra(int start){
        pq.add(new Node(start, 0));
        minDistance[start] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.weight > minDistance[cur.end]) continue;

            for(Node o : Path[cur.end]){
                if(minDistance[cur.end] + o.weight < minDistance[o.end]){
                    minDistance[o.end] = minDistance[cur.end] + o.weight;
                    pq.add(new Node(o.end, minDistance[o.end]));
                }
            }
        }
    }

    private static void deletePath(int end, int N){
        queue.add(end);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int o : reversePath[cur]){
                if(minDistance[o] + distance[o][cur] == minDistance[cur]){
                    distance[o][cur] = 0;
                    queue.add(o);
                }
            }
        }

        reSet(N);

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(i == j) continue;

                if(distance[i][j] != 0){
                    Path[i].add(new Node(j, distance[i][j]));
                }
            }
        }
    }
}