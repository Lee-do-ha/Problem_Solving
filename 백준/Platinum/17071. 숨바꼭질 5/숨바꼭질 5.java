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
    static int[][] visitTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new LinkedList<>();

        visitTime = new int[2][500001];

        Arrays.fill(visitTime[0], -1);
        Arrays.fill(visitTime[1], -1);

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = 0;

        if(N == K){
            System.out.println(0);
        }else if(K > 500000){
            System.out.println(-1);
        }else{
            visitTime[0][N] = 0;
            queue.add(N);
            System.out.println(Search(N));
        }
    }

    private static int Search(int start){
        while(!queue.isEmpty()){
            time++;

            K += time;

            if(K > 500000){
                return -1;
            }

            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int a = queue.poll();

                if(a-1 >= 0 && visitTime[time%2][a-1] == -1){
                    queue.add(a-1);
                    visitTime[time%2][a-1] = time;
                }

                if(a+1 < 500001 && visitTime[time%2][a+1] == -1){
                    queue.add(a+1);
                    visitTime[time%2][a+1] = time;
                }

                if(a*2 < 500001 && visitTime[time%2][a*2] == -1){
                    queue.add(a*2);
                    visitTime[time%2][a*2] = time;
                }
            }
            if(visitTime[time%2][K] != -1){
                return time;
            }
        }
        return -1;
    }
}