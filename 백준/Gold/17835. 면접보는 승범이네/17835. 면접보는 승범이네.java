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
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 가장 가까운 면접장까지의 거리
        long[] dijkstra = new long[N+1];
        // 갈 수 있는 지점과 거리 저장할 배열
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

        // 다익스트라 활용하기위해 PriorityQueue 사용
        PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return Long.compare(dijkstra[o1.end], dijkstra[o2.end]);
            }
        });

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++){
            int target = Integer.parseInt(st.nextToken());
            // 각 면접장에서 다익스트라 출발
            pq.add(new node(target, 0));
            dijkstra[target] = 0;
        }

        while (!pq.isEmpty()){

            node cur = pq.poll();

            // 이미 더 멀어진 거리라면 끝내고 다음 순서로 시작
            if(cur.weight > dijkstra[cur.end]) continue;

            if(!list[cur.end].isEmpty()){
                for(int i = 0 ; i < list[cur.end].size() ; i++){

                    node next = list[cur.end].get(i);

                    // 지금 온 거리가 지금까지 있던 거리보다 짧다면 거리 계산 다시하기
                    if(cur.weight + next.weight < dijkstra[next.end]){
                        dijkstra[next.end] = cur.weight + next.weight;
                        pq.add(new node(next.end, dijkstra[next.end]));
                    }

                }
            }
        }

        // 가장 먼 지점 번호
        int ansNum = 0;
        // 가장 먼 지점까지의 거리
        long ansWeight = 0;

        for(int i = 1 ; i < N+1 ; i++){
            // 면접장
            if(dijkstra[i] == 0) continue;

            if(dijkstra[i] > ansWeight){
                ansNum = i;
                ansWeight = dijkstra[i];
            }
        }

        stringBuilder.append(ansNum).append("\n").append(ansWeight);

        System.out.println(stringBuilder);

    }
}
