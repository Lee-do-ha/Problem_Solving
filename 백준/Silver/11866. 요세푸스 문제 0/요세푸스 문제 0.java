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

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        int cur = 0;

        sb.append("<");
        while (!list.isEmpty()) {
            cur += M-1;

            cur %= list.size();

            if (list.size() == 1) {
                sb.append(list.get(cur));
            } else {
                sb.append(list.get(cur) + ", ");
            }
            list.remove(cur);
        }
        sb.append(">");
        System.out.println(sb);
    }

}