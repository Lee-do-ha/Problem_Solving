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
        segTree = new int[MAX*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;

        for(int i = 1 ; i < K ; i++){
            update(0, MAX, 1, arr[i], 1);
        }

        int find = (K+1)/2;

        for(int i = K ; i < N+1 ; i++){
            update(0, MAX, 1, arr[i], 1);
            ans += find(0, MAX, 1, find);
            update(0, MAX, 1, arr[i-K+1], -1);
        }
        System.out.println(ans);
    }

    private static int update(int start, int end, int node, int index, int changenum){
        if(start > index || end < index){
            return segTree[node];
        }

        if(start == end){
            return segTree[node] += changenum;
        }

        int half = (start + end) / 2;

        return segTree[node] = update(start, half, node*2, index,changenum) + update(half+1, end, node*2+1, index, changenum);
    }

    private static int find(int start, int end, int node, int k){

        if(start == end){
            return start;
        }

        int half = (start + end) /2;

        if(segTree[node*2] >= k){
            return find(start, half, node*2, k);
        }else{
            return find(half+1, end, node*2+1, k-segTree[node*2]);
        }
    }
}