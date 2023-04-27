import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr, dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[M+1][2];
        dp = new int [M+1][M+1];

        for(int i = 1 ; i < M+1 ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = findDistance(1, 0, 0);

        sb.append(result).append("\n");

        int index1 = 0;
        int index2 = 0;
        for(int i = 1 ; i < M+1 ; i++){
            int distance = getDistance(1, index1, i);

            if(dp[index1][index2] - distance == dp[i][index2]){
                index1 = i;
                sb.append("1").append("\n");
            }else{
                index2 = i;
                sb.append("2").append("\n");
            }
        }


        System.out.println(sb);
    }

    private static int findDistance(int num, int one, int two){
        if(num > M){
            return 0;
        }

        if(dp[one][two] != 0){
            return dp[one][two];
        }

        int oneDistance = findDistance(num+1, num, two) + getDistance(1, one, num);
        int twoDistance = findDistance(num+1, one, num) + getDistance(2, two, num);

        dp[one][two] = Math.min(oneDistance, twoDistance);

        return dp[one][two];
    }

    private static int getDistance(int police, int start, int end){
        int startX = arr[start][0], startY = arr[start][1];

        if(start == 0){
            if(police == 1){
                startX = 1;
                startY = 1;
            }else if(police == 2){
                startX = N;
                startY = N;
            }
        }

        return Math.abs(startX - arr[end][0]) + Math.abs(startY - arr[end][1]);
    }
}