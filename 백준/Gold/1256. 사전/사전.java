import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 전체 조합 테이블
        int[][] combination = new int[202][202];

        for(int i = 0 ; i <= 200 ; i++){
            for(int j = 0 ; j <= i ; j++){
                if(j == 0 || j == i){
                    combination[i][j] = 1;
                }else{
                    combination[i][j] = combination[i-1][j] + combination[i-1][j-1];
                    if(combination[i][j] > 1000000000) combination[i][j] = 1000000001;
                }
            }
        }

        // 주어진 자릿수로 만들 수 없는 K값이라면 -1 출력
        if(combination[N+M][M] < K){
            System.out.println(-1);
            // 주어진 자릿수로 만들 수 있는 경우
        }else{
            while(!(N == 0 && M == 0)){
                // a를 선택했을 경우 남은 문자로 만들 수 있는 경우의 수가 K보다 크다면 a
                if(combination[N-1+M][M] >= K){
                    sb.append("a");
                    N--;
                // 모든 경우의 수가 K보다 작으면 z
                }else{
                    sb.append("z");
                    K -= combination[N-1+M][M];
                    M--;
                }
            }
            System.out.println(sb);
        }
    }
}