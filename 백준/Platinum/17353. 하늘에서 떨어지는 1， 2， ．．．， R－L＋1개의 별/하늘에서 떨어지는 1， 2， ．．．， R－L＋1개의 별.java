import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] arr, segTree, lazy, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new long[N+1];
        segTree = new long[N*4];
        lazy = new long[N*4];
        count = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        initTree(1, N, 1);

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(1, N, 1, b, c);
            }else if(a == 2){
                int b = Integer.parseInt(st.nextToken());
                long result = fine(1, N ,1, b);
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static long initTree(int start, int end, int node){
        if(start == end){
            segTree[node] = arr[start];
            return segTree[node];
        }

        int half = start + (end - start) / 2;

        return segTree[node] = initTree(start, half, node*2) + initTree(half+1, end, node*2+1);
    }

    private static long update(int start, int end, int node, int left, int right){
        lazy_update(start, end, node);

        if(start > right || end < left){
            return segTree[node];
        }

        if(start >= left && end <= right){
            long length = end - start + 1;
            long val = start - left + 1;
            int half = start + (end - start) / 2;
            segTree[node] += length * (2*val + (length-1)) / 2;
            if(start != end){
                lazy[node*2] += val;
                count[node*2]++;
                lazy[node*2+1] += val + half - start + 1;
                count[node*2+1]++;
            }
            return segTree[node];
        }

        int half = start + (end - start) / 2;

        return segTree[node] = update(start, half, node*2, left, right) + update(half+1, end, node*2+1, left, right);
    }

    private static void lazy_update(int start, int end, int node){
        if(count[node] < 1){
            return ;
        }

        long length = end - start + 1;
        segTree[node] += length * (2 * lazy[node] + (length - 1) * count[node]) / 2;

        if(start != end){
            int half = start + (end - start) / 2;
            lazy[node*2] += lazy[node];
            count[node*2] += count[node];
            lazy[node*2+1] += lazy[node] + (half + 1 - start)*count[node];
            count[node*2+1] += count[node];
        }

        lazy[node] = 0;
        count[node] = 0;
    }

    private static long fine(int start, int end, int node, int index){
        lazy_update(start, end, node);
        if(start > index || end < index) return 0;

        if(start == end){
            return segTree[node];
        }

        int half = start + (end - start) / 2;
        return fine(start, half, node*2, index) + fine(half+1, end, node*2+1, index);
    }
}