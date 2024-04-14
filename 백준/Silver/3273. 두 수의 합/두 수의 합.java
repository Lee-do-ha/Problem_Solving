import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());

        int ans = 0;

        int left = 0;
        int right = N-1;

        Arrays.sort(arr);

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum < K) {
                left++;
            } else if (sum > K) {
                right--;
            } else {
                ans++;
                left++;
            }
        }
        System.out.println(ans);
    }

}