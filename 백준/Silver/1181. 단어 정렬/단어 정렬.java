import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        HashSet<String> hashSet = new HashSet<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            hashSet.add(str);
        }

        priorityQueue.addAll(hashSet);
        
        while (!priorityQueue.isEmpty()) {
            sb.append(priorityQueue.poll()).append("\n");
        }

        System.out.println(sb);
        
    }

}