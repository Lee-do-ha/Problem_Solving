import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 도착점, 도착점까지의 경로 길이, 해당 도착점까지의 들린 경로의 갯수
    static class node implements Comparable<node> {
        int end, weight, visited;

        public node(int end, int weight, int visited) {
            this.end = end;
            this.visited = visited;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }

    static final int INF = 999999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // N은 거친 도로의 갯수, N+1은 도착하는 정점의 좌표
        int[][] dp = new int[N][N + 1];
        
        // 도착점의 경로의 길이와 들린 도로의 갯수 저장
        ArrayList<node>[] List = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(dp[i - 1], INF);
            List[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        // 시작점에서 출발하므로 해당 시작점은 0으로 저장하고 시작
        dp[0][S] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 양방향 그래프
            List[a].add(new node(b, c, 0));
            List[b].add(new node(a, c, 0));
        }

        // 도착점, 도로 길이, 거친 도로의 갯수
        pq.add(new node(S, 0, 0));

        while (!pq.isEmpty()) {
            node cur = pq.poll();

            // 이미 더 작은값이 존재하다면 해볼 필요가 없으므로 
            if(cur.weight > dp[cur.visited][cur.end]) continue;

            // N-1이상이면 이미 모든 도로를 거친 상태
            if (cur.visited != N - 1) {
                // 현재 노드에서 이어진 도착점까지의 경로값비교해서 우선순위큐에 추가
                for (node o : List[cur.end]) {
                    if (dp[cur.visited + 1][o.end] > dp[cur.visited][cur.end] + o.weight) {
                        dp[cur.visited + 1][o.end] = dp[cur.visited][cur.end] + o.weight;
                        pq.add(new node(o.end, dp[cur.visited + 1][o.end], cur.visited + 1));
                    }
                }
            }
        }

        // 도착점의 경로, 거쳐서 온 도로의 갯수 배열 저장할 List
        ArrayList<int[]> path = new ArrayList<>();

        int minWeight = INF;
        int minLength;
        for (int i = 0; i < N; i++) {
            if (dp[i][D] != INF) {
                // i가 증가하므로 현재 최솟값보다 크거나 같으면, 이전의 도로의 갯수가 더 크다는 것이므로 최솟값이 될수가 없음
                if(dp[i][D] >= minWeight){
                    continue;
                }
                // 경로에 배열 춫가
                path.add(new int[] {dp[i][D], i});

                // 최솟값 갱신
                if(dp[i][D] < minWeight){
                    minWeight = dp[i][D];
                }
            }
        }

        sb.append(minWeight).append("\n");

        // K번의 세금 증가
        for (int i = 0; i < K; i++) {
            int tax = Integer.parseInt(br.readLine());
            minWeight = INF;
            minLength = INF;
            for (int k = path.size() - 1; k >= 0; --k) {
                // 세금 증가하면 거쳐서 온 도로의 갯수의 곱만큼 증가
                path.get(k)[0] += tax * path.get(k)[1];

                // 최솟값이 될 수 없는 값 계속 없애주면서 최적화
                if (minWeight > path.get(k)[0]) {
                    minWeight = path.get(k)[0];
                    minLength = path.get(k)[1];
                } else if (path.get(k)[0] >= minWeight && path.get(k)[1] > minLength) {
                    path.remove(k);
                }
            }
            sb.append(minWeight).append("\n");
        }
        System.out.println(sb);
    }
}
