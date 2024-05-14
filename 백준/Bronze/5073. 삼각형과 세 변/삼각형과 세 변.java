import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0) break;

            if (a == b && b == c) {
                sb.append("Equilateral").append("\n");
                continue;
            };

            if (Math.max(Math.max(a, b), c) >= a + b + c - Math.max(Math.max(a, b), c)) {
                sb.append("Invalid").append("\n");
                continue;
            }

            if (a != b && b != c && a != c) {
                sb.append("Scalene").append("\n");
                continue;
            }

            sb.append("Isosceles").append("\n");

        }

        System.out.println(sb);

    }

}