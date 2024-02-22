import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node implements Comparable<node>{
        int x, y, weight;
        public node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return -(this.weight - o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new node(N-1, i, map[N-1][i]));
        }

        int time = 1;
        while (true) {
            node cur = pq.poll();

            if (time == N) {
                System.out.println(cur.weight);
                break;
            }

            if (cur.x >= 1) {
                pq.add(new node(cur.x-1, cur.y, map[cur.x-1][cur.y]));
            }
            time++;
        }
    }

}