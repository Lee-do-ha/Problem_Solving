import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        lists = new ArrayList[N+1];
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            lists[A].add(B);
            lists[B].add(A);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int k : lists[cur]) {
                if (!visited[k]) {
                    queue.add(k);
                    visited[k] = true;
                    count++;
                }
            }
        }

        System.out.println(count);
    }

}