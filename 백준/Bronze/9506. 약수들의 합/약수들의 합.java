import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            int N = Integer.parseInt(br.readLine());

            if(N == -1) break;

            sb.append(N + " ");

            int sum = 0;

            queue.clear();

            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    queue.add(i);
                    sum += i;
                }
            }

            if (sum == N) {
                sb.append("= ");
                while (!queue.isEmpty()) {
                    sb.append(queue.poll());
                    if (!queue.isEmpty()) {
                        sb.append(" + ");
                    }
                }
                sb.append("\n");
            } else {
                sb.append("is NOT perfect.").append("\n");
            }
        }

        System.out.println(sb);

    }

}