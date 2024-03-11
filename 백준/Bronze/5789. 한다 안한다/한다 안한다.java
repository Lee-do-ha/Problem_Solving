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
            String str = br.readLine();

            if (str.charAt(str.length() / 2 - 1) == str.charAt(str.length() / 2)) {
                sb.append("Do-it").append("\n");
            } else {
                sb.append("Do-it-Not").append("\n");
            }
        }
        System.out.println(sb);
    }
}