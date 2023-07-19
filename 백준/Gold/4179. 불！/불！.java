import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static Queue<int[]> fire, jihun;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        fire = new LinkedList<>();
        jihun = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J'){
                    jihun.add(new int[] {i, j});
                }else if(map[i][j] == 'F'){
                    fire.add(new int[] {i, j});
                }
            }
        }

        int time = 0;
        boolean arrived = false;
        while(!jihun.isEmpty() && !arrived){
            int size = jihun.size();
            int size2 = fire.size();
            time++;

            for(int i = 0 ; i < size2 ; i++){
                int[] cur = fire.poll();

                for(int j = 0 ; j < 4 ; j++){
                    int changeX = cur[0] + dx[j];
                    int changeY = cur[1] + dy[j];

                    if(isPossible(changeX, changeY)){
                        if(map[changeX][changeY] == 'J' || map[changeX][changeY] == '.'){
                            map[changeX][changeY] = 'F';
                            fire.add(new int[] {changeX, changeY});
                        }
                    }
                }

            }

            for(int i = 0 ; i < size ; i++){
                int[] cur = jihun.poll();

                if(cur[0] == 0 || cur[0] == N-1 || cur[1] == 0 || cur[1] == M-1){
                    arrived = true;
                    break;
                }

                for(int j = 0 ; j < 4 ; j++){
                    int changeX = cur[0] + dx[j];
                    int changeY = cur[1] + dy[j];

                    if(isPossible(changeX, changeY)){
                        if(map[changeX][changeY] == '.'){
                            map[changeX][changeY] = 'J';
                            jihun.add(new int[] {changeX, changeY});
                        }
                    }
                }

            }
        }

        if(arrived){
            System.out.println(time);
        }else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean isPossible(int a, int b){
        if(a < 0 || a >= N || b < 0 || b >= M){
            return false;
        }

        return true;
    }
}