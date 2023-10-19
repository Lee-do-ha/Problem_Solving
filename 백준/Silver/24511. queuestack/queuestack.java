import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] queueStack = new int[N];
        Deque<Integer> queue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            queueStack[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int k = Integer.parseInt(st.nextToken());

            if(queueStack[i] == 0){
                queue.addFirst(k);
            }
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int k = 0 ; k < M ; k++){

            queue.addLast(Integer.parseInt(st.nextToken()));
            stringBuilder.append(queue.pollFirst()).append(" ");

        }


        System.out.println(stringBuilder);

    }

}
