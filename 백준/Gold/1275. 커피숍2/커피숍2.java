import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 숫자 저장할 배열
        arr = new long[N+1];
        // 세그먼트 트리
        segTree = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 초기에 만들기
        Init(1, N, 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            sb.append(sum(1, N, 1, Math.min(a, b), Math.max(a,  b))).append("\n");
            update(1, N, 1, c, d);
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 초기에 만들기
    private static void Init(int start, int end, int node){
        // 구간이 한 좌표면 해당 좌표 값 넣기
        if(start == end) {
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end)/2;

        // 세그먼트 트리 자식노드로 뻗어가기
        Init(start, half, node*2);
        Init(half+1 , end, node*2+1);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    // 배열의 값 바꾼만큼 세그먼트 트리 값 변경하기
    private static void update(int start, int end, int node, int index, int changenum){

        // 바꾸려는 인덱스번호가 세그먼트 구간내에 있을때만 진행
        if(start <= index && index <= end){
            int half = (start + end)/2;

            // 아직 좌표가 아니라면 다시 구간 나누기
            if(start != end){
                update(start, half, node*2, index, changenum);
                update(half+1, end, node*2+1, index, changenum);
                // 자식노드 값 변경되었다면 해당 노드도 값 변경하기
                segTree[node] = segTree[node*2] + segTree[node*2+1];
                // 좌표라면 값 변경하기
            }else{
                segTree[node] = changenum;
                arr[index] = changenum;
            }
        }
    }

    // 구간 합 가져오기
    private static long sum(int start, int end, int node, int left, int right){
        // 범위가 겹치는 곳이 없는 경우 0 리턴
        if(start > right || end < left){
            return 0;
        }

        // 세그먼트 구간이 구하려는 구간 내에 있다면 바로 그 값 가져오기
        if(left <= start && end <= right){
            return segTree[node];
        }

        int half = (start + end) / 2;

        // 구간 다시 나눠서 값 가져오기
        return sum(start, half, node*2, left, right) + sum(half+1, end, node*2+1, left, right);
    }
}
