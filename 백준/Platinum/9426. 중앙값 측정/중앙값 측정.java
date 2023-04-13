import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 65536;
    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        // 리프노드엔 모든 온도가 들어갈 수 있으므로
        segTree = new int[MAX*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;

        // 1~K직전까지 값 트리에 먼저 넣어두기
        for(int i = 1 ; i < K ; i++){
            update(0, MAX, 1, arr[i], 1);
        }

        int find = (K+1)/2;

        for(int i = K ; i < N+1 ; i++){
            // 배열의 해당 인덱스 값 갯수 추가하기
            update(0, MAX, 1, arr[i], 1);
            // 값 찾아서 더해주기
            ans += find(0, MAX, 1, find);
            // 배열의 처음부분은 다음부분수열에 해당하지않으므로 갯수 
            update(0, MAX, 1, arr[i-K+1], -1);
        }
        System.out.println(ans);
    }

    // 해당 값 갯수 갱신하기
    private static int update(int start, int end, int node, int index, int changenum){
        // 범위 벗어나면 트리값 변경하지않고 가져오기
        if(start > index || end < index){
            return segTree[node];
        }

        // 해당 값 자리의 트리를 도착하면 갯수 갱신해주기
        if(start == end){
            return segTree[node] += changenum;
        }

        int half = (start + end) / 2;

        // 값 갱신된만큼 부모노드의 값도 갱신해주기
        return segTree[node] = update(start, half, node*2, index,changenum) + update(half+1, end, node*2+1, index, changenum);
    }

    // 값 찾기
    private static int find(int start, int end, int node, int k){

        // 리프노드까지 왔다면 해당 값 가져오기
        if(start == end){
            return start;
        }

        // 구간 나눠서 진행하기
        int half = (start + end) /2;

        // 중간값의 번호가 왼쪽노드에 있는지 오른쪽노드에 있는지 분석
        // 왼쪽 노드에 있다면 그대로 왼쪽으로 진행
        if(segTree[node*2] >= k){
            return find(start, half, node*2, k);
            // 오른쪽 노드에 있다면 왼쪽 노드의 갯수만큼빼고 진행
        }else{
            return find(half+1, end, node*2+1, k-segTree[node*2]);
        }
    }
}
