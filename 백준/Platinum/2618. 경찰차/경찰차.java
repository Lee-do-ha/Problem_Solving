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

        // 각 사건별 좌표 저장
        arr = new int[M+1][2];
        
        // 해당 좌표에서 최종 사건까지 최단 시간으로 움직이는데 걸리는 시간 저장
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
        // 처음부터 최솟값 찾으면서 지나온 경찰차 출력
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

    // num = 사건번호, one = 현재 경찰차1의 위치, two = 현재 경찰차 2의 위치
    private static int findDistance(int num, int one, int two){
        // 사건이 끝났으면 return
        if(num > M){
            return 0;
        }

        // 이미 값 구해져있다면 return
        if(dp[one][two] != 0){
            return dp[one][two];
        }

        // 현재에서 가장 최단시간을 구하는 방법은 다음 사건을 경찰차1이 가는 것과 경찰차2가 가는 것 중의 최솟값 찾기
        // 다음 사건을 1번이 갔다고 치면 1번 경찰차의 위치는 다음 사건의 위치로 갱신하고 거리값 더해주기
        int oneDistance = findDistance(num+1, num, two) + getDistance(1, one, num);
        int twoDistance = findDistance(num+1, one, num) + getDistance(2, two, num);

        // 현재 위치에서의 최솟값은 경찰차 둘 중 하나가 간 거리중 최솟값
        dp[one][two] = Math.min(oneDistance, twoDistance);

        return dp[one][two];
    }

    // 해당 좌표 까지의 이동하는데 걸리는 거리
    private static int getDistance(int police, int start, int end){
        // 시작점은 받은 위치의 좌표
        int startX = arr[start][0], startY = arr[start][1];

        // 시작이 0인경우에는 아직 출발 안함
        if(start == 0){
            // 1번 경찰차는 (1,1)에서 출발
            if(police == 1){
                startX = 1;
                startY = 1;
            // 2번 경찰차는 (N, N)에서 출발
            }else if(police == 2){
                startX = N;
                startY = N;
            }
        }

        // 맨해튼거리로 거리값 return
        return Math.abs(startX - arr[end][0]) + Math.abs(startY - arr[end][1]);
    }
}
