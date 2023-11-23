import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            if(visited[i]) continue;
            
            queue.add(i);
            
            answer++;
            
            while(!queue.isEmpty()){
                
                int cur = queue.poll();
                
                for(int k = 0 ; k < n ; k++){
                    if(cur == k) continue;
                    
                    if(computers[cur][k] == 1 && !visited[k]){
                        queue.add(k);
                        visited[k] = true;
                    }
                }
                
            }
            
        }
                
        return answer;
    }
}