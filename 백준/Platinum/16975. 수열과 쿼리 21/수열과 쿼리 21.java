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
        arr = new long[N+1];
        segTree = new long[N*4];
        lazy = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

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

    private static void initTree(int start, int end, int node){
        if(start == end){
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end)/2;
        initTree(start, half, node*2);
        initTree(half+1, end, node*2+1);

        segTree[node] = segTree[node*2] + segTree[node*2+1];
    }

    private static void lazy_update(int start, int end, int node){
        if(lazy[node] == 0){
            return;
        }

        segTree[node] += (end - start + 1) * lazy[node];

        if(start != end){
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }

        lazy[node] = 0;
    }

    private static void update(int start, int end, int node, int left, int right, int changenum){
        lazy_update(start, end, node);
        if(end < left || start > right){
            return ;
        }

        if(start >= left && end <= right){
            lazy[node] = changenum;
            lazy_update(start, end, node);
            return;
        }

        int half = (start + end) / 2;
        update(start, half, node*2, left, right, changenum);
        update(half+1, end, node*2+1, left, right, changenum);
    }

    private static long find(int start, int end, int node, int index){
        lazy_update(start, end, node);

        if(end < index || start > index){
            return 0;
        }

        if(start == index && end == index){
            return segTree[node];
        }

        int half = (start + end) / 2;

        return find(start, half, node*2, index) + find(half+1, end, node*2+1, index);
    }
}