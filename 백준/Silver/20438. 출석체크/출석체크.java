import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] isSleep = new boolean[N+3];
        boolean[] isChecked = new boolean[N+3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            isSleep[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int start = Integer.parseInt(st.nextToken());
            if (!isSleep[start]) {
                for (int a = start; a < N + 3; a += start) {
                    if (!isSleep[a]) {
                        isChecked[a] = true;
                    }
                }
            }
        }

        int[] ans = new int[N+3];
        for (int i = 3; i < N + 3; i++) {
            if (!isChecked[i]) {
                ans[i] = ans[i - 1] + 1;
            } else {
                ans[i] = ans[i - 1];
            }
        }

        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            stringBuilder.append(ans[end] - ans[start-1]).append("\n");
        }

        System.out.println(stringBuilder);

    }

}