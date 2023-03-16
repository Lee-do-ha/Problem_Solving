import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Queue<Integer> queue;
    static int time, K;
    // 방문한 시간과 방문 시간의 짝수, 홀수 판별을 위한 2차원 배열
    static int[][] visitTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new LinkedList<>();

        visitTime = new int[2][500001];

        // -1이 아니면 이미 방문한 점
        Arrays.fill(visitTime[0], -1);
        Arrays.fill(visitTime[1], -1);

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        time = 0;
        
        // 만약 두 수가 같다면 이미 만났으므로 0
        if(N == K){
            System.out.println(0);
        // 동생이 범위를 나갔을 경우 -1
        }else if(K > 500000){
            System.out.println(-1);
        // 두 경우 모두 아니라면 현재 수빈이의 위치에 시간은 0이므로 visitTime[0 = 짝수시간][현재 수빈이의 위치]의 값을 0으로 저장하고 BFS 탐색
        }else{
            visitTime[0][N] = 0;
            queue.add(N);
            System.out.println(Search(N));
        }
    }

    private static int Search(int start){
        // 큐가 빌때까지 진행
        while(!queue.isEmpty()){
            // 시간 진행
            time++;

            // 해당 시간의 동생의 위치
            K += time;

            // 동생이 범위를 나갈때까지 찾지못했으므로 -1
            if(K > 500000){
                return -1;
            }
            
            // 각 시간별 갈 수 있는 위치 저장
            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int a = queue.poll();
                
                // 방문 가능한 지점이고 방문 기록이 없다면 해당 시간의 홀,짝에 맞는 위치에 시간 저장
                if(a-1 >= 0 && visitTime[time%2][a-1] == -1){
                    queue.add(a-1);
                    visitTime[time%2][a-1] = time;
                }

                // 방문 가능한 지점이고 방문 기록이 없다면 해당 시간의 홀,짝에 맞는 위치에 시간 저장
                if(a+1 < 500001 && visitTime[time%2][a+1] == -1){
                    queue.add(a+1);
                    visitTime[time%2][a+1] = time;
                }

                // 방문 가능한 지점이고 방문 기록이 없다면 해당 시간의 홀,짝에 맞는 위치에 시간 저장
                if(a*2 < 500001 && visitTime[time%2][a*2] == -1){
                    queue.add(a*2);
                    visitTime[time%2][a*2] = time;
                }
            }
            // 만약 현재 수빈이의 위치에 맞는 홀,짝 시간에 방문 기록이 있다면 2초마다 다시 방문할 수 있으므로 현재 시간 출력
            if(visitTime[time%2][K] != -1){
                return time;
            }
        }
        // 큐가 빌때까지 방문하지못했으므로 -1
        return -1;
    }
}
