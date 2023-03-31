import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] map;
    static int n, m, s;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class position{
        int x, y, z;

        public position(int x, int y, int z) {
            super();
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }
    static int[] numbers;
    static Queue<position> queue;
    static Map<Integer, int[]> keymap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][64];
        queue = new LinkedList<>();
        numbers = new int[6];
        s = 0;
        keymap = new HashMap<>();

        for(int i = 0 ; i < n ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < m ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0'){
                    queue.add(new position(i,j,0));
                    visited[i][j][0] = true;
                    map[i][j] = '.';
                }
            }
        }
        per(0);
//        System.out.println("size = " + queue.size());
        int time = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            time++;
            for(int i = 0 ; i < size ; i++){

                position cur = queue.poll();
//                System.out.println(cur.x + " " + cur.y + " " + cur.z);

                for(int k = 0 ; k < 4 ; k++){
                    int changeX = cur.x + dx[k];
                    int changeY = cur.y + dy[k];

                    if(checked(changeX, changeY)){
                        if(map[changeX][changeY] == '1'){
                            System.out.println(time);
                            return;
                        }
                        if(map[changeX][changeY] == '.' && !visited[changeX][changeY][cur.z]){
                            queue.add(new position(changeX, changeY, cur.z));
                            visited[changeX][changeY][cur.z] = true;
                        }else if(map[changeX][changeY] == 'a' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[0] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[0] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'b' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[1] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[1] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'c' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[2] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[2] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'd' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[3] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[3] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'e' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[4] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[4] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'f' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[5] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                            }else{
                                int[] arr = keymap.get(cur.z).clone();
                                arr[5] = 1;
                                int changeZ = findSet(arr);
                                if(!visited[changeX][changeY][changeZ]){
                                    queue.add(new position(changeX, changeY, changeZ));
                                    visited[changeX][changeY][changeZ] = true;
                                }
                            }
                        }else if(map[changeX][changeY] == 'A' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[0] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }else if(map[changeX][changeY] == 'B' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[1] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }else if(map[changeX][changeY] == 'C' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[2] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }else if(map[changeX][changeY] == 'D' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[3] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }else if(map[changeX][changeY] == 'E' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[4] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }else if(map[changeX][changeY] == 'F' && !visited[changeX][changeY][cur.z]){
                            visited[changeX][changeY][cur.z] = true;
                            if(keymap.get(cur.z)[5] == 1) {
                                queue.add(new position(changeX, changeY, cur.z));
                                visited[changeX][changeY][cur.z] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static void per(int cnt) {
        if(cnt == 6) {
            keymap.put(s, numbers.clone());
//            System.out.println(s + " == " + Arrays.toString(keymap.get(s)));
            s++;
            return;
        }

        numbers[cnt] = 0;
        per(cnt+1);
        numbers[cnt] = 1;
        per(cnt+1);
    }

    private static boolean checked(int a, int b) {
        if(a < 0 || a >= n || b < 0 || b >= m) return false;

        return true;
    }

    private static int findSet(int [] a) {


        for(int i = 0 ; i < keymap.size() ; i++) {
            int[] k = keymap.get(i);

            int n = 0;
            for(int z = 0 ; z < 6 ; z++) {
                if(k[z] != a[z]) {
                    break;
                }
                n++;
            }
            if(n == 6) {
                return i;
            }

        }
        return -1;
    }
}