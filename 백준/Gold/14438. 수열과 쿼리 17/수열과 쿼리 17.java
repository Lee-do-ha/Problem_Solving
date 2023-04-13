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

    private static void init(int start, int end, int node){
        if(start == end){
            segTree[node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        init(start, half, node*2);
        init(half+1, end, node*2+1);

        segTree[node] = Math.min(segTree[node*2], segTree[node*2+1]);
    }

    private static void update(int start, int end, int node, int index, int changenum){

        if(start <= index && index <= end){
            int half = (start + end) / 2;
            if(start == end){
                segTree[node] = changenum;
                arr[index] = changenum;
            }else{
                update(start, half, node*2, index, changenum);
                update(half+1, end, node*2+1, index, changenum);
                segTree[node] = Math.min(segTree[node*2], segTree[node*2+1]);
            }
        }
    }

    private static int find(int start, int end, int node, int left, int right){
        if(start > right || end < left){
            return Integer.MAX_VALUE;
        }

        if(start >= left && end <= right){
            return segTree[node];
        }

        int half = (start + end) / 2;

        return Math.min(find(start, half, node*2, left, right), find(half+1, end, node*2+1, left, right));
    }
}