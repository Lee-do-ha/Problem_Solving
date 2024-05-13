import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int ans = 0;

        PriorityQueue<int[]> startQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> endQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            startQueue.add(new int[] {start, end});

        }

        while (!startQueue.isEmpty()) {
            int[] cur = startQueue.poll();
            
            endQueue.add(cur[1]);

            while (true) {
                if (endQueue.peek() <= cur[0]) {
                    endQueue.poll();
                } else {
                    break;
                }
            }

            ans = Math.max(ans, endQueue.size());

        }

        System.out.println(ans);

    }

}