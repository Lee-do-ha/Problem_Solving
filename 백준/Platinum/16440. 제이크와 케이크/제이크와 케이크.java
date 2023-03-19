import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        String fruit = br.readLine();

        char[] fruits = fruit.toCharArray();

        int start = 0;
        int end = (N/2)-1;

        int kiwi = 0;
        int straw = 0;

        for(int i = start ; i <= end ; i++){
            if(fruits[i] == 's') straw++;
            if(fruits[i] == 'k') kiwi++;
        }

        while(true){
            if(kiwi == N/4 && straw == N/4){
                if(start == 0 || end == N-1) {
                    System.out.println(1);
                    System.out.println(end+1);
                    return;
                }else{
                    System.out.println(2);
                    System.out.print(start + " ");
                    System.out.println(end+1);
                    return;
                }
            }

            if(fruits[start] == 's'){
                straw--;
                start++;
            }else if(fruits[start] == 'k'){
                kiwi--;
                start++;
            }

            if(fruits[end+1] == 's'){
                straw++;
                end++;
            }else if(fruits[end+1] == 'k'){
                kiwi++;
                end++;
            }
        }
    }
}