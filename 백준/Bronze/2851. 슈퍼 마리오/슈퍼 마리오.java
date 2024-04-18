import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int[] sum = new int[11];

        for (int i = 1; i < 11; i++) {
            int score = Integer.parseInt(br.readLine());
            for (int k = i; k < 11; k++) {
                sum[k] += score;
            }
        }

        int ans = 0;

        for (int i = 1; i < 11; i++) {
            int k = sum[i];

            if (Math.abs(k - 100) < Math.abs(ans - 100)) {
                ans = k;
            } else if (Math.abs(k - 100) == Math.abs(ans - 100)) {
                ans = Math.max(ans, k);
            }
        }
        System.out.println(ans);
    }

}