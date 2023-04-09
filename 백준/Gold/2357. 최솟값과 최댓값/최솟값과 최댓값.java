import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, maxTree, minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        minTree = new int[N*4];
        maxTree = new int[N*4];

        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Init(1, N, 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findMin(1, N, 1, a, b)).append(" ").append(findMax(1, N, 1, a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static void Init(int start, int end, int Node){
        if(start == end){
            minTree[Node] = arr[start];
            maxTree[Node] = arr[start];
            return ;
        }

        int half = (start + end) / 2;

        Init(start, half, Node*2);
        Init(half+1, end, Node*2+1);

        minTree[Node] = Math.min(minTree[Node*2], minTree[Node*2+1]);
        maxTree[Node] = Math.max(maxTree[Node*2], maxTree[Node*2+1]);
    }

    private static int findMin(int start, int end, int Node, int left, int right){

        if(start > right || end < left){
            return Integer.MAX_VALUE;
        }

        if(start >= left && end <= right){
            return minTree[Node];
        }

        int half = (start+ end)/2;

        return Math.min(findMin(start, half, Node*2, left, right), findMin(half+1, end, Node*2+1, left, right));
    }

    private static int findMax(int start, int end, int Node, int left, int right){

        if(start > right || end < left){
            return Integer.MIN_VALUE;
        }

        if(start >= left && end <= right){
            return maxTree[Node];
        }

        int half = (start+ end)/2;

        return Math.max(findMax(start, half, Node*2, left, right), findMax(half+1, end, Node*2+1, left, right));
    }
}