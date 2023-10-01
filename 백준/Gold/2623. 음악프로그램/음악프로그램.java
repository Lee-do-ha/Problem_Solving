import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder stringBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N+1];
        ArrayList<Integer>[] lists = new ArrayList[N+1];
        for(int i = 1 ; i < N+1 ; i++){
            lists[i] = new ArrayList<>();
        }
        int pre = 0;

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < k ; j++){
                int x = Integer.parseInt(st.nextToken());

                if(j != 0){
                    lists[pre].add(x);
                    in[x]++;
                }
                pre = x;
            }

        }

        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        for(int i = 1 ; i < N+1 ; i++){
            if(in[i] == 0){
                visited[i] = true;
                priorityQueue.add(i);
            }
        }

        while (!priorityQueue.isEmpty()){
            int cur = priorityQueue.poll();
            ans.add(cur);

            for(int k : lists[cur]){
                in[k]--;
                if(!visited[k] && in[k] == 0){
                    priorityQueue.add(k);
                }
            }
        }

        if(ans.size() == N){
            for(int o : ans){
                System.out.println(o);
            }
        }else{
            System.out.println(0);
        }

    }
}
