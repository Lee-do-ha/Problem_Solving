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

        arr = new long[N+1];
        segTree = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

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

    private static void Init(int start, int end, int node){
        if(start == end) {
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end)/2;

        Init(start, half, node*2);
        Init(half+1 , end, node*2+1);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    private static void update(int start, int end, int node, int index, int changenum){

        if(start <= index && index <= end){
            int half = (start + end)/2;

            if(start != end){
                update(start, half, node*2, index, changenum);
                update(half+1, end, node*2+1, index, changenum);
                segTree[node] = segTree[node*2] + segTree[node*2+1];
            }else{
                segTree[node] = changenum;
                arr[index] = changenum;
            }
        }
    }

    private static long sum(int start, int end, int node, int left, int right){
        if(start > right || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return segTree[node];
        }

        int half = (start + end) / 2;

        return sum(start, half, node*2, left, right) + sum(half+1, end, node*2+1, left, right);
    }
}