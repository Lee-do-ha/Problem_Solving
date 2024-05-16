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

        long[] arr = new long[N];
        long[] sum = new long[N];
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i > 0) {
                sum[i] += sum[i-1] + arr[i];
                ans += sum[i-1] * arr[i];
            } else {
                sum[i] = arr[i];
            }
        }

//        System.out.println(Arrays.toString(sum));

        System.out.println(ans);
    }

}