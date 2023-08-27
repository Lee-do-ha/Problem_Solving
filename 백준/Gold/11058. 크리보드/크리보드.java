import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        dp = new long[101];

        for(int i = 1; i < N+1 ; i++){
            dp[i] = dp[i-1] + 1;

            if(i > 6){
                for(int j = 2 ; j < 5 ; j++){
                    dp[i] = Math.max(dp[i], dp[i-(j+1)]*j);
                }
            }
        }

        System.out.println(dp[N]);
    }
}