import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        segTree = new int[N*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트트리 초기 세팅
        Init(1, N, 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(find(1, N, 1, a, b)).append("\n");
        }

        System.out.println(sb);
    }

    // 세그먼트트리 초기 세팅
    private static void Init(int start, int end, int Node){
        // 시작점과 끝점이 같다면 배열값 가져오기
        if(start == end){
            segTree[Node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        // 다시 구간나눠서 트리 값 조정
        Init(start, half, Node*2);
        Init(half+1, end, Node*2+1);

        segTree[Node] = Math.min(segTree[Node*2], segTree[Node*2+1]);
    }

    // 트리에서 값 찾기
    private static int find(int start, int end, int Node, int left, int right){

        // 구간을 벗어나는 경우 최솟값을 찾으므로 최대값으로 설정
        if(start > right || end < left){
            return Integer.MAX_VALUE;
        }

        // 구간내에 완전 겹쳐진다면 바로 트리값 가져오기
        if(start >= left && end <= right){
            return segTree[Node];
        }

        int half = (start+ end)/2;

        // 다시 트리 나눠서 진행
        return Math.min(find(start, half, Node*2, left, right), find(half+1, end, Node*2+1, left, right));
    }
}
