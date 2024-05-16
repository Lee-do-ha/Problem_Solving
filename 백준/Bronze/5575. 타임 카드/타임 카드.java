import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int s3 = Integer.parseInt(st.nextToken());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());
            int e3 = Integer.parseInt(st.nextToken());

            int a1, a2, a3;

            if (s3 > e3) {
                e2--;
                e3 += 60;
            }
            a3 = e3 - s3;

            if (s2 > e2) {
                e1--;
                e2 += 60;
            }
            a2 = e2 - s2;
            a1 = e1 - s1;

            sb.append(a1 + " " + a2 + " " + a3 + "\n");
        }
        System.out.println(sb);
    }

}