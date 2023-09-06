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

            // 첫번째 빌딩 바로 스택에 넣기
            if(i == 0){
                deque.add(new node(i, curHeight));
            // 첫번째가 아닌 경우
            }else{

                // 현재 입력받은 빌딩의 높이보다 이전 빌딩이 높거나 큐에 빌딩이 없으면 종료
                while(true){

                    // 큐에 아무것도 없는경우 저장하고 종료
                    if(deque.isEmpty()){
                        deque.add(new node(i, curHeight));
                        break;
                    }

                    // 큐에 마지막 빌딩의 높이가 더 작다면 해당 빌딩이 볼 수 있는 건물 값 구해주고 큐에서 제거
                    if(deque.peekLast().height <= curHeight){
                        ans[deque.peekLast().index] = (long) (i - deque.peekLast().index - 1);
                        deque.pollLast();
                        // 큐에 마지막 빌딩의 높이가 더 높다면 현재 빌딩 큐에 넣기
                    } else {
                        deque.addLast(new node(i, curHeight));
                        break;
                    }

                }

            }

        }

        // 아직 큐에 남아있는 빌딩들은 본인 기준 오른쪽 모든 빌딩을 볼 수 있는 경우
        while (!deque.isEmpty()){

            // 모든 건물에서 현재 건물 index+1 을 빼준 건물만큼 볼 수 있음
            ans[deque.peekFirst().index] = (long) (N - deque.peekFirst().index - 1);
            deque.pollFirst();

        }
        
        // 총 볼 수 있는 건물의 갯수
        Long answer = 0L;

        for(Long i : ans){
            answer += i;
        }

        System.out.println(answer);
    }
}