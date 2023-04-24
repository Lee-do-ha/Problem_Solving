import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class puel{
        int left, up, right;
    }

    static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        puel[][] minDistance = new puel[N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                minDistance[i][j] = new puel();
                minDistance[i][j].left = INF;
                minDistance[i][j].up = INF;
                minDistance[i][j].right = INF;
                if(i == 1){
                    if(j-1 >= 0){
                        minDistance[i][j].left = map[i-1][j-1] + map[i][j];
                    }

                    minDistance[i][j].up = map[i-1][j] + map[i][j];

                    if(j+1 < M){
                        minDistance[i][j].right = map[i-1][j+1] + map[i][j];
                    }
                }else{
                    if(j-1 >= 0){
                        minDistance[i][j].left = Math.min(minDistance[i-1][j-1].up, minDistance[i-1][j-1].right) + map[i][j];
                    }

                    minDistance[i][j].up = Math.min(minDistance[i-1][j].left, minDistance[i-1][j].right) + map[i][j];

                    if(j+1 < M){
                        minDistance[i][j].right = Math.min(minDistance[i-1][j+1].left, minDistance[i-1][j+1].up) + map[i][j];
                    }
                }
            }
        }
        int min = INF;
        for(int i = 0 ; i < M ; i++){
            if(minDistance[N-1][i].right < min){
                min = minDistance[N-1][i].right;
            }
            if(minDistance[N-1][i].left < min){
                min = minDistance[N-1][i].left;
            }
            if(minDistance[N-1][i].up < min){
                min = minDistance[N-1][i].up;
            }
        }
        System.out.println(min);
    }
}