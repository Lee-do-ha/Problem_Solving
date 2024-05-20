import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum = 0;
        int ans = 0;
        int ansNum = 0;

        for (int i = 0; i < 10; i++) {
            int cur = Integer.parseInt(br.readLine());

            sum += cur;
            hashMap.putIfAbsent(cur, 0);
            hashMap.put(cur, hashMap.get(cur) + 1);
        }

        for (int k : hashMap.keySet()) {
            if (hashMap.get(k) > ansNum) {
                ansNum = hashMap.get(k);
                ans = k;
            }
        }

        System.out.println(sum / 10);
        System.out.println(ans);

    }
}