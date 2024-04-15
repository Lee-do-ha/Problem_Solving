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

        for (int i = N - 1; i >= 0; i--) {
            for (int k = 0; k < i; k++) {
                sb.append(" ");
            }
            for (int k = N - i; k > 0; k--) {
                sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}