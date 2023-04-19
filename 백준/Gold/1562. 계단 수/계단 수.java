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

        if(N < 10){
            System.out.println(0);
        }else{
            int[][][] dp = new int[10][N+1][1<<10];

            for(int i = 0 ; i < 10 ; i++){
                dp[i][1][1<<i] = 1;
            }

            // N번째 자리
            for(int j = 1 ; j < N ; j++){

                // 끝나는 수 i
                for(int i = 0 ; i < 10 ; i++){

                    // k는 가지고 있는 숫자
                    for(int k = 0 ; k < 1<<10 ; k++){

                        if(dp[i][j][k] != 0){

                            if(i == 0){
                                dp[i+1][j+1][k | 1<<(i+1)] = (dp[i+1][j+1][k | 1<<(i+1)] + dp[i][j][k]) % mod;
                            }else if(i == 9){
                                dp[i-1][j+1][k | 1<<(i-1)] = (dp[i-1][j+1][k | 1<<(i-1)] + dp[i][j][k]) % mod;
                            }else{
                                dp[i+1][j+1][k | 1<<(i+1)] = (dp[i+1][j+1][k | 1<<(i+1)] + dp[i][j][k]) % mod;
                                dp[i-1][j+1][k | 1<<(i-1)] = (dp[i-1][j+1][k | 1<<(i-1)] + dp[i][j][k]) % mod;
                            }
                        }
                    }

                }
            }

//            for(int i = 0 ; i < 10 ; i++){
//                System.out.println(dp[i][N][1023]);
//            }

            int result = 0;
            for(int i = 1 ; i < 10 ; i++){
                result = (result + dp[i][N][(1<<10) - 1]) % mod;
            }

            System.out.println(result);

        }
    }
}