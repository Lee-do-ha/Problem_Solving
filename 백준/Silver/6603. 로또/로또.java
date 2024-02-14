import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();

        int[] ans = new int[6];

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            if(N == 0) break;

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            permutation(arr, ans, 0, 0);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void permutation(int[] arr, int[] ans, int start, int r) {
        if (r == 6) {
            for (int i = 0; i < r; i++) {
                sb.append(ans[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < arr.length; i++) {
            ans[r] = arr[i];
            permutation(arr, ans, i+1, r + 1);
        }

    }


}