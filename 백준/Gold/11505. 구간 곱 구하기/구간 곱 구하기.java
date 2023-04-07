import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    private static void Init(int start, int end, int node){
        if(start == end) {
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        Init(start, half, node*2);
        Init(half + 1, end, node*2 + 1);

        segTree[node] = segTree[node * 2] * segTree[node * 2 + 1] % INF;
    }

    private static void update(int start, int end, int node, int index, int changenum){

        if(start <= index && index <= end){
            int half = (start + end) / 2;

            if(start != end) {
                update(start, half, node * 2, index, changenum);
                update(half + 1, end, node * 2 + 1, index, changenum);
                segTree[node] = segTree[node * 2] * segTree[node * 2 + 1] % INF;
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


    private static long find(int start, int end, int node, int left, int right){
        if(start > right || end < left) return 1;

        if(left <= start && right >= end){
            return segTree[node];
        }

        int half = (start + end) / 2;

        return find(start, half, node * 2 , left, right) * find(half+ 1, end, node* 2 + 1, left, right) % INF;
    }

}
