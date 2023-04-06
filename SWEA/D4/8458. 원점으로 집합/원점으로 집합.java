import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static class position{
        int x, y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());


        for(int test = 1 ; test <= T ; test++){
            int N = Integer.parseInt(br.readLine());
            int result = 0;
            ArrayList<position> List = new ArrayList<>();

            for(int i = 0 ; i < N ; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                List.add(new position(a, b));
            }

            // 모두 0인지 체크
            boolean flag = false;

            for(position o : List){
                if(o.x != 0 || o.y != 0){
                    flag = true;
                    break;
                }
            }

            // 모든 수가 0이 아닐때
            if(flag){
                // 방문 못하는 경우
                int sum = 0;
                for(position o : List){
                    sum += (Math.abs(o.x)+Math.abs(o.y)) %2;
                }

                // 모두 홀수가 아니거나, 모두 짝수가 아닌 경우 방문 불가
                if(sum != 0 && sum != List.size()) {
                    result = -1;
                    // 하나로 통일된 경우
                }else {
                    // 방문 가능하므로 result값 최소값으로하고 시작
                    result = Integer.MIN_VALUE;
                    int maxLength = 0;
                    // 각 좌표별 총 움직여야하는길이 moveList에 저장
                    for(position o : List){
                        maxLength = Math.max(Math.abs(o.x) + Math.abs(o.y), maxLength);
                    }


                    // 모두 짝수
                    if(sum == 0){
                        int K = 0;

                        // 총 움직여야 하는 길이중 가장 큰수보다 sum 길이가 같거나 같을떄까지 K초까지 이동
                        while(sum < maxLength){
                            K++;
                            sum += K;
                        }

                        // K초에 홀수만 이동가능하여 2초 뒤에 가능
                        if(K%4 == 1){
                            result = K+2;
                            // K초에 홀수만 이동가능하여 1초 뒤에 가능
                        }else if(K%4 == 2){
                            result = K+1;
                            // K초에 짝수가 가능한 경우
                        }else{
                            result = K;
                        }
                        
                        // 모두 홀수
                    }else{
                        int K = 0;
                        sum = 0;
                        while(sum < maxLength){
                            K++;
                            sum += K;
                        }

                        // K초에 짝수만 이동가능하여 1초 뒤에 가능
                        if(K%4 == 0){
                            result = K+1;
                            // K초에 짝수만 이동가능하여 2초 뒤에 가능
                        }else if(K%4 == 3){
                            result = K+2;
                            // K초에 홀수가 가능한 경우
                        }else {
                            result = K;
                        }
                    }
                }
            }

            sb.append("#").append(test).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

}
