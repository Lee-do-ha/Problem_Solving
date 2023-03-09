import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    // 출발 정점과 도착 정점, 가중치 저장할 클래스
    static class path implements Comparable<path>{
        int start, end;
        double distance;

        public path(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        // 가중치 순으로 정렬하기 위해 Comparable
        @Override
        public int compareTo(path o) {
            if(this.distance >= o.distance) return 1;

            return -1;
        }
    }

    // 부모 집합 저장할 배열
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        // 가중치 순으로 저장할 PriorityQueue
        PriorityQueue<path> PathPQ = new PriorityQueue<>();
        // 정점의 좌표들 저장할 리스트
        ArrayList<Double[]> dist = new ArrayList<>();
        parents = new int[N+1];

        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            parents[i] = i;
            
            // dist에 이미 자료가 있다면 현재 들어온 정점과의 거리 구하고 PriorityQueue에 저장
            if(!dist.isEmpty()){
                for(int k = 0 ; k < dist.size() ; k++){
                    double x = Math.abs(a - dist.get(k)[0]);
                    double y = Math.abs(b - dist.get(k)[1]);

                    double weight = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    PathPQ.add(new path(k+1,i,weight));
                }
            }

            // dist 배열에 좌표 저장
            dist.add(new Double[] {a,b});

        }
        // 간선이 몇개 연결되었는지 확인할 num
        int num = 0;
        // 최종 경로의 가중치
        double result = 0;
        // N개의 정점이므로 N-1개의 간선이 연결되면 종료
        while(num != N-1){
            path P = PathPQ.poll();

            // 이미 연결되있다면 다음 자료 꺼내기
            if(Find(P.start) == Find(P.end)) continue;

            // 연결이 안되어있다면 부모 집합 결합하고 가중치 더해주고 연결된 간선의 갯수 추가
            Union(P.start, P.end);
            result += P.distance;
            num++;
        }
        // 소수 2자리까지 출력
        System.out.printf("%.2f",result);
    }

    // 자신의 부모 집합 
    private static int Find(int a){
        if(parents[a] == a) return a;

        return parents[a] = Find(parents[a]);
    }

    // 집합 연결할 
    private static void Union(int x, int y){
        if(Find(x) == Find(y)) return;

        parents[Find(y)] = Find(x);
    }
}
