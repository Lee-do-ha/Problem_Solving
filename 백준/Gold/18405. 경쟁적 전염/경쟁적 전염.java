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

        int curx = x-1;
        int cury = y-1;

        visited[curx][cury] = true;
        if(map[curx][cury] != 0){
            System.out.println(map[curx][cury]);
            return;
        }

        queue.add(new int[] {curx, cury});


        while(time != end){
            time++;

            if(!queue.isEmpty()){

                int size = queue.size();

                for(int i = 0 ; i < size ; i++){
                    int xx = queue.peek()[0];
                    int yy = queue.peek()[1];

                    queue.poll();

                    for(int k = 0 ; k < 4 ; k++){
                        int changeX = xx + dx[k];
                        int changeY = yy + dy[k];

                        if(changeX >= 0 && changeY >= 0 && changeY < N && changeX < N && visited[changeX][changeY] == false){
                            visited[changeX][changeY] = true;
                            queue.add(new int[] {changeX, changeY});
                            if(map[changeX][changeY] != 0){
                                pq.add(map[changeX][changeY]);
                            }
                        }
                    }
                }
            }
            if(!pq.isEmpty()){
                System.out.println(pq.poll());
                return;
            }

            if(time == end && pq.isEmpty()){
                System.out.println(0);
                return;
            }

        }
        System.out.println(0);
    }
}