import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        HashSet<Integer> hashSet = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[2][N+1];
        dp[0][1] = arr[0];

        for (int i = 2; i < N + 1; i++) {
            dp[0][i] = Math.max(dp[1][Math.max(i - 2, 0)], dp[0][Math.max(i - 2, 0)]) + arr[i - 1];
            dp[1][i] = dp[0][i-1] + arr[i-1];
        }

//        for (int i = 0; i < 2; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(Math.max(dp[0][N], dp[1][N]));

    }

}