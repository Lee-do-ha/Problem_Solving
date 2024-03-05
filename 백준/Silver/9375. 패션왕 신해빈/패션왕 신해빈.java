import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int t = 0; t < T; t++) {

            hashMap.clear();

            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                hashMap.put(type, hashMap.getOrDefault(type, 0) + 1);
            }

            int ans = 1;

            for (String ty : hashMap.keySet()) {
                ans *= (hashMap.get(ty) + 1);
            }

            sb.append(ans - 1).append("\n");
        }
        System.out.println(sb);
    }

}