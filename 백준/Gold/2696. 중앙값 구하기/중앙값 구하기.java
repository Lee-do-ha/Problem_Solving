import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static PriorityQueue<Integer> minQueue, maxQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int test = 0 ; test < T ; test++){
            int N = Integer.parseInt(br.readLine());

            sb.append(N/2 + 1).append("\n");

            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>(Collections.reverseOrder());

            for(int i = 0 ; i < N/10 + 1 ; i++){
                st = new StringTokenizer(br.readLine());

                if(i == N/10){
                    for(int j = 0 ; j < N%10 ; j++){
                        if(j%2 == 0){
                            sb.append(update(Integer.parseInt(st.nextToken()))).append(" ");
                        }else{
                            update(Integer.parseInt(st.nextToken()));
                        }
                    }
                }else{
                    for(int j = 0 ; j < 10 ; j++){
                        if(j%2 == 0){
                            sb.append(update(Integer.parseInt(st.nextToken()))).append(" ");
                        }else{
                            update(Integer.parseInt(st.nextToken()));
                        }
                    }
                }

                if(i%2 == 1 && i != N/10){
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int update(int cur){

        if(maxQueue.size() == minQueue.size()){
            maxQueue.add(cur);
        }else{
            minQueue.add(cur);
        }

        if(!maxQueue.isEmpty() && !minQueue.isEmpty()) {
            if (maxQueue.peek() > minQueue.peek()) {
                int now = minQueue.poll();
                minQueue.add(maxQueue.poll());
                maxQueue.add(now);
            }
        }

        return maxQueue.peek();
    }
}