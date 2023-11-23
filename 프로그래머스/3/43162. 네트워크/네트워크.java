import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        ArrayList<Integer>[] lists = new ArrayList[n];
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++){
            lists[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i == j) continue;
                
                if(computers[i][j] == 1){
                    lists[i].add(j);
                }
                
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            if(visited[i]) continue;
            
            queue.add(i);
            
            answer++;
            
            while(!queue.isEmpty()){
                
                int cur = queue.poll();
                
                for(int k : lists[cur]){
                    if(!visited[k]){
                        queue.add(k);
                        visited[k] = true;
                    }
                }
                
            }
            
        }
                
        return answer;
    }
}