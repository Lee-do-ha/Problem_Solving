import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    
    // 들어온 숫자와 인덱스 저장할 클래스
    static class mininum{
        int num, index;

        public mininum(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 앞뒤에서 나가기 위해 Deque 활용
        Deque<mininum> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N+1 ; i++){
            int a = Integer.parseInt(st.nextToken());

            // 큐 안에서 자신보다 큰수가 있다면 모두 제거
            while(!deque.isEmpty() && deque.peekLast().num > a){
                deque.pollLast();
            }

            // 자신을 Deqeue 에 추가
            deque.addLast(new mininum(a, i));

            // 큐에 있는 최솟값이 범위를 벗어났다면 제거
            if(deque.peekFirst().index < i - K +1){
                deque.poll();
            }

            // Deqeue의 맨 앞에 있는 값이 해당 인덱스의 값
            sb.append(deque.peek().num + " ");
        }
        System.out.println(sb);
    }
}
