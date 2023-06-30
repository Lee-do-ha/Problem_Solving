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

        PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i++){
            if(queue1.size() == queue2.size()){
                queue1.add(Integer.parseInt(br.readLine()));
            }else{
                queue2.add(Integer.parseInt(br.readLine()));
            }

            if(!queue1.isEmpty() && !queue2.isEmpty()){
                if(queue2.peek() < queue1.peek()){
                    int cur = queue2.poll();
                    queue2.add(queue1.poll());
                    queue1.add(cur);
                }
            }

            sb.append(queue1.peek()).append("\n");
        }

        System.out.println(sb);
    }
}