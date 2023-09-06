import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer stringBuffer = new StringBuffer();

        // 총 테스트케이스 갯수
        int testCase = Integer.parseInt(br.readLine());


        char[][] map = new char[1000][1000];
        // 상근이와 불의 이동
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> fire = new LinkedList<>();

        // 각 테스트 케이스 경우
        for(int test = 0 ; test < testCase ; test++){

            // 너비 입력 받기
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 방문 체크할 배열
            visited = new boolean[M][N];

            // 지도 값 입력받기
            for(int i = 0 ; i < M ; i++){
                String arr = br.readLine();
                for(int j = 0 ; j < N ; j++){
                    map[i][j] = arr.charAt(j);
                    // 시작 지점 이동하는 큐에 넣기
                    if(map[i][j] == '@'){
                        visited[i][j] = true;
                        queue.add(new int[] {i, j});
                        // 불이 있는 지점 불 큐에 넣기
                    }else if(map[i][j] == '*'){
                        fire.add(new int[] {i, j});
                    }
                }
            }

            // 이동하는 시간 저장
            int time = 0;
            // 탈출했는지 여부 저장
            boolean flag = false;

            // BFS 시작
            // 이동 할 수 없거나 이미 탈출했을 경우 BFS 종료
            while (!queue.isEmpty() && !flag){

                // 현재 불 큐에 있는 크기만큼만 진행
                int fireSize = fire.size();
                for(int k = 0 ; k < fireSize ; k++){
                    int[] cur = fire.poll();

                    for(int i = 0 ; i < 4 ; i++){
                        int changeX = cur[0] + dx[i];
                        int changeY = cur[1] + dy[i];

                        // 좌표 내부에 있는지 체크
                        if (isInner(changeX, changeY, N, M)){
                            //다음 지점이 벽이 아니고 이미 불이 붙은 지점이 아닌경우에만 진행
                            if(map[changeX][changeY] != '#' && map[changeX][changeY] != '*'){
                                map[changeX][changeY] = '*';
                                fire.add(new int[] {changeX, changeY});
                            }
                        }

                    }

                }

                // 현재 큐에 있는 크기만큼만 진행
                int queueSize = queue.size();
                for(int k = 0 ; k < queueSize ; k++){
                    int[] cur = queue.poll();

                    // 지금 탈출가능한 지점이라면 탈출가능으로 바꾸고 시간 출력값에 추가하고 종료
                    if(cur[0] == 0 || cur[0] == M-1 || cur[1] == 0 || cur[1] == N-1){
                        flag = true;
                        stringBuffer.append(time+1).append("\n");
                        break;
                    }

                    for(int i = 0 ; i < 4 ; i++){
                        int changeX = cur[0] + dx[i];
                        int changeY = cur[1] + dy[i];

                        // 좌표 내부에 있고 방문안한점만 진행
                        if(isInner(changeX, changeY, N, M) && !visited[changeX][changeY]){
                            if(map[changeX][changeY] == '.'){
                                queue.add(new int[] {changeX, changeY});
                                visited[changeX][changeY] = true;
                            }
                        }
                    }
                }
                // 한 사이클 돌았으면 시간 추가
                time++;
            }
            
            // 탈출못했을 경우
            if(!flag){
                stringBuffer.append("IMPOSSIBLE").append("\n");
            }

            // 테스트케이스가 끝났으면 큐 초기화
            queue.clear();
            fire.clear();

        }

        System.out.println(stringBuffer);

    }

    private static boolean isInner(int x1, int y1, int m, int n){

        if(x1 < 0 || x1 >= n || y1 < 0 || y1 >= m){
            return false;
        }

        return true;
    }
}