import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        cheese = 0;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1){
                    cheese++;
                }

            }
        }

        int cheeseNum = 0;
        int time = 0;

        while (cheese != 0){

            cheeseNum = cheese;
            time++;
            visited = new boolean[N][M];
            bfs();

        }

        System.out.println(time);
        System.out.println(cheeseNum);

    }

    private static void bfs(){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()){

            int[] cur = queue.poll();

            for(int k = 0 ; k < 4 ; k++){
                int changeX = cur[0] + dx[k];
                int changeY = cur[1] + dy[k];

                if(changeX < 0 || changeX >= map.length || changeY < 0 || changeY >= map[0].length || visited[changeX][changeY]){
                    continue;
                }

                visited[changeX][changeY] = true;

                if(map[changeX][changeY] == 0){
                    queue.add(new int[] {changeX, changeY});
                }else{
                    cheese--;
                    map[changeX][changeY] = 0;
                }

            }

        }

    }

}