import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            int k = Integer.parseInt(st.nextToken());
            queue.add(k);
            visited[k] = true;
        }

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int cur = queue.poll();

                for(int k = 1 ; k < N ; ){
                    if((cur ^ k) <= N && !visited[cur ^ k]){
                        queue.offer(cur ^ k);
                        visited[cur ^ k ] = true;
                    }
                    k = k<<1;
                }
            }
            ans++;
        }
        System.out.println(ans-1);
    }
}