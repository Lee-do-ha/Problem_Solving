import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        dp[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            if (i == 2) {
                dp[i] = 2;
            } else {
                dp[i] = (dp[i-2] + dp[i-1]) % 15746;
            }
        }

        System.out.println(dp[N]);

    }

}