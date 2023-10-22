import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        Deque<Integer> deque = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){

            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            switch (str){
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    if(deque.isEmpty()){
                        stringBuilder.append(-1).append("\n");
                    }else{
                        stringBuilder.append(deque.pollFirst()).append("\n");
                    }
                    break;
                case "pop_back":
                    if(deque.isEmpty()){
                        stringBuilder.append(-1).append("\n");
                    }else{
                        stringBuilder.append(deque.pollLast()).append("\n");
                    }
                    break;
                case "size":
                    stringBuilder.append(deque.size()).append("\n");
                    break;
                case "empty":
                    if(deque.isEmpty()){
                        stringBuilder.append(1).append("\n");
                    }else {
                        stringBuilder.append(0).append("\n");
                    }
                    break;
                case "front":
                    if(deque.isEmpty()){
                        stringBuilder.append(-1).append("\n");
                    }else{
                        stringBuilder.append(deque.peekFirst()).append("\n");
                    }
                    break;
                case "back":
                    if(deque.isEmpty()){
                        stringBuilder.append(-1).append("\n");
                    }else{
                        stringBuilder.append(deque.peekLast()).append("\n");
                    }
                    break;
            }

        }

        System.out.println(stringBuilder);

    }

}