import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map ;
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<int[]> queue;
    static ArrayList<int[]> addSet;
    static boolean[][] visited;
    static boolean[][] addvisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 맵 입력받기
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // BFS진행할 큐
        queue = new LinkedList<>();
        
        // 전체 맵 BFS 돌릴 때 방문처리할 배열
        visited = new boolean[N][M];
        
        // 0이 아닌 지점 방문처리할 배열
        addvisited = new boolean[N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 0 && visited[i][j] == false){
                    check(i,j);
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                sb.append(map[i][j]%10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void check(int x, int y){
        // 더해줘야할 좌표 저장할 리스트
        addSet = new ArrayList<>();
        // 현재 좌표 방문처리
        visited[x][y] = true;
        queue.add(new int[] {x,y});
        // 0의 개수 
        int region = 1;
        
        // 4방 탐색 BFS
        while(!queue.isEmpty()){
            int[] k = queue.poll();
            for(int i = 0 ; i< 4 ; i++){
                int changeX = k[0] + dx[i];
                int changeY = k[1] + dy[i];

                // 좌표가 범위를 벗어났는지 체크
                if(changeX >= 0 && changeX < N && changeY >= 0 && changeY < M){
                    
                    // 좌표값이 0이고 아직 방문하지 않은 지점이라면 큐에 넣고 방문처리
                    if(map[changeX][changeY] == 0 && visited[changeX][changeY] == false){
                        queue.add(new int[] {changeX,changeY});
                        visited[changeX][changeY] = true;
                        region++;
                    // 좌표값이 0이 아니고 이번 BFS에서 방문했는지 체크
                    }else if(map[changeX][changeY] != 0 && addvisited[changeX][changeY] == false){
                        addSet.add(new int[] {changeX,changeY});
                        addvisited[changeX][changeY] = true;
                    }
                }
            }
        }

        // 현재 값 추가해줘야 할 좌표들 값 추가해주고 방문하지않은것으로 체크
        // 다음 BFS진행할때 또 더해줘야할수도 있기때문에 false
        // 매번 배열을 만드는 경우에 시간초과가 나서 해당 BFS가 끝났을때 다시 원상복귀
        for(int[] a : addSet){
            map[a[0]][a[1]] += region;
            addvisited[a[0]][a[1]] = false;
        }
    }
}
