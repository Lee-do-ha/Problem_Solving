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

        HashSet<Integer> hashSet = new HashSet<>();

        long ans = 0;
        int count = 1;
        int installed = 0;

        Queue<Integer> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            hashSet.add(cur);
            queue.add(cur);
        }

        while (!queue.isEmpty() && installed < M) {
            int size = queue.size();
            int number = 0;

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                int left = cur-1;
                int right = cur+1;

                if (!hashSet.contains(left)) {
                    hashSet.add(left);
                    queue.add(left);
                    installed++;
                    number++;
                }

                if (!hashSet.contains(right)) {
                    hashSet.add(right);
                    queue.add(right);
                    installed++;
                    number++;
                }
            }
            ans += number * count;

            if (installed > M) {
                ans -= (installed - M) * count;
            }
            count++;

        }
        System.out.println(ans);
    }

}