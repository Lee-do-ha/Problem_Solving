import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken()) - 1;
            int eY = Integer.parseInt(st.nextToken()) - 1;

            for (int x = sX; x <= eX; x++) {
                for (int y = sY; y <= eY; y++) {
                    visited[y][x] = true;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;
                    int ans = 1;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int cX = cur[0] + dx[k];
                            int cY = cur[1] + dy[k];

                            if(cX < 0 || cY < 0 || cX >= N || cY >= M) continue;

                            if(visited[cX][cY]) continue;

                            queue.add(new int[] {cX, cY});
                            visited[cX][cY] = true;
                            ans++;
                        }
                    }
                    priorityQueue.add(ans);
                }
            }
        }
        sb.append(priorityQueue.size()).append("\n");
        while (!priorityQueue.isEmpty()) {
            sb.append(priorityQueue.poll()).append(" ");
        }

        System.out.println(sb);
    }

}