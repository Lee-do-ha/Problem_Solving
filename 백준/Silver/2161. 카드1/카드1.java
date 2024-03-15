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

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            sb.append(queue.poll() + " ");

            if (!queue.isEmpty()) {
                queue.add(queue.poll());
            }
        }

        System.out.println(sb);

    }
}