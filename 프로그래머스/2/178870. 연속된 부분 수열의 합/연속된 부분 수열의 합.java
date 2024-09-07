import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
                
        int start = 0;
        int end = 0;
        int sum = sequence[start];
        answer[0] = 0;
        answer[1] = sequence.length - 1;
        
        while(end < sequence.length && start <= end){
            if(sum < k) {
                end++;
                
                if(end > sequence.length-1) break;
                                
                sum += sequence[end];
            } else if(sum > k){
                sum -= sequence[start];
                start++;
            } else if(sum == k) {
                if(end - start < answer[1] - answer[0]) {
                    answer[0] = start;
                    answer[1] = end;
                }
                sum -= sequence[start];
                start++;
            }
        }
                
        System.out.println(start + " " + end);
        return answer;
    }
}