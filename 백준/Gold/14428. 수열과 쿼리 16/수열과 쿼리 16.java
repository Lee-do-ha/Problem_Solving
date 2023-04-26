import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[0] = Integer.MAX_VALUE;

        segTree = new int[N*4];
        init(1, N, 1);

        int M = Integer.parseInt(br.readLine().trim());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1){
                update(1, N, 1, b, c);
            }else if(a == 2){
                sb.append(select(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void init(int start, int end, int node){
        if(start == end){
            segTree[node] = start;
            return ;
        }

        int half = (end + start) / 2;
        init(start, half, node*2);
        init(half + 1, end, node*2 + 1);

        if(arr[segTree[node*2]] > arr[segTree[node*2+1]]){
            segTree[node] = segTree[node*2+1];
        }else if(arr[segTree[node*2]] < arr[segTree[node*2+1]]){
            segTree[node] = segTree[node*2];
        }else{
            segTree[node] = Math.min(segTree[node*2],segTree[node*2+1] );
        }
    }

    private static void update(int start, int end, int node, int index, int changenum){
        if(start <= index && index <= end){
            if(start == end){
                arr[index] = changenum;
            }else{
                int half = (end + start) / 2;
                update(start, half, node*2, index, changenum);
                update(half + 1, end, node*2 + 1, index, changenum);

                if(arr[segTree[node*2]] > arr[segTree[node*2+1]]){
                    segTree[node] = segTree[node*2+1];
                }else if(arr[segTree[node*2]] < arr[segTree[node*2+1]]){
                    segTree[node] = segTree[node*2];
                }else{
                    segTree[node] = Math.min(segTree[node*2],segTree[node*2+1] );
                }
            }
        }
    }

    private static int select(int start, int end, int node, int left, int right){
        if(end < left || start > right){
            return 0;
        }

        if(start >= left && end <= right){
            return segTree[node];
        }

        int half = (end + start) / 2;
        int a = select(start, half, node*2, left, right);
        int b = select(half + 1, end, node*2 + 1, left, right);

        if(arr[a] < arr[b]){
            return a;
        }else if(arr[a] > arr[b]){
            return b;
        }else{
            return Math.min(a, b);
        }
    }
}