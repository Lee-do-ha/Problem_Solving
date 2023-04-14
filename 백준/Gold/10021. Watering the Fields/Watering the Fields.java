import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] x = new int[N];
        int[] y = new int[N];
        parents = new int[N];

        for(int i = 0 ; i < N ; i++){
            parents[i] = i;
        }

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N-1 ; i++){
            for(int j = i + 1 ; j < N ; j++){
                if(Math.pow(x[i]-x[j], 2) + Math.pow(y[i]-y[j], 2) >= C){
                    pq.add(new node(i, j, (int)Math.pow(x[i]-x[j],2) + (int)Math.pow(y[i]-y[j], 2)));
                }
            }
        }

        int num = 0;
        int result = 0;
        while(!pq.isEmpty()){
            node cur = pq.poll();

            if(findSet(cur.start) != findSet(cur.end)){
                unionSet(cur.start, cur.end);
                result += cur.weight;
                num++;
                if(num == N-1){
                    break;
                }
            }
        }

        if(num == N-1){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }

    private static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }
}