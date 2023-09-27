import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] money;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T ; i++){
            int N = Integer.parseInt(br.readLine());

            money = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int k = 0 ; k < N ; k++){
                money[k] = Integer.parseInt(st.nextToken());
            }

            int price = Integer.parseInt(br.readLine());

            dp = new int[price+1];

            for(int x = 0 ; x < N ; x++){
                for(int y = 1 ; y < price+1 ; y++){

                    if(y - money[x] > 0){
                        dp[y] = dp[y] + dp[y-money[x]];
                    }else if(y - money[x] == 0){
                        dp[y]++;
                    }

                }
            }
            stringBuilder.append(dp[price]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}