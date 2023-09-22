import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(a);
        priorityQueue.add(b);
        priorityQueue.add(c);
        
        priorityQueue.poll();
        System.out.println(priorityQueue.poll());
    }
}