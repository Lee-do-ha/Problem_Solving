import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0 ; i < N ; i++){
            priorityQueue.add(Integer.parseInt(br.readLine()));
        }

        int start = priorityQueue.poll();
        int middle = priorityQueue.poll();
        int end = priorityQueue.poll();

        if(start < middle + end){
            System.out.println(start + middle + end);
            return ;
        }

        while (!priorityQueue.isEmpty()){

            start = middle;
            middle = end;
            end = priorityQueue.poll();

            if(start < middle + end){
                System.out.println(start + middle + end);
                return ;
            }

        }

        System.out.println(-1);

    }
}
