import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1 ; i <= Math.min(N, M) ; i++){
            if (N % i == 0 && M % i == 0) {
                stringBuilder.append(i + " " + N/i + " " + M/i).append("\n");
            }
        }

        System.out.println(stringBuilder);

    }

}