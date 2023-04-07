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

    // 목적지, 목적지로 가는 비용 저장
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

    // 다익스트라 초기값 저장
    static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N != 0 && M != 0){
            // 모든 배열 초기화
            reSet(N);
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 배열 초기화
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
                
                // 경로와 비용 저장
                distance[a][b] = c;
                Path[a].add(new Node(b,c));
                
                // 역추적하기위해
                reversePath[b].add(a);
            }

            // 시작점부터 다익스트라
            dijkstra(start);

            // 최단 경로에 해당하는 경로들 삭제
            deletePath(end, N);

            // 다시 다익스트라
            dijkstra(start);

            // 경로 삭제하고 다익스트라돌렸을 경우 목적지까지의 최소 비용이 INF라면 연결안되어있음
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

    // 배열 및 리스트 모두 초기화
    private static void reSet(int N){

        for(int i = 0 ; i < N ; i++){
            Path[i] = new ArrayList<>();
            reversePath[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < 500 ; i++){
            minDistance[i] = INF;
        }
    }

    // 다익스트라
    private static void dijkstra(int start){
        // 출발지점 시작
        pq.add(new Node(start, 0));
        minDistance[start] = 0;
        
        // 다익스트라 PriorityQueue로 돌리기
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.weight > minDistance[cur.end]) continue;

            // 현재 정점에서 갈 수 있는 정점까지 들려서 가는 비용 비교
            for(Node o : Path[cur.end]){
                if(minDistance[cur.end] + o.weight < minDistance[o.end]){
                    minDistance[o.end] = minDistance[cur.end] + o.weight;
                    pq.add(new Node(o.end, minDistance[o.end]));
                }
            }
        }
    }

    // 최단 경로 삭제
    private static void deletePath(int end, int N){
        queue.add(end);

        // 현재까지의 최소 비용이 해당 정점까지의 최소 비용 + 현재 정점까지 이동 비용이라면 최단 경로이므로 삭제
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

        // 0이 아닌 경로만 이동 가능하므로 경로에 다시 
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
