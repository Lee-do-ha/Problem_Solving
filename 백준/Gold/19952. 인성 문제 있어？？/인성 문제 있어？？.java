import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            map = new int[H+1][W+1];
            visited = new boolean[H+1][W+1];
            queue = new LinkedList<>();

            for(int k = 0 ; k < O ; k++){
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }

            boolean flag = false;
            queue.add(new int[] {startX, startY, F});
            visited[startX][startY] = true;

            while (!queue.isEmpty()){

                int[] cur = queue.poll();

                if(cur[0] == endX && cur[1] == endY){
                    flag = true;
                    break;
                }

                if(cur[2] == 0){
                    continue;
                }

                for(int k = 0 ; k < 4 ; k++){
                    int changeX = cur[0] + dx[k];
                    int changeY = cur[1] + dy[k];

                    if(changeX < 1 || changeY < 1 || changeX > H || changeY > W) continue;

                    if(!visited[changeX][changeY]){
                        if(map[changeX][changeY] - map[cur[0]][cur[1]] <= cur[2]){
                            queue.add(new int[] {changeX, changeY, cur[2]-1});
                            visited[changeX][changeY] = true;
                        }
                    }

                }

            }

            if(flag){
                stringBuilder.append("잘했어!!").append("\n");
            }else{
                stringBuilder.append("인성 문제있어??").append("\n");
            }

        }

        System.out.println(stringBuilder);
    }
}