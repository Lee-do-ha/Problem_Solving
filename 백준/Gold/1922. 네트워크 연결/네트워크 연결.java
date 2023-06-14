import java.io.*;
import java.util.*;

public class Main {

    // 간선의 정보를 담을 클래스
    static class path implements Comparable<path>{
        // 간선의 연결되는 두 점과, 간선의 가중치
        int start, end, weight;
        public path(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        // PriorityQueue 사용하기 위해 가중치 순으로 정렬
        @Override
        public int compareTo(path o) {
            return this.weight - o.weight;
        }
    }
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 갯수
        int N = Integer.parseInt(st.nextToken());

        // 간선의 갯수
        int M = Integer.parseInt(br.readLine());

        // 부모 배열값 자신으로 초기화 하고 시작
        parents = new int[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            parents[i] = i;
        }

        PriorityQueue<path> pq = new PriorityQueue<>();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new path(s, e, w));
        }

        // 최종 가중치의 값
        int ans = 0;

        // 현재 연결된 간선의 갯수
        // 총 연결되야하는 간선의 갯수는 항상 정점의 갯수-1이므로 해당 갯수가 되면 종료하기 위ㅐ
        int connentedNode = 0;

        // 큐가 빌때까지 실행
        while(!pq.isEmpty()){
            path cur = pq.poll();

            // 현재 간선이 연결하는 두 점이 이미 연결된 상태라면 다음 간선으로 진행
            if(findSet(cur.start) == findSet(cur.end)) continue;

            // 연결되지않았다면 해당 간선이 연결하는 두 점 연결하기
            unionSet(cur.start, cur.end);

            // 현재 간선의 가중치값 더해주기
            ans += cur.weight;

            // 현재 연결된 간선의 갯수 1개 더해주기
            connentedNode++;

            // 간선이 정점의 갯수-1 만큼 연결되었다면 그만 진행해도됨
            if(connentedNode == N-1) break;
        }

        System.out.println(ans);

    }

    // 현재 집합 찾기
    private static int findSet(int a){
        if(parents[a] == a){
            return a;
        }else{
            return parents[a] = findSet(parents[a]);
        }
    }

    // 집합 합치기
    private static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }
}