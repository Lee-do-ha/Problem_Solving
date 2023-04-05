import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] map;
    static int N, M, K;
    static Queue<int[]> queue;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();

        map = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        visited = new boolean[N][M][K+1];

        queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});
        visited[0][0][0] = true;

        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int[] cur = queue.poll();
                if(cur[0] == N-1 && cur[1] == M-1){
                    queue.clear();
                    break;
                }

                for(int k = 0 ; k < 4 ; k++){
                    int changeX = cur[0] + dx[k];
                    int changeY = cur[1] + dy[k];
                    if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                    if(map[changeX][changeY] == 0 && !visited[changeX][changeY][cur[2]]){
                        queue.add(new int[] {changeX, changeY, cur[2]});
                        visited[changeX][changeY][cur[2]] = true;
                    }else if(map[changeX][changeY] == 1 && cur[2] < K && !visited[changeX][changeY][cur[2]+1]){
                        if(time%2 == 0){
                            queue.add(new int[] {changeX, changeY, cur[2]+1});
                            visited[changeX][changeY][cur[2]+1] = true;
                        }else{
                            queue.add(new int[]{cur[0],cur[1],cur[2]});
                        }
                    }
                }
            }
            time++;
        }
        boolean flag = false;

        for(int i = 0 ; i < K+1 ; i++){
            if(visited[N-1][M-1][i]){
                flag = true;
                break;
            }
        }

        if(flag){
            System.out.println(time);
        }else{
            System.out.println(-1);
        }
    }

}
