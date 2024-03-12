import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        boolean flag1;
        boolean flag2;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            flag1 = false;
            flag2 = false;

            if(N%M == 0) flag1 = true;
            if(M%N == 0) flag2 = true;

            if (flag2) {
                sb.append("factor").append("\n");
            } else if (flag1) {
                sb.append("multiple").append("\n");
            } else {
                sb.append("neither").append("\n");
            }

        }
        System.out.println(sb);
    }
}