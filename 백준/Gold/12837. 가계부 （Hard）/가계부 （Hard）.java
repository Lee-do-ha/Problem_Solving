import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static long[] segTree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        segTree = new long[N*4];
        arr = new long[N+1];

        for(int i = 0 ; i < Q ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                arr[b] += c;
                update(1, N, 1, b);
            }else if(a == 2){
                sb.append(find(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(int start, int end,int node, int index){

        if(start <= index && end >= index){
            if(start == end){
                segTree[node] = arr[index];
            }else{
                int half = (start + end) / 2;

                update(start, half, node*2, index);
                update(half+1, end, node*2 + 1, index);
                segTree[node] = segTree[node*2] + segTree[node*2+1];
            }
        }
    }

    private static long find(int start, int end, int node, int left, int right){

        if(left <= start && right >= end){
            return segTree[node];
        }

        if(left > end || right < start){
            return 0;
        }

        int half = (start + end) / 2;

        return find(start, half, node*2, left, right) + find(half+1, end, node*2+1, left, right);
    }
}