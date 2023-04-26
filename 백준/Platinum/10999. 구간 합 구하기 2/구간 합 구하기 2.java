import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 값 저장할 배열, 세그먼트 트리, 트리의 갱신값 저장할 배열
    static long[] arr, segTree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        segTree = new long[N*4];
        lazy = new long[N*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        // 초기 트리 구성
        init(1, N, 1);

        for(int i = 0 ; i < M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update(1, N, 1, b, c, d);
            }else if(a == 2){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long result = getSum(1, N, 1, b, c);
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 초기 트리 구성 = 일반 세그먼트 트리와 동일
    private static void init(int start, int end, int node){
        if(start == end){
            segTree[node] = arr[start];
            return;
        }

        int half = (start + end) / 2;

        init(start, half, node*2);
        init(half + 1, end, node*2+1);
        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    // left~right까지 changenum만큼의 값 갱신 메소드
    private static void update(int start, int end, int node, int left, int right, long changenum){
        // 현재 노드의 layz값 확인
        lazy_update(start, end, node);
        
        // 갱신하려는 범위에 해당하지않는다면 return
        if(end < left || start > right ){
            return ;
        }

        // 범위내에 들어간다면 해당 범위 lazy값 갱신했으므로 lazy값에 갱신하려는 값 넣어주고 다시 lazy값 확인하기
        if(left <= start && end <= right){
            lazy[node] = changenum;
            lazy_update(start, end, node);
            return ;
        }

        // 범위에 겹치지 않았다면 다시 2구역으로 나누고 진행
        int half = (start + end) / 2;

        update(start, half, node*2, left, right, changenum);
        update(half + 1, end, node*2+1, left, right, changenum);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    // lazy값 있는지 확인하고 갱신하는 메소드
    private static void lazy_update(int start, int end, int node){
        // 현재 노드에 lazy값 없다면 할 필요 없으므로 return
        if(lazy[node] == 0){
            return ;
        }

        // lazy값 있다면 값 현재 노드값 갱신
        segTree[node] += (end - start + 1) * lazy[node];

        // 리프노드가 아니라면 자식노드들에게 lazy값 다시 내려주기
        if(start != end){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }

        // 현재 노드에서 lazy값 처리했으므로 0으로 다시 초기화
        lazy[node] = 0;
    }

    // 값 구하는 메소드
    private static long getSum(int start, int end, int node, int left, int right){
        // 현재 노드의 lazy값 확인
        lazy_update(start, end, node);

        // 범위 벗어난다면 0 return
        if(end < left || start > right){
            return 0;
        }

        // 범위 내에 들어간다면 해당 트리값 리턴
        if(start >= left && end <= right){
            return segTree[node];
        }

        // 범위 내에 들어가지않았다면 다시 2개로 나눠서 
        int half = (start + end) / 2;

        return getSum(start, half, node*2, left, right) + getSum(half+1, end, node*2+1, left, right);
    }
}
