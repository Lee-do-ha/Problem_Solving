import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int ans = 0;
        while (idx < N) {

            if (arr[idx] > 0) {
                // 첫번째 공장에서 구매
                int min = arr[idx];
                ans += 3 * min;

                // 두번째 공장에서 구매
                min = Math.min(arr[idx], arr[idx + 1]);
                ans += 2 * min;
                arr[idx + 1] -= min;

                // 세번째 공장에서 구매
                min = Math.min(min, arr[idx + 2] - Math.min(arr[idx + 1], arr[idx + 2]));
                ans += 2 * min;
                arr[idx + 2] -= min;

            }

            idx++;

        }

        System.out.println(ans);

    }

}