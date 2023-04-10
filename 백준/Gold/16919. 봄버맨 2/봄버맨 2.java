import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        if(K == 1){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }else if(K%2 == 0){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    System.out.print("O");
                }
                System.out.println();
            }
        }else{
            int num = 0;
            if(K%4 == 1){
                num = 2;
            }else if(K%4 == 3){
                num = 1;
            }

            for(int k = 1 ; k <= num ; k++){
                ArrayList<int[]> List = new ArrayList<>();
                for(int i = 0 ; i < N ; i++){
                    for(int j = 0 ; j < M ; j++){
                        if(map[i][j] == 'O'){
                            List.add(new int[] {i, j});
                        }
                        map[i][j] = 'O';
                    }
                }

                if(!List.isEmpty()){
                    for(int[] o : List){
                        for(int z = 0 ; z < 5; z++){
                            int changeX = o[0] + dx[z];
                            int changeY = o[1] + dy[z];

                            if(changeX < 0 || changeX >= N || changeY < 0 || changeY >= M) continue;

                            map[changeX][changeY] = '.';
                        }
                    }
                }
            }
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < M ; j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}