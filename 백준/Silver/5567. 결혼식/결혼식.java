import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        boolean[] visited = new boolean[N+1];

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (cur[1] < 2) {
                for (int k : list[cur[0]]) {
                    if (!visited[k]) {
                        queue.add(new int[] {k, cur[1] + 1});
                        visited[k] = true;
                    }
                }
            }

        }

        int ans = 0;

        for (int i = 2; i <= N; i++) {
            if(visited[i]) ans++;
        }

        System.out.println(ans);

    }

}