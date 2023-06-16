import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static int ans;
    static Queue<int[]> queue;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        ArrayList<int[]> list = new ArrayList<>();
        ans = 0;
        queue = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0){
                    list.add(new int[] {i,j});
                }
            }
        }

        for(int i = 0 ; i < list.size() ; i++){
            for(int j = i+1 ; j < list.size() ; j++){
                int[] point1 = list.get(i);
                int[] point2 = list.get(j);

                search(point1, point2);

            }
        }

        System.out.println(ans);

    }

    private static void search(int[] point1, int[] point2){
        map[point1[0]][point1[1]] = 1;
        map[point2[0]][point2[1]] = 1;

        int num = 0;

        boolean[][] visited = new boolean[map.length][map[0].length];

        for(int i = 0 ; i < map.length ; i++){
            for(int j = 0 ; j < map[i].length ; j++){
                if(map[i][j] == 2 && !visited[i][j]){

                    boolean flag = true;
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;

                    int blackNum = 0;

                    while(!queue.isEmpty()){
                        int[] cur = queue.poll();
                        blackNum++;

                        for(int k = 0 ; k < 4 ; k++){
                            if(cur[0] + dx[k] >= 0 && cur[0] + dx[k] < map.length && cur[1] + dy[k] >= 0 && cur[1] + dy[k] < map[i].length){
                                if(map[cur[0] + dx[k]][cur[1] + dy[k]] == 0){
                                    flag = false;
                                }

                                if(map[cur[0] + dx[k]][cur[1] + dy[k]] == 2 && !visited[cur[0] + dx[k]][cur[1] + dy[k]]){
                                    queue.add(new int[] {cur[0]+dx[k], cur[1] + dy[k]});
                                    visited[cur[0] + dx[k]][cur[1] + dy[k]] = true;
                                }
                            }
                        }
                    }
                    if(flag){
                        num += blackNum;
                    }
                }
            }
        }
        map[point1[0]][point1[1]] = 0;
        map[point2[0]][point2[1]] = 0;
        if(num > ans) ans = num;
    }
}