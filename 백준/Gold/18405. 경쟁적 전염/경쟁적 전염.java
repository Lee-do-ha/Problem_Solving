import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int end = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int time = 0;
    
        // 탐색하려는 좌표
        int curx = x-1;
        int cury = y-1;

        // 탐색하려는 좌표 방문 체크
        visited[curx][cury] = true;
        // 탐색하려는 좌표가 0이 아니라면 해당 좌표값 출력하고 종료
        if(map[curx][cury] != 0){
            System.out.println(map[curx][cury]);
            return;
        }

        // 좌표 큐에 넣고 BFS 탐색 진행
        queue.add(new int[] {curx, cury});


        // 시간이 다 될때까지 진행
        while(time != end){
            time++;
        
            // 큐가 비었는지 체크
            if(!queue.isEmpty()){

                int size = queue.size();
                
                // 큐 사이즈만큼만 진행
                for(int i = 0 ; i < size ; i++){
                    int xx = queue.peek()[0];
                    int yy = queue.peek()[1];

                    queue.poll();
                    
                    // 방문하려는 좌표
                    for(int k = 0 ; k < 4 ; k++){
                        int changeX = xx + dx[k];
                        int changeY = yy + dy[k];
                            
                        // 방문하려는 좌표가 방문 가능하면 방문 체크하고 큐에 추가
                        if(changeX >= 0 && changeY >= 0 && changeY < N && changeX < N && visited[changeX][changeY] == false){
                            visited[changeX][changeY] = true;
                            queue.add(new int[] {changeX, changeY});
                            // 만약 방문하려는 좌표의 값이 0이 아니라면 PriorityQueue에 추가
                            if(map[changeX][changeY] != 0){
                                pq.add(map[changeX][changeY]);
                            }
                        }
                    }
                }
            }
            // PriorityQueue가 안비었다면 전염된다는 뜻이므로 가장 낮은 바이러스가 먼저이므로 PriorityQueue에서 꺼낸값이 정답
            if(!pq.isEmpty()){
                System.out.println(pq.poll());
                return;
            }
            
            // 시간이 다 지나도 PriorityQueue가 비었다면 전염이 안됐으므로 0 출력
            if(time == end && pq.isEmpty()){
                System.out.println(0);
                return;
            }

        }
        // 탐색가능한 시간이 0이고 해당 좌표값이 0인 경우를 대비
        System.out.println(0);
    }
}
