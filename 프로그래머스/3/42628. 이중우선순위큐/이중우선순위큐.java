import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        TreeSet<Integer> treeSet = new TreeSet<>();
        
        for(String input : operations){
            if(input.split(" ")[0].equals("I")){
                treeSet.add(Integer.parseInt(input.split(" ")[1]));
            }else{
                
                if(input.split(" ")[1].equals("-1")){
                    // 최솟값
                    treeSet.pollFirst();
                }else{
                    // 최댓값
                    treeSet.pollLast();
                }
                
            }
            
            System.out.println("tree = " + treeSet);
        }
        
        int size = treeSet.size();
        
        if(size == 1){
            int a = treeSet.pollLast();
            if(a < 0){
                answer[1] = a;
            }else{
                answer[0] = a;
            }
        }else if(size > 1){
            answer[0] = treeSet.pollLast();
            answer[1] = treeSet.pollFirst();
        }
        
        return answer;
    }
}