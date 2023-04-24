import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[101];
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> hash = new HashMap<>();

        for(int i = 0 ; i < N+M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            hash.put(a, b);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0 ; i < size ; i++){
                int cur = queue.poll();

                if(cur == 100){
                    System.out.println(time);
                    return;
                }

                for(int k = 1 ; k < 7 ; k++){
                    if(cur+k < 101 && !visited[cur+k]){
                        if(hash.containsKey(cur+k)){
                            visited[cur+k] = true;
                            queue.add(hash.get(cur+k));
                            visited[hash.get(cur+k)] = true;
                        }else{
                            queue.add(cur+k);
                            visited[cur+k] = true;
                        }
                    }
                }
            }
            time++;
        }
    }
}