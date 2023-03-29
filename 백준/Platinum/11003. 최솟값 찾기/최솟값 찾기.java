import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static class mininum{
        int num, index;

        public mininum(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    static int min = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] answer = new int[N+1];
        Deque<mininum> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N+1 ; i++){
            int a = Integer.parseInt(st.nextToken());

            while(!deque.isEmpty() && deque.peekLast().num > a){
                deque.pollLast();
            }

            deque.addLast(new mininum(a, i));

            if(deque.peekFirst().index < i - K +1){
                deque.poll();
            }

            sb.append(deque.peek().num + " ");
        }
        System.out.println(sb);
    }
}