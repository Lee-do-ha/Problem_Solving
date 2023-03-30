import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class space{
        int index, x, y, z;

        public space(int index,int x, int y, int z) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class path implements Comparable<path>{
        int start, end, price;

        public path(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        @Override
        public int compareTo(path o) {
            return this.price -  o.price;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<path> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        for(int i = 0 ; i < N ; i++){
            parents[i] = i;
        }

        space[] Spaces = new space[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            Spaces[i] = new space(i ,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(Spaces, ((o1, o2) -> o1.x - o2.x));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }
        Arrays.sort(Spaces, ((o1, o2) -> o1.y - o2.y));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }
        Arrays.sort(Spaces, ((o1, o2) -> o1.z - o2.z));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }

        int result = 0;
        int n = 0;
        while(!pq.isEmpty()){
            path cur = pq.poll();
            if(findSet(cur.start) == findSet(cur.end)) continue;

            unionSet(cur.start, cur.end);
            result += cur.price;
            n++;
            if(n == N-1) break;
        }

        System.out.println(result);
    }

    private static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    private static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }
}
