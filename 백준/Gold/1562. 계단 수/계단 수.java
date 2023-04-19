import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 0~9를 모두 포함해야 하므로 최소 10자리는 되어야 함
        if(N < 10){
            System.out.println(0);
        }else{
            int[][][] dp = new int[10][N+1][1<<10];

            // 1자리의 수들은 모두 본인을 포함하고 본인으로 시작하는 수의 갯수 1개로 시작
            for(int i = 0 ; i < 10 ; i++){
                dp[i][1][1<<i] = 1;
            }

            // N번째 자리
            for(int j = 1 ; j < N ; j++){

                // 끝나는 수 i
                for(int i = 0 ; i < 10 ; i++){

                    // k는 가지고 있는 숫자
                    for(int k = 0 ; k < 1<<10 ; k++){

                        // 0이 아니라면 i번째로 시작하는 j자리 숫자가 있다는 뜻이므로 다음 숫자 만들기
                        if(dp[i][j][k] != 0){

                            // 0이면 1로 밖에 갈 수 없음
                            if(i == 0){
                                dp[i+1][j+1][k | 1<<(i+1)] = (dp[i+1][j+1][k | 1<<(i+1)] + dp[i][j][k]) % mod;
                            // 9이면 8로 밖에 갈 수 없음
                            }else if(i == 9){
                                dp[i-1][j+1][k | 1<<(i-1)] = (dp[i-1][j+1][k | 1<<(i-1)] + dp[i][j][k]) % mod;
                            // 0과 9가 아니라면 본인보다 1크거나, 1작은 수로 갈 수 있음
                            }else{
                                dp[i+1][j+1][k | 1<<(i+1)] = (dp[i+1][j+1][k | 1<<(i+1)] + dp[i][j][k]) % mod;
                                dp[i-1][j+1][k | 1<<(i-1)] = (dp[i-1][j+1][k | 1<<(i-1)] + dp[i][j][k]) % mod;
                            }
                        }
                    }

                }
            }

            // 수의 갯수에서 0으로 시작하는 수는 존재하지않으므로 1부터 9까지만 탐색
            int result = 0;
            for(int i = 1 ; i < 10 ; i++){
                result = (result + dp[i][N][(1<<10) - 1]) % mod;
            }

            System.out.println(result);

        }
    }
}
