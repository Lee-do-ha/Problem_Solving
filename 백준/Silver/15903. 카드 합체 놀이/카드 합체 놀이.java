import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long input = Long.parseLong(st.nextToken());
            pq.add(input);
        }

        for (int i = 0; i < M; i++) {
            long first = pq.poll();
            long second = pq.poll();

            pq.add(first + second);
            pq.add(first + second);

        }

        long ans = 0;

        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);

    }

}