import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        HashMap<String, Integer> hash = new HashMap<>();
        
        for(int i = 0 ; i < answer.length ; i++){
            answer[i] = players[i];
            hash.put(answer[i], i);
        }
                
        for(int i = 0 ; i < callings.length ; i++){
            String cur = callings[i];
            
            if(hash.get(cur) == 0) continue;
            
            String change = answer[hash.get(cur) -1];
            
            int curScore = hash.get(cur) - 1;
            int changeScore = hash.get(change) + 1;
                                    
            hash.put(cur, curScore);
            hash.put(change, changeScore);
            
            answer[hash.get(cur)] = cur;
            
            answer[hash.get(change)] = change;
        }
        
        return answer;
    }
}