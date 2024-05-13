import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        HashMap<String, Integer> hashMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String cur = br.readLine();

            if (hashMap.containsKey(cur)) {
                hashMap.put(cur, hashMap.get(cur) + 1);
            } else {
                hashMap.put(cur, 1);
            }
        }

        String ans = "";

        for (String name : hashMap.keySet()) {
            if (ans.isEmpty()) {
                ans = name;
            }

            int ansScore = hashMap.get(ans);
            int nameScore = hashMap.get(name);

            if (nameScore > ansScore) {
                ans = name;
            } else if (nameScore == ansScore) {
                if (ans.compareTo(name) > 0) {
                    ans = name;
                }
            }
        }
        System.out.println(ans);
    }

}