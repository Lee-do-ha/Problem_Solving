import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1194 {
    // 위치 좌표와 차원 저장
    static class position{
        int x, y, z;

        public position(int x, int y, int z) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int n,m;
    static char[][] map;
    static Queue<position> queue;
    static boolean[][][] visited;
    static int[] dx = {1,-1,0,0}, dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
        visited = new boolean[n][m][1<<6];
        map = new char[n][m];
        
        // 시작점 잡고 방문체크하고 .으로 바꿔주기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    // 처음엔 키가 없으므로 차원 0 으로 넣기
                    queue.add(new position(i, j, 0));
                    visited[i][j][0] = true;
                    map[i][j] = '.';
                }
            }
        }

        // 걸리는 시간 체크
        int time = 0;
        while(!queue.isEmpty()){
            time++;
            int size = queue.size();

            for(int t = 0 ; t < size ; t++){
                position cur = queue.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int changeX = cur.x + dx[i], changeY = cur.y + dy[i];

                    // 4방 탐색
                    if(positionCheck(changeX, changeY)){
                        // 출구 만나면 탈출
                        if(map[changeX][changeY] == '1'){
                            System.out.println(time);
                            return;
                        }
                        
                        // 가려는 좌표가 열 수 있는 문이고 방문하지않았다면 같은 차원에서 이동
                        if(isDoor(changeX, changeY) && !visited[changeX][changeY][cur.z] && openDoor(cur.z, map[changeX][changeY])){
                            visited[changeX][changeY][cur.z] = true;
                            queue.add(new position(changeX, changeY, cur.z));
                        
                            // 가려는 좌표에 열쇠가 있는 경우
                        }else if(isKey(changeX, changeY) && !visited[changeX][changeY][cur.z]){
                            // 해당 열쇠 추가했을 때, 방문하지않은점이면 큐에 넣어주기
                            if(!visited[changeX][changeY][getKey(cur.z, map[changeX][changeY])]){
                                queue.add(new position(changeX, changeY, getKey(cur.z, map[changeX][changeY])));
                                visited[changeX][changeY][getKey(cur.z, map[changeX][changeY])] = true;
                            }
                            // 키 얻기전 차원에서 방문체크
                            visited[changeX][changeY][cur.z] = true;
                            
                            // 가려는 좌표가 이동가능한 점이고 방문하지않은점이면 큐에 넣어주기
                        }else if(isMovable(changeX, changeY) && !visited[changeX][changeY][cur.z]){
                            queue.add(new position(changeX, changeY, cur.z));
                            visited[changeX][changeY][cur.z] = true;
                        }
                    }
                }
            }
        }
        // 탈출하지못했을 경우
        System.out.println(-1);
    }
    
    // 열쇠 얻기
    private static int getKey(int a, int b){
        return a | 1<<('f' - b);
    }
    
    // 문, 열쇠, 벽이 모두 아닌 공간
    private static boolean isMovable(int a, int b){
        if(map[a][b] == '.') return true;
        return false;
    }
    
    // 문인지 확인
    private static boolean isDoor(int a, int b){
        if('A' <= map[a][b] && map[a][b] <= 'F') return true;

        return false;
    }

    // 열쇠인지 확인
    private static boolean isKey(int a, int b){
        if('a' <= map[a][b] && map[a][b] <= 'f') return true;
        return false;
    }
    
    // 좌표가 벗어나지않았는지 체크
    private static boolean positionCheck(int a, int b){
        if(a < 0 || a >= n || b < 0 || b >= m) return false;
        return true;
    }

    // 열수있는 문인지 체크
    private static boolean openDoor(int a, char b){
        int k = (int)(1 << ('F' - b));

        return (a&k) == k;
    }
}
