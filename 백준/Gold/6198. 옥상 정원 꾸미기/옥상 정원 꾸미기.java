import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class node{
        int index, height;

        public node(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "node{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 빌딩의 수
        int N = Integer.parseInt(br.readLine());

        // 볼 수 있는 빌딩의 갯수 구하기 위한 자료 구조
        Deque<node> deque = new ArrayDeque<>();

        // 각 빌딩에서 볼 수 있는 빌딩의 갯수
        Long[] ans = new Long[N];

        for(int i = 0 ; i < N ; i++){

            // 입력받는 빌딩의 높이
            int curHeight = Integer.parseInt(br.readLine());
//            System.out.println(i + "번째 빌딩의 높이 = " + curHeight);

            // 첫번째 빌딩 바로 스택에 넣기
            if(i == 0){
                deque.add(new node(i, curHeight));
            // 첫번째가 아닌 경우
            }else{

                while(true){

                    if(deque.isEmpty()){
                        deque.add(new node(i, curHeight));
                        break;
                    }

                    if(deque.peekLast().height <= curHeight){
                        ans[deque.peekLast().index] = (long) (i - deque.peekLast().index - 1);
                        deque.pollLast();
                    } else {
                        deque.addLast(new node(i, curHeight));
                        break;
                    }

                }

            }

        }

        while (!deque.isEmpty()){

            ans[deque.peekFirst().index] = (long) (N - deque.peekFirst().index - 1);
//            System.out.println(deque.peekFirst().toString());
            deque.pollFirst();

        }

//        System.out.println(Arrays.toString(ans));

        Long answer = 0L;

        for(Long i : ans){
            answer += i;
        }

        System.out.println(answer);
    }
}