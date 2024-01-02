import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int i = 0; i < test; i++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N+1];
            int[] sum = new int[N+1];
            dp = new int[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            for (int k = 1; k <= N; k++) {
                arr[k] = Integer.parseInt(st.nextToken());
                sum[k] = sum[k-1] + arr[k];

            }

            int ans = fileMerge(1, N, sum);
            sb.append(ans).append("\n");

        }

        System.out.println(sb);

    }

    private static int fileMerge(int sPoint, int N, int[] arr){
        for (int gap = 1; gap < N; gap++) {
            for (int start = 1; start + gap <= N; start++) {
                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + arr[end] - arr[start-1]);
                }
            }
        }

        return dp[1][N];
    }

}