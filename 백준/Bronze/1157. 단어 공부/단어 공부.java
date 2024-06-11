import java.util.*;
 
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int[] count = new int[26];
        
        int max = -1;
        char result = '?';
        
        String s = sc.next();
        s = s.toUpperCase();
        
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'A']++;
            if(max < count[s.charAt(i) - 'A']){
                max = count[s.charAt(i) - 'A'];
                result = s.charAt(i);
            }else if(max == count[s.charAt(i) - 'A']){
                result = '?';
            }
        }
        
        System.out.println(result);
    }
}