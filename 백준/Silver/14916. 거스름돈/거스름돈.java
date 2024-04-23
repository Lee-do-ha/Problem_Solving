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

        int ans = -1;

        for (int i = N / 5; i >= 0 / N; i--) {
            int remain = N - (5 * i);

            if (remain % 2 == 0) {
                ans = i + remain / 2;
                break;
            }

        }

        System.out.println(ans);
    }

}