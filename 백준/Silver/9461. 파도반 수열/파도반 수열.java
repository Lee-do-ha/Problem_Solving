import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Long[] dp = new Long[101];
        dp[1] = 1L;
        dp[2] = 1L;
        dp[3] = 1L;

        for (int i = 4; i < 101; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }
        
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());

            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }

}