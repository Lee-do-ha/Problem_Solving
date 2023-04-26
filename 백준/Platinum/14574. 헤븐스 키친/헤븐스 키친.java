import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 각 요리사 정보 저장할 클래스
    static class cook{
        int p, x;
        public cook(int p, int x) {
            this.p = p;
            this.x = x;
        }
    }

    // MST연결하기위한 시작점, 도착점, 경로값 저장할 클래스
    static class path implements Comparable<path>{
        int start, end;
        long popul;

        public path(int start, int end, long popul) {
            this.start = start;
            this.end = end;
            this.popul = popul;
        }

        // 내림차순으로 PriorityQueue 사용하기위함
        @Override
        public int compareTo(path o) {
            if(this.popul > o.popul){
                return -1;
            }else{
                return  1;
            }
        }
    }

    // 소속된 집합 표시하기 위한 배열
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<path> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        // 어느 요리사끼리 연결되어있는지 저장하기위한 리스트
        ArrayList<Integer>[] List = new ArrayList[N];
        // 몇번 상대해야 하는지 저장할 배열
        int[] used = new int[N];


        cook[] arr = new cook[N];

        // 초기화하고 생성
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new cook(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parents[i] = i;
            List[i] = new ArrayList<>();
        }

        // 모든 요리사끼리의 대결 정보 PriorityQueue에 저장
        for(int i = 0 ; i < N ; i++){
            for(int j = i + 1 ; j < N ; j++){
                int x = Math.abs(arr[i].p - arr[j].p);
                int y = arr[i].x + arr[j].x;
                pq.add(new path(i, j, (long)Math.floor(y/x)));
            }
        }

        // 연결된 대결 갯수 저장
        int connected = 0;
        // 결과값 저장
        long result = 0;
        while(!pq.isEmpty()){
            path cur = pq.poll();
            // 연결안되어있다면 연결 시키고 값 갱신
            if(findSet(cur.start) != findSet(cur.end)){
                unionSet(cur.start, cur.end);
                result += cur.popul;
                connected++;
                // 양방향으로 연결
                List[cur.start].add(cur.end);
                List[cur.end].add(cur.start);
                // 둘다 대결횟수 추가
                used[cur.start]++;
                used[cur.end]++;

                if(connected == N-1){
                    break;
                }
            }
        }

        sb.append(result).append("\n");

        // 모두 대결이 끝나기 전까지 진행
        while(!tf(used)){
            for(int i = 0 ; i < N ; i++){
                // 1경기만 남았다면 해당 요리사는 연결되어있는 사람에게 이기고 천국가면 됨
                if(used[i] == 1){
                    if(!List[i].isEmpty()){
                        for(int k = 0 ; k < List[i].size() ; k++){
                            // 가지고 있는 상대중에 0보다 크면 대결 진행
                            if(used[List[i].get(k)] > 0 ){
                                sb.append(List[i].get(k)+1).append(" ").append(i+1).append("\n");
                                // 대결 횟수 감소
                                used[i]--;
                                // 상대의 대결 횟수도 감소
                                used[List[i].get(k)]--;
                                // 리스트에서 현재 요리사 제거
                                List[i].remove(k);
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(sb);

    }

    // 소속된 집합 찾기
    static int findSet(int a){
        if(a == parents[a]) return a;

        return parents[a] = findSet(parents[a]);
    }

    // 소속된 집합 합치기
    static void unionSet(int a, int b){
        parents[findSet(b)] = findSet(a);
    }

    // 모두 종료되었는지 확인
    static boolean tf(int[] arr){
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] != 0) return false;
        }
        return true;
    }
}
