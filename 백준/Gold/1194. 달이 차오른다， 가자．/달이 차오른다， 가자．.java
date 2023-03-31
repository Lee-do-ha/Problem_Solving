import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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
        // 시작점 잡고 .으로 바꿔주기
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    queue.add(new position(i, j, 0));
                    visited[i][j][0] = true;
                    map[i][j] = '.';
                }
            }
        }

        int time = 0;
        while(!queue.isEmpty()){
            time++;
            int size = queue.size();

            for(int t = 0 ; t < size ; t++){
                position cur = queue.poll();

//                System.out.println(cur.x + " " + cur.y + " " + cur.z);

                for(int i = 0 ; i < 4 ; i++){
                    int changeX = cur.x + dx[i], changeY = cur.y + dy[i];

                    if(positionCheck(changeX, changeY)){
                        if(map[changeX][changeY] == '1'){
                            System.out.println(time);
                            return;
                        }
                        if(isDoor(changeX, changeY) && !visited[changeX][changeY][cur.z] && openDoor(cur.z, map[changeX][changeY])){
                            visited[changeX][changeY][cur.z] = true;
                            queue.add(new position(changeX, changeY, cur.z));
                        }else if(isKey(changeX, changeY) && !visited[changeX][changeY][cur.z]){
                            if(!visited[changeX][changeY][getKey(cur.z, map[changeX][changeY])]){
                                queue.add(new position(changeX, changeY, getKey(cur.z, map[changeX][changeY])));
                                visited[changeX][changeY][getKey(cur.z, map[changeX][changeY])] = true;
                            }
                            visited[changeX][changeY][cur.z] = true;
                        }else if(isMovable(changeX, changeY) && !visited[changeX][changeY][cur.z]){
                            queue.add(new position(changeX, changeY, cur.z));
                            visited[changeX][changeY][cur.z] = true;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
    private static int getKey(int a, int b){
        return a | 1<<('f' - b);
    }
    private static boolean isMovable(int a, int b){
        if(map[a][b] == '.') return true;
        return false;
    }
    private static boolean isDoor(int a, int b){
        if('A' <= map[a][b] && map[a][b] <= 'F') return true;

        return false;
    }

    private static boolean isKey(int a, int b){
        if('a' <= map[a][b] && map[a][b] <= 'f') return true;
        return false;
    }

    private static boolean positionCheck(int a, int b){
        if(a < 0 || a >= n || b < 0 || b >= m) return false;
        return true;
    }

    private static boolean openDoor(int a, char b){
        int k = (int)(1 << ('F' - b));

        return (a&k) == k;
    }
}