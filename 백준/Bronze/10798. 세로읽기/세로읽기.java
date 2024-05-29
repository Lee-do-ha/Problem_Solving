import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        Queue<Character>[] queues = new Queue[5];
        for (int i = 0; i < 5; i++) {
            queues[i] = new LinkedList<>();
            String str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                queues[i].add(str.charAt(j));
            }
        }

        while (!isEmpty(queues)) {

            for (int i = 0; i < 5; i++) {
                if (!queues[i].isEmpty()) {
                    sb.append(queues[i].poll());
                }
            }

        }

        System.out.println(sb);
    }

    private static boolean isEmpty(Queue<Character>[] queues) {
        for (int i = 0; i < 5; i++) {
            if(!queues[i].isEmpty()) return false;
        }
        return true;
    }

}