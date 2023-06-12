import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

       int N = Integer.parseInt(st.nextToken());

       ArrayList<Integer>[] list = new ArrayList[N+1];
       int[] inDegree = new int[N+1];
       int[] build = new int[N+1];

       for(int i = 0 ; i < N ; i++){
           list[i+1] = new ArrayList<>();
       }

       for(int i = 0 ; i < N ; i++){

           st = new StringTokenizer(br.readLine());

           int time = Integer.parseInt(st.nextToken());

           build[i+1] = time;

           while(true){
               int k = Integer.parseInt(st.nextToken());
               if(k == -1){
                   break;
               }

               inDegree[i+1]++;
               list[k].add(i+1);

           }

       }

       Queue<Integer> queue = new LinkedList<>();

       for(int i = 0 ; i < N ; i++){
           if(inDegree[i+1] == 0){
               queue.add(i+1);
           }
       }

       int[] result = new int[N+1];

       while(!queue.isEmpty()){
           int cur = queue.poll();

           for(int next : list[cur]){
               inDegree[next]--;

               result[next] = Math.max(result[next], result[cur] + build[cur]);

               if(inDegree[next] == 0){
                   queue.add(next);
               }
           }
       }

       for(int i = 0 ; i < N ; i++){
           System.out.println(result[i+1] + build[i+1]);
       }
    }
}