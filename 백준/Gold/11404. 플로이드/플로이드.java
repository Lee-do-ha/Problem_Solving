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

        // 최소 비용 저장할 2차원 배열
        int[][] distance = new int[N][N];

        // 자신을 향하는 경로 빼고는 모두 40000000으로 저장
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

            // 똑같은 경로가 더 큰값으로 들어올 수 있으므로 값비교해서 더 작은값으로 저장
            distance[a-1][b-1] = Math.min(distance[a-1][b-1], c);
        }

        // 플로이드 진행
        // 경유지
        for(int k = 0 ; k < N ; k++){
            // 출발지
            for(int i = 0 ; i < N ; i++){
                if(k == i) continue;
                // 목적지
                for(int j = 0 ; j < N ; j++){
                    if(i == j || k == j) continue;
                    
                    // 최소 비용은 거쳐가는 비용과 현재 비용중 더 작은값으로 저장
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        // 갱신되지않은 경로면 0으로 저장하고 배열 출력
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
