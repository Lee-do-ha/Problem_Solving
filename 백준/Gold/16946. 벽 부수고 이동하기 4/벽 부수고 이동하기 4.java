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

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        queue = new LinkedList<>();
        visited = new boolean[N][M];
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
        addSet = new ArrayList<>();
        visited[x][y] = true;
        queue.add(new int[] {x,y});
        int region = 1;
        while(!queue.isEmpty()){
            int[] k = queue.poll();
            for(int i = 0 ; i< 4 ; i++){
                int changeX = k[0] + dx[i];
                int changeY = k[1] + dy[i];

                if(changeX >= 0 && changeX < N && changeY >= 0 && changeY < M){
                    if(map[changeX][changeY] == 0 && visited[changeX][changeY] == false){
                        queue.add(new int[] {changeX,changeY});
                        visited[changeX][changeY] = true;
                        region++;
                    }else if(map[changeX][changeY] != 0 && addvisited[changeX][changeY] == false){
                        addSet.add(new int[] {changeX,changeY});
                        addvisited[changeX][changeY] = true;
                    }
                }
            }
        }

        for(int[] a : addSet){
            map[a[0]][a[1]] += region;
            addvisited[a[0]][a[1]] = false;
        }
    }
}