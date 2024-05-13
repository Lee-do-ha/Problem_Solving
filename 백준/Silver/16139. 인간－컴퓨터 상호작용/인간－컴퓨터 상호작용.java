import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        int N = Integer.parseInt(br.readLine());

        int[][] sum = new int[str.length() + 1][26];

        for (int i = 1; i <= str.length(); i++) {
            char cur = str.charAt(i-1);
            for (int j = 0; j < 26; j++) {
                sum[i][j] = sum[i - 1][j];
                if (cur - 'a' == j) {
                    sum[i][j]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char input = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(sum[end+1][input-'a'] - sum[start][input-'a']).append("\n");
        }
        System.out.println(sb);
    }

}