import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] weight, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0){
                break;
            }

            weight = new int[N+1];
            parents = new int[N+1];

            for(int i = 1 ; i < N+1 ; i++){
                parents[i] = i;
            }

            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());

                String k = st.nextToken();

                if(k.equals("!")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    unionSet(a, b, c);
                }else if(k.equals("?")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if(findSet(a) != findSet(b)){
                        sb.append("UNKNOWN").append("\n");
                    }else{
                        sb.append(weight[b] - weight[a]).append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }

    private static int findSet(int a){
        if(a == parents[a]) return a;

        int pa = findSet(parents[a]);
        weight[a] += weight[parents[a]];
        return parents[a] = pa;
    }

    private static void unionSet(int a, int b, int c){
        int pa = findSet(a);
        int pb = findSet(b);

        if(pa == pb) return;

        parents[pb] = pa;
        weight[pb] += (c - weight[b] + weight[a]);
    }
}