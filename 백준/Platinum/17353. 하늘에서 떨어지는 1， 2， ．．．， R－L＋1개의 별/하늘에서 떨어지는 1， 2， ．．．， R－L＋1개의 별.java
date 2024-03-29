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
        // 배열 값 저장
        arr = new long[N+1];
        
        // 세그먼트 트리
        segTree = new long[N*4];
        
        // Lazy값 갱신
        lazy = new long[N*4];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1 ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 초기 구성
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

    // 초기 트리 구성
    private static long initTree(int start, int end, int node){
        // 구간이 1개라면 해당하는 배열 값 입력
        if(start == end){
            segTree[node] = arr[start];
            return segTree[node];
        }

        // 구간 나누어서 진행
        int half = start + (end - start) / 2;

        return segTree[node] = initTree(start, half, node*2) + initTree(half+1, end, node*2+1);
    }

    // 트리 구간 업데이트
    private static long update(int start, int end, int node, int left, int right){
        lazy_update(start, end, node);

        // 업데이트 구간 벗어났다면 해당 트리값 갱신 없이 가져오기
        if(start > right || end < left){
            return segTree[node];
        }

        // 범위 내에 포함된다면 값 갱신
        if(start >= left && end <= right){
            long length = end - start + 1;
            long val = start - left + 1;
            int half = start + (end - start) / 2;
            
            // 등차수열로 진행되므로 공식에 맞는 값 갱신
            segTree[node] += length * (2*val + (length-1)) / 2;
            
            // 리프노드가 아니라면 자식 노드들에게 모두 Lazy값 갱신해주기
            if(start != end){
                lazy[node*2] += val;
                count[node*2]++;
                lazy[node*2+1] += val + half - start + 1;
                count[node*2+1]++;
            }
            return segTree[node];
        }

        // 구간 나누어서 진행
        int half = start + (end - start) / 2;

        return segTree[node] = update(start, half, node*2, left, right) + update(half+1, end, node*2+1, left, right);
    }

    // Lazy값 업데이트
    private static void lazy_update(int start, int end, int node){
        // 클릭수 없다면 종료
        if(count[node] < 1){
            return ;
        }

        // 총 구간 길이
        long length = end - start + 1;
        // 트리 값 갱신
        segTree[node] += length * (2 * lazy[node] + (length - 1) * count[node]) / 2;

        // 리프노드 아니라면 다시 구간 나누어서 Lazy값 나누어주기
        if(start != end){
            int half = start + (end - start) / 2;
            lazy[node*2] += lazy[node];
            count[node*2] += count[node];
            lazy[node*2+1] += lazy[node] + (half + 1 - start)*count[node];
            count[node*2+1] += count[node];
        }

        // Lazy값과 count값 모두 0으로 
        lazy[node] = 0;
        count[node] = 0;
    }

    // 해당 인덱스에 해당하는 값 찾기
    private static long fine(int start, int end, int node, int index){
        lazy_update(start, end, node);
        // 구간 벗어난다면 0 return
        if(start > index || end < index) return 0;

        // 구간 벗어나지 않고 진행됐다면 현재 트리값이 정답
        if(start == end){
            return segTree[node];
        }

        // 구간 나누어서 
        int half = start + (end - start) / 2;
        return fine(start, half, node*2, index) + fine(half+1, end, node*2+1, index);
    }
}
