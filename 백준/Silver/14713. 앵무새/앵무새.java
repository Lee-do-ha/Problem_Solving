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

        Queue<String>[] queues = new Queue[N];
        for (int i = 0; i < N; i++) {
            queues[i] = new LinkedList<>();
            String str = br.readLine();
            for (String string : str.split(" ")) {
                queues[i].add(string);
            }
        }

        String ans = "Possible";
        String str = br.readLine();

        for (String string : str.split(" ")) {
            boolean flag = false;
            for (Queue<String> q : queues) {
                if (q.isEmpty()) {
                    continue;
                }
                if (q.peek().equals(string)) {
                    q.poll();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans = "Impossible";
                break;
            }
        }

        for (Queue<String> queue : queues) {
            if (!queue.isEmpty()) {
                ans = "Impossible";
                break;
            } 
        }
        
        System.out.println(ans);
    }

}