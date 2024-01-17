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

        int[] arr = new int[N];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int cur = Integer.parseInt(br.readLine());

        for (int i = 1; i < N; i++) {
            priorityQueue.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;

        while (true && !priorityQueue.isEmpty()) {
            int max = priorityQueue.poll();

            if(cur > max) break;

            cur++;
            priorityQueue.add(--max);
            ans++;
        }

        System.out.println(ans);

    }

}