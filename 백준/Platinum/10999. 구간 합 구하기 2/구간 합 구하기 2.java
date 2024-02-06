import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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

    private static void update(int start, int end, int node, int left, int right, long changenum){
        lazy_update(start, end, node);
        if(end < left || start > right ){
            return ;
        }

        if(left <= start && end <= right){
            lazy[node] = changenum;
            lazy_update(start, end, node);
            return ;
        }

        int half = (start + end) / 2;

        update(start, half, node*2, left, right, changenum);
        update(half + 1, end, node*2+1, left, right, changenum);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    private static void lazy_update(int start, int end, int node){
        if(lazy[node] == 0){
            return ;
        }

        segTree[node] += (end - start + 1) * lazy[node];

        if(start != end){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }

        lazy[node] = 0;
    }

    private static long getSum(int start, int end, int node, int left, int right){
        lazy_update(start, end, node);

        if(end < left || start > right){
            return 0;
        }

        if(start >= left && end <= right){
            return segTree[node];
        }

        int half = (start + end) / 2;

        return getSum(start, half, node*2, left, right) + getSum(half+1, end, node*2+1, left, right);
    }
}