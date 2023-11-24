import java.util.*;

class Solution {
    // 보석 중복 제거할 Set
    static Set<String> set;
    
    // 보석의 갯수를 저장할 Map
    static Map<String, Integer> map;
    
    // 총 보석의 갯수
    static int gemSize;
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
                
        set = new HashSet<>();
        map = new HashMap<>();
        
        // 보석들 Set에 추가
        for(String gem : gems){
            set.add(gem);
        }
        
        // 총 보석의 갯수
        gemSize = set.size();
        
        // 시작점
        int start = 0;
        // 도착점
        int end = 0;
        // 총 배열의 길이
        int count = Integer.MAX_VALUE;
        
        // 보석 갯수 1개 추가
        map.put(gems[0], 1);
        
        // 투포인터 이동
        while(true){
            
            // 모든 보석을 포함한 경우
            if(pass()){
                
                // 만약 현재 통과한 배열의 길이가 기존 배열의 길이보다 짧은 경우
                if(end - start < count){
                    // 시작점
                    answer[0] = start+1;
                    // 도착점
                    answer[1] = end+1;
                    // 배열의 길이
                    count = end-start;
                }
                
                // 현재 빼려는 보석의 갯수
                int gemCount = map.get(gems[start]);
                
                // 만약 2개 이상이라면 1개 감소한 갯수로 갱신
                if(gemCount > 1){
                    map.put(gems[start], gemCount-1);
                // 만약 1개라면 map에서 제거
                }else{
                    map.remove(gems[start]);
                }
                
                // 시작점 1칸 이동
                start++;
            // 모든 보석을 포함하지 못한 경우
            }else if(!pass()){
                // 도착점 1칸 증가
                end++;
                
                // 만약 전체 보석 배열의 길이를 넘었다면 종료
                if(end >= gems.length){
                    break;
                }
                
                // 보석 추가
                add(gems[end]);
                
            }
            
        } 
        
        return answer;
    }
    
    // 전체 보석 포함 여부
    private static boolean pass(){
        
        // 현재 가진 보석의 map갯수가 전체 보석갯수와 다르다면 false
        if(map.size() != gemSize){
            return false;
        }
        
        return true;
    }
    
    // map에 보석 추가
    private static void add(String gem){
        
        // 만약 map에 보석이 없다면 1개로 넣기
        if(!map.containsKey(gem)){
            map.put(gem, 1);
        // 만약 map에 보석이 있다면 기존 보석의 갯수에서 1개 추가
        }else{
            // 현재 넣을려는 보석의 갯수
            int count = map.get(gem);
            
            // 보석의 갯수 1개 추가
            map.put(gem, count + 1);
        }
    }
}