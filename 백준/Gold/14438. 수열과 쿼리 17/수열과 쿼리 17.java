import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        segTree = new int[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
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
            if(a == 1){
                update(1, N, 1, b, c);
            }else if(a == 2){
                sb.append(find(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 초기 세팅
    private static void init(int start, int end, int node){
        // 시작과 끝이 같다면 해당 값 가져오기
        if(start == end){
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        // 구간 나눠서 진행
        init(start, half, node*2);
        init(half+1, end, node*2+1);

        // 부모 노드는 두 자식 노드 중 최솟값으로 저장
        segTree[node] = Math.min(segTree[node*2], segTree[node*2+1]);
    }

    // 값 바꾸기
    private static void update(int start, int end, int node, int index, int changenum){

        // 바꾸려는 인덱스번호가 찾으려는 구간내에 있을때만 진행
        if(start <= index && index <= end){
            int half = (start + end) / 2;
            // 해당 인덱스로 도착했다면 값 바꾸기
            if(start == end){
                segTree[node] = changenum;
                arr[index] = changenum;
                // 아니라면 계속 진행하고 돌아오면서 다시 값 갱신하기
            }else{
                update(start, half, node*2, index, changenum);
                update(half+1, end, node*2+1, index, changenum);
                segTree[node] = Math.min(segTree[node*2], segTree[node*2+1]);
            }
        }
    }

    // 값 찾기
    private static int find(int start, int end, int node, int left, int right){
        // 구간 벗어나면 최대값으로 리턴
        if(start > right || end < left){
            return Integer.MAX_VALUE;
        }

        // 구간내에 완전히 겹친다면 해당 트리값 가져오기
        if(start >= left && end <= right){
            return segTree[node];
        }

        // 구간 나눠서 값 
        int half = (start + end) / 2;

        return Math.min(find(start, half, node*2, left, right), find(half+1, end, node*2+1, left, right));
    }
}
