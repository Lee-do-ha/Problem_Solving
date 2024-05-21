import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] dp = new long[M+1];
        dp[0] = N;

        for (int i = 1; i < M + 1; i++) {
            dp[i] = (long) (dp[i-1] * 1.05);
            if (i >= 3) {
                dp[i] = (long) Math.max(dp[i], Math.floor(dp[i-3]*1.2));
            }
            if (i >= 5) {
                dp[i] = (long) Math.max(dp[i], Math.floor(dp[i-5] * 1.35));
            }
        }

        System.out.println(dp[M]);
    }
}