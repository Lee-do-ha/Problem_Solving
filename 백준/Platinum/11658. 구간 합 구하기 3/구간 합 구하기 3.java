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

        int[][] arr = new int[N+1][N+1];
        int[][] fenwickTree = new int[N+1][N+1];

        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1 ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                update(fenwickTree, i, j, arr[i][j]);
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if(a == 1){
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                long result = sum(fenwickTree, x2,y2) - sum(fenwickTree, x2, y1-1) - sum(fenwickTree, x1 -1, y2) + sum(fenwickTree, x1- 1, y1 -1 );
                sb.append(result).append("\n");
            }else if(a == 0){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                update(fenwickTree, b, c, d - arr[b][c]);
                arr[b][c] = d;
              
            }
        }


        System.out.println(sb);
    }

    private static void update(int[][] tree, int a, int b, int k){
        int n = b;
        while(a < tree.length){

            while(n < tree.length){
                tree[a][n] += k;
                n += (n&-n);
            }
            a += (a&-a);
            n = b;
        }
    }

    private static long sum(int[][] tree, int a, int b){
        long ans = 0;
        int n = b;
        while(a > 0){

            while(n > 0){
                ans += tree[a][n];
                n -= (n&-n);
            }
            a -= (a&-a);
            n = b;
        }
        return ans;
    }
}
