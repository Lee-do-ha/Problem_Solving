import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class cook{
        int p, x;
        public cook(int p, int x) {
            this.p = p;
            this.x = x;
        }
    }

    static class path implements Comparable<path>{
        int start, end;
        long popul;

        public path(int start, int end, long popul) {
            this.start = start;
            this.end = end;
            this.popul = popul;
        }

        @Override
        public int compareTo(path o) {
            if(this.popul > o.popul){
                return -1;
            }else{
                return  1;
            }
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<path> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        ArrayList<Integer>[] List = new ArrayList[N];
        int[] used = new int[N];


        cook[] arr = new cook[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new cook(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parents[i] = i;
            List[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = i + 1 ; j < N ; j++){
                int x = Math.abs(arr[i].p - arr[j].p);
                int y = arr[i].x + arr[j].x;
                pq.add(new path(i, j, (long)Math.floor(y/x)));
            }
        }

        int connected = 0;
        long result = 0;
        while(!pq.isEmpty()){
            path cur = pq.poll();
            if(findSet(cur.start) != findSet(cur.end)){
                unionSet(cur.start, cur.end);
                result += cur.popul;
                connected++;
                List[cur.start].add(cur.end);
                List[cur.end].add(cur.start);
                used[cur.start]++;
                used[cur.end]++;

                if(connected == N-1){
                    break;
                }
            }
        }

        sb.append(result).append("\n");

        while(!tf(used)){
            for(int i = 0 ; i < N ; i++){
                if(used[i] == 1){
                    if(!List[i].isEmpty()){
                        for(int k = 0 ; k < List[i].size() ; k++){
                            if(used[List[i].get(k)] > 0 ){
                                sb.append(List[i].get(k)+1).append(" ").append(i+1).append("\n");
                                used[i]--;
                                used[List[i].get(k)]--;
                                List[i].remove(k);
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(sb);

    }

    static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }

    static boolean tf(int[] arr){
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] != 0) return false;
        }
        return true;
    }
}