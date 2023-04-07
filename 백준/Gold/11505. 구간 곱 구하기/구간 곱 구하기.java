import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 초기 트리 만들기
    private static void Init(int start, int end, int node){
        // 구간이 1개라면 저장된 값 리턴
        if(start == end) {
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        // 구간 절반으로 나누고 다시 만들기 
        Init(start, half, node*2);
        Init(half + 1, end, node*2 + 1);

        segTree[node] = segTree[node * 2] * segTree[node * 2 + 1] % INF;
    }

    // 세그먼트트리 갱신
    private static void update(int start, int end, int node, int index, int changenum){

        // 바꾸려는 index값이 현재 구간안에 있는 경우에만 진행
        if(start <= index && index <= end){
            int half = (start + end) / 2;

            // 아직 구간이 있는 경우 다시 구간나누고 진행
            if(start != end) {
                update(start, half, node * 2, index, changenum);
                update(half + 1, end, node * 2 + 1, index, changenum);
                segTree[node] = segTree[node * 2] * segTree[node * 2 + 1] % INF;
                // 구간이 1개로 index값이라면 구간값 다시 올라가면서 트리값 
            }else{
                segTree[node] = changenum;
                arr[index] = changenum;
            }
        }

    }
    static long[] arr, segTree;
    static final int INF = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        segTree = new long[N*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 1부터 N까지의 거리로 1번 루트노드부터 시작
        Init(1, N, 1);

        for(int i = 0 ; i < M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                update(1, N, 1, b, c);
            }else if(a == 2){
                sb.append(find(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 트리에서 구간값 가져오기
    private static long find(int start, int end, int node, int left, int right){
        // 구간에서 벗어난 경우 곱이므로 1 리턴
        if(start > right || end < left) return 1;

        // 구간이 더 작은 경우는 루트값 리턴
        if(left <= start && right >= end){
            return segTree[node];
        }

        int half = (start + end) / 2;

        // 다시 구간 나눠서 
        return find(start, half, node * 2 , left, right) * find(half+ 1, end, node* 2 + 1, left, right) % INF;
    }

}
