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

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            int sum = 0;
            int score = 0;
            for (int k = 0; k < str.length(); k++) {
                if (str.charAt(k) == 'O') {
                    score++;
                    sum += score;
                } else {
                    score = 0;
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

}