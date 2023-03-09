import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 통나무의 시작 좌표, 종료 좌표, 높이, 인덱스 넣을 클래스
    static class tree implements Comparable<tree>{
        int start, end, height, index;
        public tree(int start, int end, int height,int  index) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.index = index;
        }
        
        // 시작 좌표 기준으로 오름차순으로 정렬하고, 시작 좌표 같은 경우 종료 좌표로 오름 차순 정렬
        @Override
        public int compareTo(tree o) {
            if(this.start == o.start) return this.end - o.end;

            return this.start - o.start;
        }
    }
    
    // 부모 집합 저장할 배열
    static int[] parents;
    // 통나무들 넣을 배열
    static tree[] trees;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        parents = new int[N];
        trees = new tree[N];
        
        // 수 입력받고 통나무 배열과 부모 집합 배열 초기화
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            parents[i] = i;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            trees[i] = new tree(a,b,c,i);
        }
        
        // 통나무 배열 정렬
        Arrays.sort(trees);

        // 통나무들 정렬하고 앞 통나무의 종료지점과 뒤 통나무의 시작 지점 비교해서
        // 같은 집합으로 분류하기
        for (int i = 0; i < N-1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (trees[i].end >= trees[j].start) {
                    union(trees[i].index, trees[j].index);
                } else {
                    break;
                }
            }
        }
        
        // 부모 집합 비교해서 다를 경우 0, 같은 경우 1 StringBuilder에 넣기
        for(int i = 0 ; i < Q ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(unionFind(a-1) == unionFind(b-1)){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 부모 배열 갱신해주는 메소드
    private static int unionFind(int n){
        if(parents[n] == n) return n;

        return parents[n] = unionFind(parents[n]);
    }

    // 같은 집합으로 갱신해주는 메소드
    private static void union(int n , int m){
        if(unionFind(n) == unionFind(m)) return;

        parents[unionFind(m)] = unionFind(n);
    }
}
