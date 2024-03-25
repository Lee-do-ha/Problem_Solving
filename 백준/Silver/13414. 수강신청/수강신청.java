import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        LinkedHashSet<String> hashSet = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            String cur = br.readLine();

            hashSet.remove(cur);
            hashSet.add(cur);
        }

        int count = 0;
        for (String k : hashSet) {
            count++;
            sb.append(k).append("\n");

            if(count == n) break;
        }

        System.out.println(sb);
    }

}