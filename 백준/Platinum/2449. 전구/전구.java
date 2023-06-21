import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[] arr;
    static int[][] dp;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();


        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(find(0, N-1));
    }
    
    private static int find(int left, int right){
        if(left == right){
            return 0;
        }
        
        if(dp[left][right] != 0){
            return dp[left][right];
        }
        
        int temp = 0;
        dp[left][right] = Integer.MAX_VALUE;
        
        for(int i = left ; i < right ; i++){
            temp = arr[left] != arr[i+1] ? 1 : 0;
            dp[left][right] = Math.min(dp[left][right], find(left, i) + find(i+1, right) + temp);
        }
        
        return dp[left][right];
    }
}