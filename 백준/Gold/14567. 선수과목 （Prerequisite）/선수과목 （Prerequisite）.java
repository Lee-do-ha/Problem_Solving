import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 선수 과목 남은 갯수
        int[] count = new int[N+1];

        // 후에 수강하는 과목 리스트
        ArrayList<Integer>[] list = new ArrayList[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            list[i] = new ArrayList<>();
        }

        // 수강했는지 여부
        boolean[] computed = new boolean[N+1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            list[before].add(after);
            count[after]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1 ; i < N+1 ; i++){
            if(count[i] == 0){
                computed[i] = true;
                queue.add(i);
            }
        }

        int[] ans = new int[N+1];

        int time = 1;
        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int cur = queue.poll();

                for(int j : list[cur]){
                    count[j]--;
                    if(count[j] == 0 && !computed[j]){
                        queue.add(j);
                    }
                }

                ans[cur] = time;
                computed[cur] = true;
            }

            time++;

        }

        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 1 ; i < N+1 ; i++){
            stringBuffer.append(ans[i] + " ");
        }
        System.out.println(stringBuffer);
    }
}