// 모든 행성에 관해서 탐색하여 경로 저장할 경우 메모리 초과함
// => 모든 행성의 거리는 x,y,z기준 가장 최소값이므로 각 x,y,z 기준으로 정렬하고 큐에 저장
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 행성 번호와 좌표 저장할 클래스
    static class space{
        int index, x, y, z;

        public space(int index,int x, int y, int z) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // 행성 번호들과 해당 경로의 길이 저장
    // 경로의 길이순으로 정렬하기 위해 Comparable
    static class path implements Comparable<path>{
        int start, end, price;

        public path(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        @Override
        public int compareTo(path o) {
            return this.price -  o.price;
        }
    }

    // 조상 저장할 배열
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<path> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        // 조상 본인으로 초기화
        for(int i = 0 ; i < N ; i++){
            parents[i] = i;
        }

        space[] Spaces = new space[N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            Spaces[i] = new space(i ,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        // x기준으로 정렬
        Arrays.sort(Spaces, ((o1, o2) -> o1.x - o2.x));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }
        // y기준으로 정렬
        Arrays.sort(Spaces, ((o1, o2) -> o1.y - o2.y));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }
        // z기준으로 정렬
        Arrays.sort(Spaces, ((o1, o2) -> o1.z - o2.z));
        for(int i = 0 ; i < N-1 ; i++){
            pq.add(new path(Spaces[i].index, Spaces[i+1].index, Math.min(Math.abs(Spaces[i].x - Spaces[i+1].x), Math.min(Math.abs(Spaces[i].y - Spaces[i+1].y), Math.abs(Spaces[i].z - Spaces[i+1].z)))));
        }

        // 최종 값
        int result = 0;
        // 몇개 연결했는지 세기 위함
        int n = 0;
        
        // 크루스칼 시작
        while(!pq.isEmpty()){
            path cur = pq.poll();
            // 이미 이어져있다면 continue
            if(findSet(cur.start) == findSet(cur.end)) continue;

            // 조상 결합하고 값 더해주고 연결된 경로수 더해주기
            unionSet(cur.start, cur.end);
            result += cur.price;
            n++;
            // 총 N개이므로 N-1개의 경로가 이어지면 종료
            if(n == N-1) break;
        }

        System.out.println(result);
    }

    // 조상 찾는 메소드
    private static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    // 조상 이어줄 메소드
    private static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }
}
