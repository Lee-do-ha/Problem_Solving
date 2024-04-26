import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = N * M;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            int cur = Integer.parseInt(st.nextToken());

            sb.append(cur - ans).append(" ");
        }
        System.out.println(sb);
    }

}