import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class noodle implements Comparable<noodle>{
        int deadLine, count;
        public noodle(int deadLine, int count) {
            this.deadLine = deadLine;
            this.count = count;
        }

        @Override
        public int compareTo(noodle o) {
            return this.deadLine - o.deadLine;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<noodle> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            queue.add(new noodle(deadLine, count));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            noodle cur = queue.poll();
            pq.add(cur.count);

            if (cur.deadLine < pq.size()) {
                pq.poll();
            }
        }

        int result = 0;
        for (int n : pq) {
            result += n;
        }

        System.out.println(result);

    }

}