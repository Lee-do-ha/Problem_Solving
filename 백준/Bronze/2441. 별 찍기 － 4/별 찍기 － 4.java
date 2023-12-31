import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            for (int k = 0; k < N; k++) {
                if (k < i) {
                    sb.append(" ");
                } else {
                    sb.append("*");
                }
            }
            sb.append("\n");

        }

        System.out.println(sb);

    }

}