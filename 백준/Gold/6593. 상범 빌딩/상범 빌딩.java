import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][][] map;
    static boolean[][][] visited;
    static Queue<int[]> queue;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[30][30][30];
        queue = new LinkedList<>();

        while(N != 0 && F != 0 && M != 0){

            boolean flag = false;
            visited = new boolean[F][N][M];

            for(int floor = 0 ; floor < F ; floor++){
                for(int i = 0 ; i < N ; i++){
                    String str = br.readLine();
                    for(int j = 0 ; j < M ; j++){
                        map[floor][i][j] = str.charAt(j);
                        if(map[floor][i][j] == 'S'){
                            queue.add(new int[] {floor, i, j});
                            visited[floor][i][j] = true;
                        }
                    }
                }

                br.readLine();
            }

            int time = 0;

            while (!queue.isEmpty()){

                int size = queue.size();

                for(int i = 0 ; i < size ; i++){

                    int[] cur = queue.poll();
                    
                    if(map[cur[0]][cur[1]][cur[2]] == 'E'){
                        flag = true;
                        queue.clear();
                        break;
                    }

                    for(int k = 0 ; k < 6 ; k++){
                        int changeZ = cur[0] + dz[k];
                        int changeX = cur[1] + dx[k];
                        int changeY = cur[2] + dy[k];

                        if(changeX < 0 || changeY < 0 || changeZ < 0 || changeX >= N || changeY >= M || changeZ >= F) continue;

                        if(!visited[changeZ][changeX][changeY] && map[changeZ][changeX][changeY] == '.' || map[changeZ][changeX][changeY] == 'E'){
                            queue.add(new int[] {changeZ, changeX, changeY});
                            visited[changeZ][changeX][changeY] = true;
                        }
                    }
                }

                time++;
            }

            if(flag){
                stringBuilder.append("Escaped in ").append(time-1).append(" minute(s).").append("\n");
            }else{
                stringBuilder.append("Trapped!").append("\n");
            }

            st = new StringTokenizer(br.readLine());
            F = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

        }
        System.out.println(stringBuilder);
    }
}