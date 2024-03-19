import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            hashMap.put(str, String.valueOf(i));
            hashMap.put(String.valueOf(i), str);
        }

        for (int i = 1; i <= M; i++) {
            sb.append(hashMap.get(br.readLine())).append("\n");
        }

        System.out.println(sb);
    }

}