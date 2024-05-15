import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String right = "right";
        String wrong = "wrong";

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0) break;

            int max = Math.max(a, Math.max(b, c));

            String ans = wrong;

            if (max == a) {
                if (Math.pow(max, 2) == Math.pow(b, 2) + Math.pow(c, 2)) {
                    ans = right;
                }
            } else if (max == b) {
                if (Math.pow(max, 2) == Math.pow(a, 2) + Math.pow(c, 2)) {
                    ans = right;
                }
            } else if (max == c) {
                if (Math.pow(max, 2) == Math.pow(a, 2) + Math.pow(b, 2)) {
                    ans = right;
                }
            }

            sb.append(ans).append("\n");

        }

        System.out.println(sb);
    }

}