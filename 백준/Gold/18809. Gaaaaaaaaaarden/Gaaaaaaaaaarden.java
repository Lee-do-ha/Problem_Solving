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

        // 배양할 수 있는 지역
        zone = new ArrayList<>();
        // 각 색깔마다 배양할 지역 번호 저장할 배열
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
        // 방문 배열 체크
        visited = new boolean[zone.size()];
        result = 0;
        // 각각 BFS 돌릴 큐
        redQ = new LinkedList<>();
        greenQ = new LinkedList<>();
        per1(0,0);
        System.out.println(result);
    }

    // RED 배양할 지역 고르기
    private static void per1(int k, int cnt){
        if(cnt == R){
            // GRREN 배양할 지역 고르기
            per2(0, 0, redNumbers);
            return;
        }

        // RED 배양할 지역 고를때만 방문체크하기 -> GREEN 배양할 지역 고를때 중복 방지하기 위해
        for(int i = k ; i < zone.size() ; i++){
            redNumbers[cnt] = i;
            visited[i] = true;
            per1(i+1, cnt+1);
            visited[i] = false;
        }
    }

    // GREEN 배양할 지역 고르기
    private static void per2(int k ,int cnt, int[] numbers){
        // 둘다 배양할 지역 골랐다면 시뮬레이션 진행
        if(cnt == G){
            simulation(numbers, greenNumbers);
            return ;
        }

        for(int i = k ; i < zone.size() ; i++){
            // 방문표시된 번호는 이미 RED에서 고른 번호
            if(!visited[i]){
                greenNumbers[cnt] = i;
                per2(i+1, cnt+1, numbers);
            }
        }

    }

    private static void simulation(int[] red, int[] green){
        // 원래 map 카피해서 시뮬레이션 진행
        Ground[][] copy = new Ground[N][M];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                copy[i][j] = new Ground(map[i][j].x, map[i][j].red, map[i][j].green);
            }
        }

        // RED 배양할 지역에 red값 추가해주기
        for(int i = 0 ; i < red.length ; i++){
            copy[zone.get(red[i])[0]][zone.get(red[i])[1]].red = 1;
            redQ.add(new int[] {zone.get(red[i])[0], zone.get(red[i])[1]});
        }

        // GREEN 배양할 지역에 green값 추가해주기
        for(int i = 0 ; i < green.length ; i++){
            copy[zone.get(green[i])[0]][zone.get(green[i])[1]].green = 1;
            greenQ.add(new int[] {zone.get(green[i])[0], zone.get(green[i])[1]});
        }

        // 둘 중하나 빌때까지 진행
        while(true){
            if(!redQ.isEmpty()){
                int redSize = redQ.size();

                for(int i = 0 ; i < redSize ; i++){
                    int[] cur = redQ.poll();

                    // 이미 완성되었으면 거기서는 진행할 수 없음
                    if(copy[cur[0]][cur[1]].red != 0 && copy[cur[0]][cur[1]].green != 0 && copy[cur[0]][cur[1]].red == copy[cur[0]][cur[1]].green){
                        continue;
                    }

                    for(int k = 0 ; k < 4 ; k++){
                        int changeX = cur[0] + dx[k];
                        int changeY = cur[1] + dy[k];

                        // 범위 체크
                        if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                        // 아직 아무것도 배양되지않았다면 red 먼저 배양 시작
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

                        // 범위 체크
                        if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                        if(copy[changeX][changeY].x != 0){
                            // 아직 아무것도 배양되지않았다면 green 배양
                            if(copy[changeX][changeY].red == 0 && copy[changeX][changeY].green == 0){
                                copy[changeX][changeY].green = copy[cur[0]][cur[1]].green+1;
                                greenQ.add(new int[] {changeX, changeY});
                                // green배양되는 시간과 이미 배양된 red의 시간이 같다면 green값 넣고 해당 점은 BFS진행 못하므로 큐에 추가 안해줌
                            }else if(copy[changeX][changeY].red == copy[cur[0]][cur[1]].green+1 && copy[changeX][changeY].green == 0){
                                copy[changeX][changeY].green = copy[cur[0]][cur[1]].green+1;
                            }
                        }
                    }
                }
            }
            // 둘 중 하나라도 비면 더이상 꽃이 필 수 없으므로 종료
            if(redQ.isEmpty() || greenQ.isEmpty()){
                break;
            }
        }

        // 두 큐 모두 초기화
        redQ.clear();
        greenQ.clear();
        int NUM = 0;

        // red와 green의 시간이 같다면 그 지역은 꽃이 핀 지역이므로 꽃 갯수 추가
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
               if(copy[i][j].x != 0){
                   if(copy[i][j].red != 0 && copy[i][j].green != 0 && copy[i][j].red == copy[i][j].green){
                       NUM++;
                   }
               }
            }
        }

        // 최대 꽃의 갯수를 찾으므로 계속 최대값으로 갱신
        result = Math.max(result, NUM);
    }
}
