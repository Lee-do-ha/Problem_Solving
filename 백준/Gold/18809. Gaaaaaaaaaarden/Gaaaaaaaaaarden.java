import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] redNumbers, greenNumbers;
    static int N, M, R, G, result;
    static boolean[] visited;
    static ArrayList<int[]> zone;
    static Ground[][] map;
    static class Ground{
        int x, red, green;
        public Ground(int x, int red, int green) {
            this.x = x;
            this.red = red;
            this.green = green;
        }

        @Override
        public String toString() {
            return "Ground{" +
                    "x=" + x +
                    ", red=" + red +
                    ", green=" + green +
                    '}';
        }
    }

    static Queue<int[]> redQ;
    static Queue<int[]> greenQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        zone = new ArrayList<>();
        redNumbers = new int[R];
        greenNumbers = new int[G];

        map = new Ground[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = new Ground(Integer.parseInt(st.nextToken()), 0, 0);
                if(map[i][j].x == 2){
                    zone.add(new int[] {i,j});
                }
            }
        }
        visited = new boolean[zone.size()];
        result = 0;
        redQ = new LinkedList<>();
        greenQ = new LinkedList<>();
        per1(0,0);
        System.out.println(result);
    }

    private static void per1(int k, int cnt){
        if(cnt == R){
            per2(0, 0, redNumbers);
            return;
        }

        for(int i = k ; i < zone.size() ; i++){
            redNumbers[cnt] = i;
            visited[i] = true;
            per1(i+1, cnt+1);
            visited[i] = false;
        }
    }

    private static void per2(int k ,int cnt, int[] numbers){
        if(cnt == G){
            simulation(numbers, greenNumbers);
//            System.out.println(Arrays.toString(numbers));
//            System.out.println(Arrays.toString(greenNumbers));
            return ;
        }

        for(int i = k ; i < zone.size() ; i++){
            if(!visited[i]){
                greenNumbers[cnt] = i;
                per2(i+1, cnt+1, numbers);
            }
        }

    }

    private static void simulation(int[] red, int[] green){
        Ground[][] copy = new Ground[N][M];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                copy[i][j] = new Ground(map[i][j].x, map[i][j].red, map[i][j].green);
            }
        }

        for(int i = 0 ; i < red.length ; i++){
            copy[zone.get(red[i])[0]][zone.get(red[i])[1]].red = 1;
            redQ.add(new int[] {zone.get(red[i])[0], zone.get(red[i])[1]});
        }

        for(int i = 0 ; i < green.length ; i++){
            copy[zone.get(green[i])[0]][zone.get(green[i])[1]].green = 1;
            greenQ.add(new int[] {zone.get(green[i])[0], zone.get(green[i])[1]});
        }

        while(true){
            if(!redQ.isEmpty()){
                int redSize = redQ.size();

                for(int i = 0 ; i < redSize ; i++){
                    int[] cur = redQ.poll();

                    if(copy[cur[0]][cur[1]].red != 0 && copy[cur[0]][cur[1]].green != 0 && copy[cur[0]][cur[1]].red == copy[cur[0]][cur[1]].green){
                        continue;
                    }

                    for(int k = 0 ; k < 4 ; k++){
                        int changeX = cur[0] + dx[k];
                        int changeY = cur[1] + dy[k];

                        if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                        if(copy[changeX][changeY].x != 0 && copy[changeX][changeY].green == 0 && copy[changeX][changeY].red == 0){
                            copy[changeX][changeY].red = copy[cur[0]][cur[1]].red+1;
                            redQ.add(new int[] {changeX, changeY});
                        }
                    }
                }
            }

            if(!greenQ.isEmpty()){
                int greenSize = greenQ.size();

                for(int i = 0 ; i < greenSize ; i++){
                    int[] cur = greenQ.poll();

                    for(int k = 0 ; k < 4 ; k++){
                        int changeX = cur[0] + dx[k];
                        int changeY = cur[1] + dy[k];

                        if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                        if(copy[changeX][changeY].x != 0){
                            if(copy[changeX][changeY].red == 0 && copy[changeX][changeY].green == 0){
                                copy[changeX][changeY].green = copy[cur[0]][cur[1]].green+1;
                                greenQ.add(new int[] {changeX, changeY});
                            }else if(copy[changeX][changeY].red == copy[cur[0]][cur[1]].green+1 && copy[changeX][changeY].green == 0){
                                copy[changeX][changeY].green = copy[cur[0]][cur[1]].green+1;
                            }
                        }
                    }
                }
            }
            if(redQ.isEmpty() || greenQ.isEmpty()){
                break;
            }
        }

        redQ.clear();
        greenQ.clear();
        int NUM = 0;

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
               if(copy[i][j].x != 0){
                   if(copy[i][j].red != 0 && copy[i][j].green != 0 && copy[i][j].red == copy[i][j].green){
                       NUM++;
                   }
               }
            }
        }

        result = Math.max(result, NUM);
    }
}