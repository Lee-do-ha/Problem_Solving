import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> reverse_pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String input : operations){
            switch(input.split(" ")[0]){
                case "I":
                    int add = Integer.parseInt(input.split(" ")[1]);
                    pq.add(add);
                    reverse_pq.add(add);
                    break;
                case "D":
                    if(input.split(" ")[1].equals("1")){
                        if(!reverse_pq.isEmpty()){
                            int poll = reverse_pq.poll();
                            pq.remove(poll);
                        }
                    }else{
                        if(!pq.isEmpty()){
                            int poll = pq.poll();
                            reverse_pq.remove(poll);
                        }
                    }
                    break;
            }
        }
        
        if(!pq.isEmpty()){
            answer[1] = pq.poll();
        }
        
        if(!reverse_pq.isEmpty()){
            answer[0] = reverse_pq.poll();
        }
        
        return answer;
    }
}