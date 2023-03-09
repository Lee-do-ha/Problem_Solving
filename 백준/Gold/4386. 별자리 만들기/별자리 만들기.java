import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class path implements Comparable<path>{
        int start, end;
        double distance;

        public path(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(path o) {
            if(this.distance >= o.distance) return 1;

            return -1;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<path> PathPQ = new PriorityQueue<>();
        ArrayList<Double[]> dist = new ArrayList<>();
        parents = new int[N+1];

        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            parents[i] = i;

            if(!dist.isEmpty()){
                for(int k = 0 ; k < dist.size() ; k++){
                    double x = Math.abs(a - dist.get(k)[0]);
                    double y = Math.abs(b - dist.get(k)[1]);

                    double weight = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    PathPQ.add(new path(k+1,i,weight));
                }
            }

            dist.add(new Double[] {a,b});

        }
        int num = 0;
        double result = 0;
        while(num != N-1){
            path P = PathPQ.poll();

            if(Find(P.start) == Find(P.end)) continue;

            Union(P.start, P.end);
            result += P.distance;
            num++;
        }
        System.out.printf("%.2f",result);
    }

    private static int Find(int a){
        if(parents[a] == a) return a;

        return parents[a] = Find(parents[a]);
    }

    private static void Union(int x, int y){
        if(Find(x) == Find(y)) return;

        parents[Find(y)] = Find(x);
    }
}