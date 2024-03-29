import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static ArrayList[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        segTree = new ArrayList[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트트리 세팅
        Init(1, N, 1);

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sb.append(find(1, N, 1, a, b, c)).append("\n");
        }
        System.out.println(sb);
    }

    private static void Init(int start, int end, int node){
        // start와 end가 같으면 list만들고 해당 인덱스값 넣기
        if(start == end){
            segTree[node] = new ArrayList<>();
            segTree[node].add(arr[start]);
            return ;
        }

        int half = (start + end) / 2;

        Init(start, half, node*2);
        Init(half+1, end, node*2+1);


        // 양 옆의 값들 추가해주기
        segTree[node] = new ArrayList();
        segTree[node].addAll(segTree[node*2]);
        segTree[node].addAll(segTree[node*2+1]);
        // 이분탐색위해 정렬
        Collections.sort(segTree[node]);
    }

    private static int find(int start, int end, int node, int left, int right, int num){
        // 범위 벗어난 경우 0개
        if(start > right || end < left){
            return 0;
        }

        // 범위 내에 있는경우 해당 배열의 num이상인 값의 갯수 찾기
        if(left <= start && right >= end && !segTree[node].isEmpty()){
            return binarySearch(segTree[node], num);
        }

        // 구간 나누어서 계속 진행
        int half = (start + end) / 2;

        return find(start, half, node*2, left, right, num) + find(half+1, end, node*2+1, left, right, num);
    }

    // 모든 배열 정렬되어있으므로 이분 탐색으로 중간인덱스 찾기
    private static int binarySearch(ArrayList<Integer> List, int num){
        int start = 0;
        int end = List.size()-1;
        int center = (start + end) / 2;

        if(List.size() == 1){
            if(List.get(0) > num) return 1;
            else return 0;
        }

        if(List.get(0) > num){
            return List.size();
        }else if(List.get(List.size()-1) <= num){
            return 0;
        }else{
            while(start < end){

                if(List.get(center) > num){
                    end = center;
                }else{
                    start = center+1;
                }
                center = (start + end) / 2;
            }
            return List.size() - start;
        }
    }
}
/*
5
2 2 2 2 2
1
1 5 2
 */
