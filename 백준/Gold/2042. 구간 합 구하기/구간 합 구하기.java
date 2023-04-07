import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] fenwickTree = new long[N+1];
        long[] arr = new long[N+1];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Long.parseLong(br.readLine());
            update(fenwickTree, i, arr[i]);
        }

        for(int i = 0 ; i < M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            if(a == 1){
                long c = Long.parseLong(st.nextToken());
                update(fenwickTree, b, c-arr[b]);
                arr[b] = c;
            }else if(a == 2){
                int c = Integer.parseInt(st.nextToken());
                long result = sum(fenwickTree,(int)c) - sum(fenwickTree,b-1);
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(long[] tree, int num, long k){
        while(num < tree.length){
            tree[num] += k;
            num += (num&-num);
        }
    }

    private static long sum(long[] tree, int a){
        long ans = 0;
        while(a > 0){
            ans += tree[a];
            a -= (a&-a);
        }
        return ans;
    }
}