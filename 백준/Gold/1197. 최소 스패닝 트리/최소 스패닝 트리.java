import java.io.*;
import java.util.*;

public class Main {

    static class path implements Comparable<path>{
        int start, end, weight;

        public path(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

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

        int ans = 0;
        int connentedNode = 0;

        while(!pq.isEmpty()){
            path cur = pq.poll();

            if(findSet(cur.start) == findSet(cur.end)) continue;

            unionSet(cur.start, cur.end);

            ans += cur.weight;
            connentedNode++;

            if(connentedNode == N-1) break;
        }

        System.out.println(ans);

    }

    private static int findSet(int a){
        if(parents[a] == a){
            return a;
        }else{
            return parents[a] = findSet(parents[a]);
        }
    }

    private static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }
}