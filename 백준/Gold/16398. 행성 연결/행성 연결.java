import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class node implements Comparable<node> {
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
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N+1][N+1];

        parents = new int[N+1];

        PriorityQueue<node> priorityQueue = new PriorityQueue<>();

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    priorityQueue.add(new node(i, j, map[i][j]));
                }
            }
            parents[i] = i;
        }

        int connect = 0;
        Long ans = 0L;

        while(!priorityQueue.isEmpty() && connect != N - 1){
            node cur = priorityQueue.poll();

            if(findSet(cur.start) != findSet(cur.end)){
                unionSet(cur.start, cur.end);
                ans += cur.weight;
                connect++;
            }

        }

        System.out.println(ans);

    }

    private static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b){

        if(findSet(a) == findSet(b)) return;

        parents[findSet(b)] = findSet(a);

    }
}