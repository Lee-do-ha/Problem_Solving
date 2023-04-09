import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int[] oddTree;
    static int[] evenTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        // 홀수갯수 트리
        oddTree = new int[N*4];
        // 짝수갯수 트리
        evenTree = new int[N*4];

        st = new StringTokenizer(br.readLine());

        for(int i =1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 세그먼트트리 초기 세팅
        init(1, N, 1);

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch(a){
                    // 값 변경
                case 1:
                    update(1, N, 1, b, c);
                    break;
                    // 구간 내 짝수 갯수 출력
                case 2:
                    sb.append(findEven(1, N, 1, b, c)).append("\n");
                    break;
                    // 구간 내 홀수 갯수 출력
                case 3:
                    sb.append(findOdd(1, N, 1, b, c)).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    // 세그먼트트리 초기 세팅
    private static void init(int start, int end, int Node){
        if(start == end){
            // 인덱스에 맞는 홀수, 짝수 갯수 세팅해주기
            if(arr[start]%2 == 0){
                evenTree[Node] = 1;
                oddTree[Node] = 0;
                return ;
            }else{
                oddTree[Node] = 1;
                evenTree[Node] = 0;
                return;
            }
        }

        int half = (start + end) / 2;

        // 구간 나누어지면 계속 나누면서 진행
        init(start, half, Node*2);
        init(half+1, end, Node*2+1);

        evenTree[Node] = evenTree[Node*2] + evenTree[Node*2+1];
        oddTree[Node] = oddTree[Node*2] + oddTree[Node*2+1];
    }

    private static void update(int start, int end, int Node, int index, int changenum){

        // 변경하려는 값의 인덱스가 구간 내에 있을때만 진행
        if(start <= index && index <= end){

            int half = (start + end) / 2;

            if(start != end){
                // 아직 나누어진다면 계속 나누면서 진행
                update(start, half, Node*2, index, changenum);
                update(half+1, end, Node*2+1, index, changenum);
                evenTree[Node] = evenTree[Node*2] + evenTree[Node*2+1];
                oddTree[Node] = oddTree[Node*2] + oddTree[Node*2+1];
            }else{
                // 인덱스를 찾았다면 변경하려는 값에따라 해당 트리값 변경
                if(changenum%2 == 0){
                    evenTree[Node] = 1;
                    oddTree[Node] = 0;
                }else{
                    evenTree[Node] = 0;
                    oddTree[Node] = 1;
                    arr[index] = changenum;
                }
            }
        }
    }

    // 홀수값 찾기
    private static int findOdd(int start, int end, int Node, int left, int right){
        if(start > right || end < left){
            return 0;
        }

        if(left <= start && right >= end){
            return oddTree[Node];
        }

        int half = (start + end) / 2;

        return findOdd(start, half, Node*2, left, right) + findOdd(half+1, end, Node*2+1, left, right);
    }

    // 짝수값 찾기
    private static int findEven(int start, int end, int Node, int left, int right){
        if(start > right || end < left){
            return 0;
        }

        if(left <= start && right >= end){
            return evenTree[Node];
        }

        int half = (start + end) / 2;

        return findEven(start, half, Node*2, left, right) + findEven(half+1, end, Node*2+1, left, right);
    }
}
