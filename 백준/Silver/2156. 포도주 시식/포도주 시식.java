import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(br.readLine());

            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            dp[i][1] = dp[i-1][0] + cur;
            dp[i][2] = dp[i-1][1] + cur;
        }

        int ans = Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2]));

        System.out.println(ans);
    }

}