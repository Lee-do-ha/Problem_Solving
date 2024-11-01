import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        for(int i = 0 ; i < clothes.length ; i++) {
            hashMap.putIfAbsent(clothes[i][1], 0);
            hashMap.put(clothes[i][1], hashMap.get(clothes[i][1]) + 1);
        }
        
        for(String key : hashMap.keySet()) {
            answer *= hashMap.get(key) + 1;
        }
                
        return answer - 1;
    }
}