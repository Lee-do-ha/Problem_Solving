import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node implements Comparable<node>{
        int x, y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(node o) {
            if(this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pq.add(new node(x, y));
        }

        while (!pq.isEmpty()) {
            node cur = pq.poll();

            sb.append(cur.x + " " + cur.y + "\n");
        }
        System.out.println(sb);
    }

}