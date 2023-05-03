import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, segTree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // 기본 값 저장할 배열
        arr = new long[N+1];
        
        // 세그먼트 트리 배열
        segTree = new long[N*4];
        
        // 세그먼트 트리의 해당 트리가 가지고 있는 Lazy값 저장할 배열
        lazy = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        // 세그먼트 트리 초기 
        initTree(1, N, 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                update(1, N, 1, b, c, d);
            }else if(a == 2){
                int b = Integer.parseInt(st.nextToken());
                sb.append(find(1, N, 1, b)).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 초기 구성
    private static void initTree(int start, int end, int node){
        // 구간이 1개이면 해당 배열 값 입력
        if(start == end){
            segTree[node] = arr[start];
            return ;
        }

        // 구간이 1개일때까지 나누어서 진행
        int half = (start + end)/2;
        initTree(start, half, node*2);
        initTree(half+1, end, node*2+1);

        // 자식 노드 갱신하면서 현재 트리 노드 값 갱신
        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    // Lazy값 업데이트해주기
    private static void lazy_update(int start, int end, int node){
        
        // Lazy값 0이면 업데이트를 할 필요가 없음
        if(lazy[node] == 0){
            return;
        }

        // 현재 트리의 구간 너비에 해당하는 값 갱신
        segTree[node] += (end - start + 1) * lazy[node];

        // 리프노드아니면 다시 트리 구간 나누어서 갱신값 더해주기
        if(start != end){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }

        // 현재 트리 Lazy값 처리 완료했으므로 0으로 초기화
        lazy[node] = 0;
    }

    // 구간 값 갱신
    private static void update(int start, int end, int node, int left, int right, int changenum){
        lazy_update(start, end, node);
        
        // 범위 벗어나는 경우 return
        if(end < left || start > right){
            return ;
        }

        // 만약 값을 갱신하려는 범위가 현재 트리의 범위보다 크다면 Lazy값 넣어주고 Lazy업데이트
        if(start >= left && end <= right){
            lazy[node] = changenum;
            lazy_update(start, end, node);
            return;
        }

        // 범위가 걸쳐있으므로 구간 나누어서 계속 진행
        int half = (start + end) / 2;
        update(start, half, node*2, left, right, changenum);
        update(half+1, end, node*2+1, left, right, changenum);
    }

    // 구간 값 찾기
    private static long find(int start, int end, int node, int index){
        lazy_update(start, end, node);

        // 구간 벗어나면 0
        if(end < index || start > index){
            return 0;
        }

        // 구간에 포함된다면 해당 트리값 return
        if(start == index && end == index){
            return segTree[node];
        }

        // 계속 구간 나누어서 진행
        int half = (start + end) / 2;

        return find(start, half, node*2, index) + find(half+1, end, node*2+1, index);
    }
}
