import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 인덱스와 x좌표, y좌표 저장할 클래스
    static class coordinate implements Comparable<coordinate>{
        int index,x, y;

        public coordinate(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
        // x값 오름차순으로 정렬하기위하
        @Override
        public int compareTo(coordinate o) {
            return this.x - o.x;
        }
    }

    // 시작 인덱스와 종료 인덱스 번호와 기울기 저장할 클래스
    static class Node implements Comparable<Node>{
        int x,y;
        double weight;

        public Node(int x, int y, double weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        // 정답을 뽑기위한 compareto
        @Override
        public int compareTo(Node o) {
            if(this.weight == o.weight){
                if(this.x == o.x){
                    return this.y - o.y;
                }else{
                    return this.x-o.x;
                }
            }else{
                if(this.weight > o.weight){
                    return -1;
                }else{
                    return 1;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 노드 담을 PriotityQueue
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 좌표담을 리스트
        ArrayList<coordinate> List = new ArrayList<>();
        ArrayList<coordinate> copyList = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        double curMax = 0;
        int start = 0, end = 0;
        for(int i = 1 ; i < N+1 ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // i = 인덱스 번호 , x = x좌표 , y = y좌표
            // x순으로 정렬할 리스트
            List.add(new coordinate(i,x,y));
            // 원본 리스트
            copyList.add(new coordinate(i,x,y));
        }

        // x순으로 정렬
        Collections.sort(List);

        for(int i = 0 ; i < N-1 ; i++){
            // 현재 좌표
            int curx = List.get(i).x;
            int cury = List.get(i).y;
            // 다음 좌표
            int nextx = List.get(i+1).x;
            int nexty = List.get(i+1).y;
            // 기울기
            double xy = (double)((Math.abs(cury - nexty)) / (Math.abs(curx - nextx)));

            // 현재 기울기가 이전 기울기 보다 크면 PriorityQueue 비우고 기울기 갱신
            if(xy > curMax){
                pq.clear();
                pq.add(new Node(Math.min(List.get(i).index, List.get(i+1).index), Math.max(List.get(i).index, List.get(i+1).index), xy));
                curMax = xy;
            // 기울기가 같다면 priorityQueue에 추가
            }else if(xy == curMax){
             pq.add(new Node(Math.min(List.get(i).index, List.get(i+1).index), Math.max(List.get(i).index, List.get(i+1).index), xy));
            }
        }

        // 결과 노드
        Node result = pq.poll();

        // 시작 노드에서 같은 기울기를 가진 더 짧은 좌표가 있는지 확인
        // 시작노드의 x좌표와 y좌표
        int curx = copyList.get(result.x-1).x;
        int cury = copyList.get(result.x-1).y;

        // 더 큰 좌표의 인덱스
        int endIndex = result.y;
        
        // 시작 노드의 인덱스부터 종료 노드의 인덱스까지 탐색
        for(int i = result.x ; i < result.y-1 ; i++){
            // 기울기가 작은게 나왔다면 종료 인덱스 갱신하기
            if((double)(Math.abs(cury-copyList.get(i).y)/Math.abs(curx-copyList.get(i).x)) == result.weight){
                // 리스트는 0부터 시작했으므로 +1 해주기
                endIndex = i+1;
                // 찾았다면 더 큰 좌표는 볼 필요없으므로 break;
                break;
            }
        }
        System.out.println(result.x + " " + endIndex);
    }
}
