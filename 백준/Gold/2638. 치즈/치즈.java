import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int cheeseNum = 0;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheeseNum++;
            }
        }

        // 방문 체크
        boolean[][] visited = new boolean[N][M];
        // 공기인지 체크
        boolean[][] isAir = new boolean[N][M];
        // BFS진행할 Queue
        Queue<int[]> queue = new LinkedList<>();
        // 녹은 치즈들 저장할 배열
        ArrayList<int[]> List = new ArrayList<>();
        
        // 총 걸리는 
        int time = 0;

        while(cheeseNum > 0){
            queue.add(new int[] {0,0});
            visited[0][0] = true;
            isAir[0][0] = true;

            // 공기 찾기 BFS
            while (!queue.isEmpty()){
                int[] cur = queue.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int changeX = cur[0] + dx[i];
                    int changeY = cur[1] + dy[i];
                    if(isPossible(changeX,changeY) && !visited[changeX][changeY] && map[changeX][changeY] == 0){
                        queue.add(new int[] {changeX, changeY});
                        visited[changeX][changeY] = true;
                        isAir[changeX][changeY] = true;
                    }
                }
            }

            // 치즈확인하기
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        queue.add(new int[] {i,j});
                        visited[i][j] = true;

                        while(!queue.isEmpty()){
                            int airnum = 0;
                            int[] cur = queue.poll();

                            // 치즈 계속 방문하기
                            for(int k = 0 ; k < 4 ; k++){
                                int changeX = cur[0] + dx[k];
                                int changeY = cur[1] + dy[k];
                                if(isPossible(changeX,changeY)) {
                                    if (!visited[changeX][changeY]) {
                                        queue.add(new int[]{changeX, changeY});
                                        visited[changeX][changeY] = true;
                                    }
                                    // 치즈 4방에 공기갯수 세기
                                    if(map[changeX][changeY] == 0 && isAir[changeX][changeY] == true){
                                        airnum++;
                                    }
                                }
                            }
                            // 공기갯수가 2개 이상이라면 녹는 치즈이므로 리스트에 추가
                            if(airnum >= 2){
                                List.add(new int[] {cur[0], cur[1]});
                            }
                        }
                    }
                }
            }
            
            // 남은 치즈개수 갱신
            cheeseNum -= List.size();
            
            // 녹은 치즈들 0으로 바꿔주기
            for(int[] o : List){
                map[o[0]][o[1]] = 0;
            }
            
            // 리스트 초기화
            List.clear();
            time++;

            // 배열 다시 초기화
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    isAir[i][j] = false;
                    visited[i][j] = false;
                }
            }
        }
        System.out.println(time);
    }

    // 좌표에 해당하는지 체크할 메소드
    private static boolean isPossible(int a, int b){
        if(a < 0 || a >= N || b < 0 || b >= M) return false;

        return true;
    }
}
