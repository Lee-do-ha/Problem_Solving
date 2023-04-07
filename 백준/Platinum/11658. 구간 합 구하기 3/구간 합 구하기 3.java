import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 구간합 구하기 -> fenwickTree
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 숫자 저장
        int[][] arr = new int[N+1][N+1];
        // 팬윅 트리 저장
        int[][] fenwickTree = new int[N+1][N+1];

        // 숫자 들어올때마다 팬윅트리 
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
            
            // a값에따라 갱신할지 구간합 구할지 결정
            if(a == 1){
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                
                // 2차원 구간합 구하는 방식
                long result = sum(fenwickTree, x2,y2) - sum(fenwickTree, x2, y1-1) - sum(fenwickTree, x1 -1, y2) + sum(fenwickTree, x1- 1, y1 -1 );
                sb.append(result).append("\n");
            }else if(a == 0){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                // 이미 저장되어있는 값이 있으므로 갱신하려는 값 - 존재하던 값 으로 갱신해줘야함
                update(fenwickTree, b, c, d - arr[b][c]);
                // 해당 좌표값 바꾸기
                arr[b][c] = d;
              
            }
        }


        System.out.println(sb);
    }

    // 팬윅 트리 갱신
    private static void update(int[][] tree, int a, int b, int k){
        int n = b;
        
        // 트리에 해당하는 트리좌표들 모두 갱신
        while(a < tree.length){

            while(n < tree.length){
                tree[a][n] += k;
                n += (n&-n);
            }
            a += (a&-a);
            n = b;
        }
    }

    // 구간합 구하기 메소드
    private static long sum(int[][] tree, int a, int b){
        long ans = 0;
        int n = b;
        
        // 해당 좌표까지 모두 포함되는 트리값 
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
