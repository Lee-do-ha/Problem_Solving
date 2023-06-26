import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[][] distance = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(i == j) continue;
                distance[i][j] = 40000000;
            }
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a-1][b-1] = Math.min(distance[a-1][b-1], c);
        }

        for(int k = 0 ; k < N ; k++){
            for(int i = 0 ; i < N ; i++){
                if(k == i) continue;
                for(int j = 0 ; j < N ; j++){
                    if(i == j || k == j) continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(distance[i][j] == 40000000){
                    distance[i][j] = 0;
                }
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }

    }
}